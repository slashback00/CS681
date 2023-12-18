package hw11;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class File extends FSElement {
    protected Directory parent;
    protected String name;

    
    private final ReentrantLock lock = new ReentrantLock();

    public File(Directory parent, String name, int size, LocalDateTime ldt) {
        super(parent, name, size, ldt);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isProxy() {
        return false;
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


