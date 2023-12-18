package hw10;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FileSystem {
    private static final AtomicReference<FileSystem> instance = new AtomicReference<>();
    private List<Directory> rootDirs;
    private static final Object rootDirsLock = new Object();

    private FileSystem() {
        rootDirs = new ArrayList<>();
    }

    public static FileSystem getInstance() {
        
        instance.updateAndGet(existingInstance -> {
            if (existingInstance == null) {
                return new FileSystem();
            }
            return existingInstance;
        });
        return instance.get();
    }

    public void appendRootDir(Directory root) {
        synchronized (rootDirsLock) {
            rootDirs.add(root);
        }
    }

    public List<Directory> getRootDirs() {
        synchronized (rootDirsLock) {
            
            return new ArrayList<>(rootDirs);
        }
    }




public static void main(String[] args) throws InterruptedException {
        final int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];
        AtomicBoolean keepRunning = new AtomicBoolean(true);

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                while (keepRunning.get()) {
                    
                    FileSystem fileSystem = FileSystem.getInstance();
                    Directory root = new Directory(null, "root", 0, LocalDateTime.now());
                    fileSystem.appendRootDir(root);
                    System.out.println("Accessing FileSystem data...");
                }
            });
            threads[i].start();
        }

        
        Thread.sleep(5000); 

        
        keepRunning.set(false);

        
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("All threads have terminated.");
    }
}
