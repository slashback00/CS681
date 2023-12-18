package hw17;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent> {
    private final Map<String, Double> quotes = new HashMap<>();
    private final Lock lockTQ = new ReentrantLock();

    public void changeQuote(String ticker, double quote) {
        lockTQ.lock();
        StockEvent event;
        try {
            quotes.put(ticker, quote);
            event = new StockEvent(ticker, quote);
        } finally {
            lockTQ.unlock();
        }
        notifyObservers(event);
    }

    public double getQuote(String ticker) {
        lockTQ.lock();
        try {
            return quotes.getOrDefault(ticker, 0.0);
        } finally {
            lockTQ.unlock();
        }
    }
}
