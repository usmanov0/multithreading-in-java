package with_lock;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RaceConditionWithLock {
    private final double[] accounts;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private

    RaceConditionWithLock(int n, double initialBalance){
        this.accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount){
        try {
            while (accounts[from] < amount){
                condition.await();
            }
            lock.lock();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total balance is %10.2f%n",getTotalBalance());
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public double getTotalBalance(){
        double sum = 0;
        for (double a: accounts)
            sum += a;
        return sum;
    }

    public int size(){
        return accounts.length;
    }
}
