package xyz.dyk.containers;
//  Predicting the weather with groundhogs.

import java.util.Random;

public class Prediction {
    private static Random random = new Random(47);
    private boolean shadow = random.nextDouble() > 0.5;
    public String toString() {
        if (shadow)
            return "Six more weeks of Winter!\n";
        else
            return "Early Spring!\n";
    }
}
