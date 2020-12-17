package xyz.dyk.complier;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class StringBuilderJavaSource extends SimpleJavaFileObject {
    private StringBuilder code;

    public StringBuilderJavaSource(String name) {
        super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        code = new StringBuilder();
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }

    public void append(String str) {
        code.append(str);
        code.append('\n');
    }
}
