package hw12;

public class DepositRunnable implements Runnable {
    private ThreadSafeBankAccount2 account;
    
    public DepositRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void run() {
        while(!account.isDone()) {
            
            account.deposit(100); 
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

