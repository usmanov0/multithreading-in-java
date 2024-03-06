package race_condition;

import java.util.Arrays;

public class Main {
    private int smth;

    public Main(int smth){
        this.smth = smth;
    }
    public static void main(String[] args) {

    }
}

class Bank{
    private final double[] accounts;

    public Bank(int n, double initialBalance){
        this.accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount){
        if (accounts[from] < amount) return;
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total balance is %10.2f%n",getTotalBalance());
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
