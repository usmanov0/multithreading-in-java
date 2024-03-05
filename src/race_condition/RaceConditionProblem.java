package race_condition;

public class RaceConditionProblem {
    public static final int N_ACCOUNT = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args){
        var bank = new Bank(N_ACCOUNT, INITIAL_BALANCE);

        for (int i = 0; i < N_ACCOUNT; i++) {
            int fromAccount = i;
            Runnable r = () ->{
                try {
                    while (true){
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                     }
                } catch (InterruptedException e) {
                }
            };
            var t = new Thread(r);
            t.start();
        }
    }
}
