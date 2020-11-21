package xyz.dyk.properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            PropertiesFrame frame = new PropertiesFrame();
            frame.setVisible(true);
        });
    }
}

class PropertiesFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    private File propertiesFile;
    private Properties settings;

    public PropertiesFrame() throws HeadlessException {
        //  get position, size, title from properties

        String userDir = System.getProperty("user.home");
        File propertiesDir = new File(userDir, ".dyk");
        if (!propertiesDir.exists()) {
            propertiesDir.mkdir();
        }
        propertiesFile = new File(propertiesDir, "program.properties");

        Properties defaultSettings = new Properties();
        defaultSettings.setProperty("left", "0");
        defaultSettings.setProperty("top", "0");
        defaultSettings.setProperty("width", "" + DEFAULT_WIDTH);
        defaultSettings.setProperty("height", "" + DEFAULT_HEIGHT);
        defaultSettings.setProperty("title", "");

        settings = new Properties(defaultSettings);

        if (propertiesFile.exists()) {
            try (InputStream in = new FileInputStream(propertiesFile)) {
                settings.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int left = Integer.parseInt(settings.getProperty("left"));
        int top = Integer.parseInt(settings.getProperty("top"));
        int width = Integer.parseInt(settings.getProperty("width"));
        int height = Integer.parseInt(settings.getProperty("height"));

        setBounds(left, top, width, height);

        //  if no tile given, ask user

        String title = settings.getProperty("title");
        if (title.equals("")) {
            title = JOptionPane.showInputDialog("Please supply a frame title:");
        }
        if (title == null) {
            title = "";
        }
        setTitle(title);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                settings.setProperty("left", "" + getX());
                settings.setProperty("top", "" + getY());
                settings.setProperty("width", "" + getWidth());
                settings.setProperty("height", "" + getHeight());
                settings.setProperty("title", "" + getTitle());
                try (OutputStream out = new FileOutputStream(propertiesFile)) {
                    settings.store(out, "Program Properties");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}