package xyz.dyk.strings;

/**
 * @author PhotoYamEgg
 * @date 2019/9/8 - 16:38
 */
public class DatebaseException extends Exception{
    public DatebaseException(int transactionID, int queryID, String message){
        super(String.format("(t%d,q%d) %s", transactionID, queryID, message));
    }

    public static void main(String[] args) {
        try {
            throw new DatebaseException(3, 7, "Write failed");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
