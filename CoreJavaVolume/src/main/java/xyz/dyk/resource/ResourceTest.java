package xyz.dyk.resource;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * javac resource/ResourceTest.java
 * jar cvfm ResourceTest.jar resource/ResourceTest.mf resource/*.gif resource/*.txt
 * java -jar ResourceTest.jar
 * 运行不起来。。。。。
 */
public class ResourceTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ResourceTestFrame();
            frame.setTitle("ResourceTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class ResourceTestFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    public ResourceTestFrame() throws HeadlessException {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        URL aboutUrl = getClass().getResource("about.gif");
        Image img = new ImageIcon(aboutUrl).getImage();
        setIconImage(img);

        JTextArea textArea = new JTextArea();
        InputStream stream = getClass().getResourceAsStream("about.txt");
        try (Scanner in = new Scanner(stream, "UTF-8")) {
            while (in.hasNext()) {
                textArea.append(in.nextLine() + "\n");
            }
        }
        add(textArea);
    }
}
