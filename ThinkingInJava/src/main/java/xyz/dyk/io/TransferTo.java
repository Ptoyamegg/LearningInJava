package xyz.dyk.io;
//  Using transferTo() between channels
//  {Args: TransferTo.java TransferTo.txt}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferTo {
    public static void main(String[] args) throws IOException {
        FileChannel in, out;
        if (args.length != 2) {
            in = new FileInputStream("src/xyz.dyk.io/TransferTo.java").getChannel();
            out = new FileOutputStream("src/xyz.dyk.io/TransferTo.txt").getChannel();
            //System.out.println("arguments: sourcefile destfile");
            //System.exit(1);
        }
        else {
            in = new FileInputStream(args[0]).getChannel();
            out = new FileOutputStream(args[1]).getChannel();
        }
        in.transferTo(0, in.size(), out);
        //  Or:
        //  out.transferFrom(in, 0, in.size());
    }
}
