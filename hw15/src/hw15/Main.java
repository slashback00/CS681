package hw15;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int NUM_OBSERVABLES = 5;
    private static final int NUM_LINE_CHART_OBSERVERS = 10;
    private static final int NUM_TABLE_OBSERVERS = 2;
    private static final int NUM_3D_OBSERVERS = 1;
    private static final int NUM_THREADS = 15; 

    public static void main(String[] args) throws InterruptedException {
     
        StockQuoteObservable[] observables = new StockQuoteObservable[NUM_OBSERVABLES];
        List<Observer<StockEvent>> observers = new ArrayList<>();
        for (int i = 0; i < NUM_OBSERVABLES; i++) {
            observables[i] = new StockQuoteObservable();
        }
        for (int i = 0; i < NUM_LINE_CHART_OBSERVERS; i++) {
            observers.add(new LineChartObserver());
        }
        for (int i = 0; i < NUM_TABLE_OBSERVERS; i++) {
            observers.add(new TableObserver());
        }
        for (int i = 0; i < NUM_3D_OBSERVERS; i++) {
            observers.add(new ThreeDObserver());
        }

       
        Random random = new Random();
        for (Observer<StockEvent> observer : observers) {
            for (int i = 0; i < 2; i++) {
                int index = random.nextInt(NUM_OBSERVABLES);
                observables[index].addObserver(observer);
            }
        }

        
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= NUM_THREADS; i++) {
            Thread thread = new Thread(() -> {
                Random random1 = new Random();
                Random random2 = new Random();
                for (int j = 0; j < 10; j++) {
                    int index = random1.nextInt(NUM_OBSERVABLES);
                    String ticker = "TICKER" + random2.nextInt(10);
                    double quote = random2.nextDouble() * 100;
                    observables[index].changeQuote(ticker, quote);
                }
            }, "thread" + i);
            threads.add(thread);
            thread.start();
        }

        
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
