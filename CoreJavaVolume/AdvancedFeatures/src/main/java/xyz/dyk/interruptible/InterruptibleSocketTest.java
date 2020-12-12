package xyz.dyk.interruptible;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This program shows how to interrupt a scket channel.
 */
public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new InterruptibleSocketFrame();
            frame.setTitle("InterruptibleSocketTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class InterruptibleSocketFrame extends JFrame {
    private Scanner in;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private JTextArea messages;
    private TestServer server;
    private Thread connectThread;

    public InterruptibleSocketFrame() {
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);

        final int TEXT_ROWS = 20;
        final int TEXT_COLUMNS = 60;
        messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");

        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);

        interruptibleButton.addActionListener(e -> {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() -> {
                try {
                    connectInterruptibly();
                } catch (IOException e1) {
                    messages.append("\nInterruptibileSocketTest.connectInterruptibly: " + e1);
                }
            });
            connectThread.start();
        });

        blockingButton.addActionListener(e -> {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(false);
            connectThread = new Thread(() -> {
                try {
                    connectBlocking();
                } catch (Exception e2) {
                    messages.append("\nInterruptibleSocketTest.connectBlocking: " + e2);
                }
            });
            connectThread.start();
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);
        cancelButton.addActionListener(e -> {
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });
        server = new TestServer();
        new Thread(server).start();
        pack();
    }

    /**
     * Connects to the test server, using interruptible I/O.
     *
     * @throws IOException
     */
    public void connectInterruptibly() throws IOException {
        messages.append("Interruptible: \n");
        try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189))) {
            in = new Scanner(channel, StandardCharsets.UTF_8.name());
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        } finally {
            EventQueue.invokeLater(() -> {
                messages.append("Channel closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    public void connectBlocking() throws IOException {
        messages.append("Blocking:\n");
        try (Socket socket = new Socket("localhost", 8189)) {
            in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8.name());
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        } finally {
            EventQueue.invokeLater(() -> {
                messages.append("Socket closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }


    /**
     * A multithreaded server that listens to prot 8189 and sends numbers to client,
     * simulating a hanging server after 10 numbers.
     */
    class TestServer implements Runnable {
        @Override
        public void run() {
            try (ServerSocket s = new ServerSocket(8189)) {
                while (true) {
                    Socket socket = s.accept();
                    new Thread(new TestServerHandler(socket)).start();
                }
            } catch (IOException e) {
                messages.append("\nTestServer.run: " + e);
            }
        }
    }

    /**
     * this class handles the client input for one server socket connection.
     */
    class TestServerHandler implements Runnable {
        private Socket incoming;
        private int counter;

        public TestServerHandler(Socket incoming) {
            this.incoming = incoming;
        }

        @Override
        public void run() {
            try {
                try {
                    OutputStream outStream = incoming.getOutputStream();
                    PrintWriter out = new PrintWriter(
                            new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true);
                    while (counter < 100) {
                        counter++;
                        if (counter <= 10) {
                            out.println(counter);
                        }
                        Thread.sleep(100);
                    }
                } finally {
                    incoming.close();
                    messages.append("Closing server\n");
                }
            } catch (Exception e) {
                messages.append("\nTestServerHandler.run: " + e);
            }
        }
    }
}