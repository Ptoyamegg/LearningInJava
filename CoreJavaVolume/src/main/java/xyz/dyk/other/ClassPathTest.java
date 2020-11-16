package xyz.dyk.other;

public class ClassPathTest {
    public static void main(String[] args) {
        String property = System.getProperty("java.class.path");
        String[] split = property.split(";");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
