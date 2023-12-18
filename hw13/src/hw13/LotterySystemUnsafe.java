package hw13 ;

public class LotterySystemUnsafe {
    private int availableTickets;

    public LotterySystemUnsafe(int tickets) {
        this.availableTickets = tickets;
    }

    public void purchaseTicket() {
        if (availableTickets > 0) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
            availableTickets--;
            System.out.println("Ticket purchased, remaining: " + availableTickets);
        } else {
            System.out.println("No more tickets available");
        }
    }
}
