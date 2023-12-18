package hw16;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement {
    private FSElement target;
    private final ReentrantLock lock = new ReentrantLock();

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }

    public boolean isFile() {
        return !target.isDirectory();
    }

    public boolean isLink() {
        return true;
    }

    public FSElement getTarget() {
        return target;
    }

    public void setTarget(FSElement target) {
        lock.lock();
        try {
            this.target = target;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isDirectory() {
        return false; 
    }

    @Override
    public boolean isProxy() {
        return true; 
    }

    
    public void accept(FSVisitor visitor) {
        lock.lock();
        try {
            visitor.visit(this);
        } finally {
            lock.unlock();
        }
    }
}

