package xyz.dyk.exceptions;

/**
 * @author PhotoYamEgg
 * @date 2019/8/12 - 11:02
 */
public class ExceptionSilencer {
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        }finally {
            //  Using 'return' inside the finally block
            //  will silence any thrown exception
            return;
        }
    }
}
