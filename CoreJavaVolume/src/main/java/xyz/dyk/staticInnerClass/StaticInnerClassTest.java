package xyz.dyk.staticInnerClass;

public class StaticInnerClassTest {
    public static void main(String[] args) {
        double[] d = new double[20];
        for (int i = 0; i < d.length; i++)
            d[i] = 100 * Math.random();
        ArrayAlg.Pair minmax = ArrayAlg.Pair.minmax(d);
        System.out.println("minmax.getFirst() = " + minmax.getFirst());
        System.out.println("minmax.getSecond() = " + minmax.getSecond());
    }
}

class ArrayAlg {
    public static class Pair {
        private double first;
        private double second;

        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }

        public static Pair minmax(double[] values) {
            double min = Double.POSITIVE_INFINITY;
            double max = Double.NEGATIVE_INFINITY;
            for (double value : values) {
                if (min > value) min = value;
                if (max < value) max = value;
            }
            return new Pair(min, max);
        }
    }
}