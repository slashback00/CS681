package hw09;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    protected final ReentrantLock lock = new ReentrantLock();
    protected boolean done = false;

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            lock.lock();
            boolean isDone;
            try {
                isDone = done;
            } finally {
                lock.unlock();
            }
            if (isDone) {
                break;
            }

            if (divisor > 2 && isEven(divisor)) {
                divisor++;
                continue;
            }
            if (dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
            } else {
                if (divisor == 2) {
                    divisor++;
                } else {
                    divisor += 2;
                }
            }
        }
    }

    protected boolean isEven(long n) {
        return n % 2 == 0;
    }

    @Override
    public void run() {
        generatePrimeFactors();
        System.out.println("Thread #" + Thread.currentThread()+ " generated " + factors);
    }

    public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer factorizer = new RunnableCancellablePrimeFactorizer(36, 2, (long) Math.sqrt(36));
        Thread thread = new Thread(factorizer);
        thread.start();
        try {
            Thread.sleep(100); 
            factorizer.setDone();
            thread.join();
            System.out.println("Factors: " + factorizer.getPrimeFactors());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

