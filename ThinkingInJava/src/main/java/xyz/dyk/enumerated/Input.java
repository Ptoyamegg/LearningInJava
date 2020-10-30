package xyz.dyk.enumerated;

import java.util.Random;

public enum Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABOUT_TRANSACTION {
        public int amount() {   //  Disallow
            throw new RuntimeException("ABOUT.amount()");
        }
    },
    STOP {  //  This must be the last instance.
        public int amount() {   //  Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    int value;  //  In cents
    Input(int i) { value = i; }
    Input() {}
    int amount() { return value; }  //  In cents
    static Random rand = new Random(47);
    public static Input randomSelection() {
        //  Don't include STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
}
