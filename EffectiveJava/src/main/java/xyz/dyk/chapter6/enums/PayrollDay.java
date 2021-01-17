package xyz.dyk.chapter6.enums;

public enum PayrollDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND),
    ;

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    PayrollDay() {
        this(PayType.WEEKDAY);
    }

    private enum PayType {
        WEEKDAY {
            @Override
            int overtimePay(int mins, int payRate) {
                return mins <= MINS_PER_SHIFT ? 0 :
                        (mins - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            @Override
            int overtimePay(int mins, int payRate) {
                return mins * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);

        private static final int MINS_PER_SHIFT = 8 * 60;
    }

    int pay(int minsWorked, int payRate) {
        int basePay = minsWorked * payRate;
        return basePay + this.payType.overtimePay(minsWorked, payRate);
    }
}
