package hw12;

class WithdrawRunnable implements Runnable {
    private ThreadSafeBankAccount2 account;
    
    public WithdrawRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void run() {
        while(!account.isDone()) {
            
            account.withdraw(100);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

