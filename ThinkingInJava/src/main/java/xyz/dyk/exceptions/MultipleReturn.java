package xyz.dyk.exceptions;

/**
 * @author PhotoYamEgg
 * @date 2019/8/12 - 10:41
 */
public class MultipleReturn {
    public static void f(int i){
        System.out.println("Initialization that requires cleanup");
        try{
            System.out.println("Point 1");
            if (i == 1) return;
            System.out.println("Point 2");
            if (i == 2) return;
            System.out.println("Point 3");
            if (i == 3) return;
            System.out.println("End");
            return;
        }finally {
            System.out.println("Performing cleanup");
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++)
            f(i);
    }
}
