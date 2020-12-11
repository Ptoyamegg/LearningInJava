package xyz.dyk.chapter4.threaded;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThereadedEchoServer {
    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(8189)) {
            int i = 1;
            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                new Thread(new ThreadedEchoHandler(incoming)).start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadedEchoHandler implements Runnable {
    private Socket incoming;

    public ThreadedEchoHandler(Socket incomingSocket) {
        this.incoming = incomingSocket;
    }

    @Override
    public void run() {
        try (InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream()) {
            Scanner in = new Scanner(inStream, StandardCharsets.UTF_8.name());
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true);
            out.println("Hello! Enter BYE to exit.");

            // echo client input
            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if ("BYE".equals(line.trim())) done = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}