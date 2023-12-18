package hw12;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{

    private volatile boolean done = false;

    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();
    
    public void deposit(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().threadId() + 
                    " (d): current balance: " + balance);
            while(!done && balance >= 300){
                System.out.println(Thread.currentThread().threadId() + 
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().threadId() + 
                    " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }
    
    public void withdraw(double amount){
        lock.lock();
        try{
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().threadId() + 
                    " (w): current balance: " + balance);
            while(!done && balance <= 0){
                System.out.println(Thread.currentThread().threadId() + 
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().threadId() + 
                    " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public double getBalance() { return this.balance; }

    
    public void setDone() {
        lock.lock();
        try {
            done = true;
            sufficientFundsCondition.signalAll();
            belowUpperLimitFundsCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    
    public boolean isDone() {
        return done;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        for(int i = 0; i < 5; i++){
            new Thread( new DepositRunnable(bankAccount) ).start();
            new Thread( new WithdrawRunnable(bankAccount) ).start();
        }
       
        Thread.sleep(10000);
        bankAccount.setDone();
    }
}
