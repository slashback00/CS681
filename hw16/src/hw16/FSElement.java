package hw16;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime dateTime; 
    protected Directory parentDirectory;
    protected final ReentrantLock lock = new ReentrantLock();

   
    public FSElement(Directory parentDirectory, String name, int size, LocalDateTime dateTime) {
        this.name = name;
        this.size = size;
        this.dateTime = dateTime;
        this.parentDirectory= parentDirectory;
    }

    public int getSize() {
        lock.lock();
        try {
            return this.size;
        } finally {
            lock.unlock();
        }
    }

    public LocalDateTime getCreationtime() {
        lock.lock();
        try {
            return this.dateTime;
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        lock.lock();
        try {
            return this.name;
        } finally {
            lock.unlock();
        }
    }

    public Directory getparentDirectory() {
        lock.lock();
        try {
            return this.parentDirectory;
        } finally {
            lock.unlock();
        }
    }

    public abstract boolean isDirectory();
    public abstract boolean isProxy();

    public void accept(FSVisitor visitor) {
    }
}
