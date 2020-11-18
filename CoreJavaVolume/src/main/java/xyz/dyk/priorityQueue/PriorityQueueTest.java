package xyz.dyk.priorityQueue;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 23));

        System.out.println("Iterating over elements...");

        for (LocalDate localDate : pq) {
            System.out.println(localDate);
        }
        System.out.println("Removing elements...");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
//            System.out.println(pq.poll());
        }
    }
}
