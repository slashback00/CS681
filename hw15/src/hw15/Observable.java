package hw15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Observable<T> {
    private final List<Observer<T>> observers = new ArrayList<>();
    private final Lock lockObs = new ReentrantLock();

    public void addObserver(Observer<T> observer) {
        lockObs.lock();
        try {
            observers.add(observer);
        } finally {
            lockObs.unlock();
        }
    }

    public void removeObserver(Observer<T> observer) {
        lockObs.lock();
        try {
            observers.remove(observer);
        } finally {
            lockObs.unlock();
        }
    }

    public void notifyObservers(T event) {
        List<Observer<T>> observersSnapshot;
        lockObs.lock();
        try {
            observersSnapshot = new ArrayList<>(observers);
        } finally {
            lockObs.unlock();
        }
        for (Observer<T> observer : observersSnapshot) {
            observer.update(this, event);
        }
    }
}
