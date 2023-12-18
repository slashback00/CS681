package hw13;

public class Main {
    public static void main(String[] args) {
        LotterySystemSafe lotterySystem = new LotterySystemSafe(10); 

        for (int i = 0; i < 15; i++) { 
            new LotteryThread(lotterySystem).start();
        }
    }
}

