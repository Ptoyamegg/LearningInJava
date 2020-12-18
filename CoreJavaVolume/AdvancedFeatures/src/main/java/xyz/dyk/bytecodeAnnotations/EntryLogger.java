package xyz.dyk.bytecodeAnnotations;

import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Adds "entering" logs to all methods of a class that have the LogEntry annotation.
 */
public class EntryLogger extends ClassVisitor {
    private String className;

    public EntryLogger(ClassVisitor classVisitor, String className) {
        super(Opcodes.ASM5, classVisitor);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String methodName, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, methodName, descriptor, signature, exceptions);
        return new AdviceAdapter(Opcodes.ASM5, mv, access, methodName, descriptor) {
            private String loggerName;

            @Override
            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                return new AnnotationVisitor(Opcodes.ASM5) {
                    @Override
                    public void visit(String name, Object value) {
                        if (descriptor.equals("Lxyz/dyk/bytecodeAnnotations/LogEntry;") && name.equals("logger")) {
                            loggerName = value.toString();
                        }
                    }
                };
            }

            @Override
            public void onMethodEnter() {
                if (loggerName != null) {
                    visitLdcInsn(loggerName);
                    visitMethodInsn(INVOKESTATIC, "java/util/logging/Logger", "getLogger",
                            "(Ljava/lang/String;)Ljava/util/logging/Logger;", false);
                    visitLdcInsn(className);
                    visitLdcInsn(methodName);
                    visitMethodInsn(INVOKEVIRTUAL, "java/util/logging/Logger", "entering",
                            "(Ljava/lang/String;Ljava/lang/String;)V", false);
                    loggerName = null;
                }
            }
        };
    }

    /**
     * Adds entry logging code to the given class.
     *
     * @param args the name of the class file to patch
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        args = new String[] {"CoreJavaVolume/AdvancedFeatures/target/classes/xyz/dyk/set/Item.class"};
        if (args.length == 0) {
            System.out.println("USAGE: java xyz.dyk.bytecodeAnnotations.EnterLogger classfile");
            System.exit(1);
        }
        Path path = Paths.get(args[0]);
        ClassReader reader = new ClassReader(Files.newInputStream(path));
        ClassWriter writer = new ClassWriter(
                ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        EntryLogger entryLogger = new EntryLogger(writer,
                path.toString().replace(".class", "").replaceAll("[/\\\\]", "."));
        reader.accept(entryLogger, ClassReader.EXPAND_FRAMES);
        Files.write(Paths.get(args[0]), writer.toByteArray());
    }
}
