package xyz.dyk.io;
//  Copying a file using channels and buffers
//  {Args: ChannelCopy.java test.txt}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {
    public static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel in, out;
        if (args.length != 2){
            in = new FileInputStream("src/xyz.dyk.io/ChannelCopy.java").getChannel();
            out = new FileOutputStream("src/xyz.dyk.io/text.txt").getChannel();
           // System.out.println("arguments: sourcefile destfile");
           // System.exit(1);
        }
        else {
            in = new FileInputStream(args[0]).getChannel();
            out = new FileOutputStream(args[1]).getChannel();
        }
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while (in.read(buffer) != -1) {
            buffer.flip();  //  Prepare for writing
            out.write(buffer);
            buffer.clear(); //  Prepare for reading
        }
    }
}
