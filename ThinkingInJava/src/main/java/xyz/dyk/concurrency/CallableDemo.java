package xyz.dyk.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int i) {
        id = i;
        System.out.println("new TaskWithResult(" + i + ")");
    }
    public String call() {
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results =
                new ArrayList<>();
        for (int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for (Future<String> f : results)
            try {
                //  get() blocks until completion:
                System.out.println(f.get());
            }catch (InterruptedException e) {
                System.out.println(e);
                return;
            }catch (ExecutionException e) {
                System.out.println(e);
            }finally {
                exec.shutdown();
            }
    }
}
