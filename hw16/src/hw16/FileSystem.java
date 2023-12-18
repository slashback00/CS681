package hw16;

import hw16.util.FileCrawlingVisitor;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSystem {
    private static final AtomicBoolean keepRunning = new AtomicBoolean(true);
    private static final ThreadLocal<FileCrawlingVisitor> threadLocalVisitor = ThreadLocal.withInitial(FileCrawlingVisitor::new);
    private static ConcurrentLinkedQueue<File> sharedFileList = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Directory tree1 = createTree("Drive1");
        Directory tree2 = createTree("Drive2");
        Directory tree3 = createTree("Drive3");

        Thread thread1 = new Thread(() -> crawlFileSystem(tree1));
        Thread thread2 = new Thread(() -> crawlFileSystem(tree2));
        Thread thread3 = new Thread(() -> crawlFileSystem(tree3));

        thread1.start();
        thread2.start();
        thread3.start();

        
        Thread.sleep(5000);
        keepRunning.set(false);

        
        thread1.join();
        thread2.join();
        thread3.join();

       
        System.out.println("Shared File List: " + sharedFileList);
    }

    private static void crawlFileSystem(Directory root) {
        FileCrawlingVisitor visitor = threadLocalVisitor.get();

       
        while (keepRunning.get()) {
            
            root.accept(visitor);
            break; 
        }

        sharedFileList.addAll(visitor.getFiles());
        }
    

    private static Directory createTree(String name) {
        Directory root = new Directory(null, name, 0, LocalDateTime.now());

        for (int i = 1; i <= 3; i++) {
            Directory subDir = new Directory(root, "SubDir" + i, 0, LocalDateTime.now());
            File file = new File(subDir, "File" + i, i * 10, LocalDateTime.now());
            Link link = new Link(subDir, "LinkTo" + file.getName(), 0, LocalDateTime.now(), file);

            root.appendChild(subDir);
            subDir.appendChild(file);
            subDir.appendChild(link);
        }
        return root;
    }
}