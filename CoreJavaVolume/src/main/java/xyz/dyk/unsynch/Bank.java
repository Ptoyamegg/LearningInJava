package xyz.dyk.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private final Lock bankLock = new ReentrantLock();

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }


    public void transfer2(int from, int to, double amount) {
        bankLock.lock();
        try {
            transfer(from, to, amount);
        } finally {
            bankLock.unlock();
        }
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) {
            return;
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d ", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
    }

    public double getTotalBalance() {
        double sum = 0;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.printf(" End Balance: %10.2f%n", getTotalBalance());
        super.finalize();
    }
}
