package xyz.dyk.io;
//  Create a serialized output file.

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class FreezeAlien {
    public static void main(String[] args) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("src/xyz.dyk.io/X.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}
