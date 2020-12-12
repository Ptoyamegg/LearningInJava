package xyz.dyk.threaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HalfClosedTest {
    public static void main(String[] args) {
        halfClosedSocket("www.baidu.com", 80);
    }

    public static void halfClosedSocket(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8.name());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            System.out.println(StandardCharsets.UTF_8.name());
            //  send request data

            out.print("......");
            out.flush();

            socket.shutdownOutput();
            //  now socket is half-closed
            //  read response data
            while (in.hasNextLine()) {
                String s = in.nextLine();
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
