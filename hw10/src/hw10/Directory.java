package hw10;

import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

public class Directory extends FSElement {
    Directory parent;

    CopyOnWriteArrayList<FSElement> FSlist = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Directory> DIRList = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<File> Filelist = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Link> ProxyList = new CopyOnWriteArrayList<>();

    public Directory(Directory parent, String name, int size, LocalDateTime ldt) {
        super(parent, name, size, ldt);
        this.parent = parent;
    }

    public CopyOnWriteArrayList<FSElement> getChildren() {
        return this.FSlist;
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
            FSlist.add(child);

            if (child.isDirectory()) {
                DIRList.add((Directory) child);
            } else {
                if (child.isProxy()) {
                    ProxyList.add((Link) child);
                } else {
                    Filelist.add((File) child);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        return FSlist.size();
    }

    public CopyOnWriteArrayList<Directory> getSubDirectories() {
        return this.DIRList;
    }

    public CopyOnWriteArrayList<File> getFiles() {
        return this.Filelist;
    }

    public int getTotalSize() {
        int totalSize = 0;
        for (Directory childDirectory : DIRList) {
            totalSize += childDirectory.getTotalSize();
        }
        for (File f : Filelist) {
            totalSize += f.getSize();
        }
        return totalSize;
    }

    public Directory getParentDirectory() {
        return this.parent;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isProxy() {
        return false;
    }
}
