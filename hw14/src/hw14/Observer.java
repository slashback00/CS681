package hw14;

public interface Observer<T> {
    void update(Observable<T> sender, T event);
}

