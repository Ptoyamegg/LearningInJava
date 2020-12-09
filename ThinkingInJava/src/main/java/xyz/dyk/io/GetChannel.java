package xyz.dyk.io;
//  Getting channels from xyz.dyk.streams

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    public static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        //  Write a file:
        FileChannel fc = new FileOutputStream("src/xyz.dyk.io/data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        //  Add to the end of the file:
        fc = new RandomAccessFile("src/xyz.dyk.io/data.txt", "rw").getChannel();
        fc.position(fc.size()); //  Move to the end
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();
        //  Read the file:
        fc = new FileInputStream("src/xyz.dyk.io/data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining())
            System.out.print((char)buffer.get());
    }
}
