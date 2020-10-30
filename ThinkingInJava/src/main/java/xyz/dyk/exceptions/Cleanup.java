package xyz.dyk.exceptions;
//  Guaranteeing proper cleanup of a resource.

/**
 * @author PhotoYamEgg
 * @date 2019/8/14 - 20:42
 */
public class Cleanup {
    public static void main(String[] args) {
        try {
            InputFile in = new InputFile("Cleanup.java");
            try {
                String s;
                int i = 1;
                while ((s = in.getLine()) != null)
                    ;   //  Perform line-by-line processing here...
            }catch (Exception e){
                System.out.println("Caught Exception in main");
                e.printStackTrace(System.out);
            }finally {
                in.dispose();
            }
        }catch (Exception e){
            System.out.println("InputFile construction failed");
        }
    }
}
