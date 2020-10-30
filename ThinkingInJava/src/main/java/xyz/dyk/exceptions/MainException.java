package xyz.dyk.exceptions;

import java.io.FileInputStream;

/**
 * @author PhotoYamEgg
 * @date 2019/8/14 - 22:09
 */
public class MainException {
    //  Pass all xyz.dyk.exceptions to the console:
    public static void main(String[] args) throws Exception{
        //  Open the file:
        FileInputStream file =
                new FileInputStream("MainException.java");
        //  Use the file ...
        //  Close the file:
        file.close();
    }
}
