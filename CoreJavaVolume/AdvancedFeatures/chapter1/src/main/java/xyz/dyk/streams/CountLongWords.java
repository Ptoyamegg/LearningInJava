package xyz.dyk.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String context = new String(Files.readAllBytes(
                Paths.get("C:/Users/k1345/Desktop/redis.profile")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(context.split("\\PL+"));

        long usedTime = System.currentTimeMillis();
        long count = 0;
        for (String word : words) {
            if (word.length() > 12) {
                count++;
            }
        }
        System.out.println("usedTime = " + (System.currentTimeMillis() - usedTime));
        System.out.println("count = " + count);

        usedTime = System.currentTimeMillis();
        count = words.stream().filter(word -> word.length() > 12).count();
        System.out.println("usedTime = " + (System.currentTimeMillis() - usedTime));
        System.out.println("count = " + count);

        usedTime = System.currentTimeMillis();
        count = words.parallelStream().filter(word -> word.length() > 12).count();
        System.out.println("usedTime = " + (System.currentTimeMillis() - usedTime));
        System.out.println("count = " + count);
    }
}
