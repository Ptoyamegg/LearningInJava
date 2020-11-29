package xyz.dyk.swing;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SwingThreadTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new SwingThread();
            frame.setTitle("SwingThread");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class SwingThread extends JFrame {
    public SwingThread() throws HeadlessException {
        final JComboBox<Integer> combo = new JComboBox<>();
        combo.insertItemAt(Integer.MAX_VALUE, 0);
        combo.setPrototypeDisplayValue(combo.getItemAt(0));
        combo.setSelectedIndex(0);

        JPanel panel = new JPanel();

        JButton goodButton = new JButton("Good");

        goodButton.addActionListener(event -> {
            new Thread(new GoodWorkerRunnable(combo)).start();
        });
        panel.add(goodButton);
        JButton badButton = new JButton("Bad");
        badButton.addActionListener(event -> {
            new Thread(new BadWorkerRunnable(combo)).start();
        });
        panel.add(badButton);

        panel.add(combo);
        add(panel);
        pack();
    }
}

class BadWorkerRunnable implements Runnable {
    private JComboBox<Integer> combo;
    private Random generator;

    public BadWorkerRunnable(JComboBox<Integer> combo) {
        this.combo = combo;
        generator = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int i = Math.abs(generator.nextInt());
                if (i % 2 == 0) {
                    combo.insertItemAt(i, 0);
                } else if (combo.getItemCount() > 0) {
                    combo.removeItem(i % combo.getItemCount());
                }
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
        }
    }
}

class GoodWorkerRunnable implements Runnable {
    private JComboBox<Integer> combo;
    private Random generator;

    public GoodWorkerRunnable(JComboBox<Integer> combo) {
        this.combo = combo;
        generator = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                EventQueue.invokeLater(() -> {
                    int i = Math.abs(generator.nextInt());
                    if (i % 2 == 0) {
                        combo.insertItemAt(i, 0);
                    } else if (combo.getItemCount() > 0) {
                        combo.removeItem(i % combo.getItemCount());
                    }
                });
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
        }
    }
}