package hw09;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    @Override
    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Thread #" + Thread.currentThread() + " is interrupted.");
                return;
            }

            lock.lock();
            try {
                if (done) {
                    return;
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
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void run() {
        generatePrimeFactors();
        System.out.println("Thread #" + Thread.currentThread()+ " generated " + factors);
    }

    public static void main(String[] args) {
        RunnableCancellableInterruptiblePrimeFactorizer factorizer = 
            new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, (long) Math.sqrt(36));
        Thread thread = new Thread(factorizer);
        thread.start();
        try {
            Thread.sleep(100); 
            factorizer.setDone();  
            thread.interrupt();    
            thread.join();
            System.out.println("Factors: " + factorizer.getPrimeFactors());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
