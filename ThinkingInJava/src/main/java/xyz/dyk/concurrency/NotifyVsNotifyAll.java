package xyz.dyk.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Blocker {
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.print(Thread.currentThread() + " ");
            }
        }catch (InterruptedException e) {
            //  OK to exit this way
        }
    }
    synchronized void prop() { notify(); }
    synchronized void propAll() { notifyAll(); }
}

class Task implements Runnable {
    static Blocker blocker = new Blocker();
    public void run() { blocker.waitingCall(); }
}

class Task2 implements Runnable {
    //  A separate Blocker object:
    static Blocker blocker = new Blocker();
    public void run() { blocker.waitingCall(); }
}

public class NotifyVsNotifyAll {
    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Task());
        exec.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prop = true;
            public void run() {
                if (prop) {
                    System.out.print("\nnotify() ");
                    Task.blocker.prop();
                    prop = false;
                } else {
                    System.out.print("\nnotifyAll() ");
                    Task.blocker.propAll();
                    prop = true;
                }
            }
        }, 400, 400);   //  Run every .4 second
        TimeUnit.SECONDS.sleep(5);  //  Run for a while...
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.print("Task2.blocker.propAll() ");
        Task2.blocker.propAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        exec.shutdownNow(); //  Interrupt all tasks
    }
}
