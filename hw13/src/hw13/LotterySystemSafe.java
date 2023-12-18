package hw13;

public class LotterySystemSafe {
    private int availableTickets;
    private volatile boolean isRunning = true;

    public LotterySystemSafe(int tickets) {
        this.availableTickets = tickets;
    }

    public synchronized void purchaseTicket() {
        if (!isRunning || availableTickets <= 0) {
            System.out.println("No more tickets available or sales stopped");
            return;
        }

        try {
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        }
        availableTickets--;
        System.out.println("Ticket purchased, remaining: " + availableTickets);
    }

    public void stopSales() {
        isRunning = false;
    }
}

