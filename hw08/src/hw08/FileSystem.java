package hw08;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static volatile FileSystem instance;  
    private static final Lock instanceLock = new ReentrantLock();
    private List<Directory> rootDirs;
    private static final Lock rootDirsLock = new ReentrantLock();

    private FileSystem() {
        rootDirs = new ArrayList<>();
    }

    public static FileSystem getInstance() {
        if (instance == null) {
            instanceLock.lock();
            try {
                if (instance == null) {
                    instance = new FileSystem();
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    public void appendRootDir(Directory root) {
        rootDirsLock.lock();
        try {
            rootDirs.add(root);
        } finally {
            rootDirsLock.unlock();
        }
    }

    public List<Directory> getRootDirs() {
        rootDirsLock.lock();
        try {
            return new ArrayList<>(rootDirs);
        } finally {
            rootDirsLock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            FileSystem fileSystem = FileSystem.getInstance();
            Directory root = new Directory(null, "root", 0, LocalDateTime.now());
            fileSystem.appendRootDir(root);
            System.out.println("Root Directories: " + fileSystem.getRootDirs());
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
