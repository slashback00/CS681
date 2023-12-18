package hw17; 

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class Observable<T> {
    private final ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    public void notifyObservers(T event) {
        observers.forEach(observer -> observer.update(this, event));
    }

    public int countObservers() {
        return observers.size();
    }

    public List<Observer<T>> getObservers() {
        return observers.stream().collect(Collectors.toList());
    }

    public void clearObservers() {
        observers.clear();
    }
}
