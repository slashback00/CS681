package hw13;

public class LotteryThread extends Thread {
    private LotterySystemSafe lotterySystem;

    public LotteryThread(LotterySystemSafe system) {
        this.lotterySystem = system;
    }

    @Override
    public void run() {
        lotterySystem.purchaseTicket();
    }
}



