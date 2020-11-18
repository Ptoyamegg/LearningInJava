package xyz.dyk.shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleTest {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            numbers.add(i + 1);
        }
        Collections.shuffle(numbers);
        List<Integer> winningCombination = numbers.subList(0, 6);
        Collections.sort(winningCombination);
        System.out.println(winningCombination);
    }
}
