package xyz.dyk.complier;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class ByteArrayJavaClass extends SimpleJavaFileObject {
    private ByteArrayOutputStream stream;

    public ByteArrayJavaClass(String name) {
        super(URI.create("byte:///" + name), Kind.CLASS);
        stream = new ByteArrayOutputStream();
    }

    public OutputStream openOutputStream() throws IOException {
        return stream;
    }

    public byte[] getBytes() {
        return stream.toByteArray();
    }
}
