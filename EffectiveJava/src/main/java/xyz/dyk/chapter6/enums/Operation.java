package xyz.dyk.chapter6.enums;

public enum Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };
    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(double x, double y);

    public static Operation inverse(Operation operation) {
        switch (operation) {
            case PLUS:
                return Operation.MINUS;
            case MINUS:
                return Operation.PLUS;
            case TIMES:
                return Operation.DIVIDE;
            case DIVIDE:
                return Operation.TIMES;

            default:
                throw new AssertionError("Unknown op: " + operation);
        }
    }

    public static void main(String[] args) {
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", 1d, op, 2d, op.apply(1d, 2d));
        }
    }
}
