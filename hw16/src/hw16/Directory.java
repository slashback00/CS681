package hw16;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Directory extends FSElement {
    private Directory parent;
    private ConcurrentLinkedQueue<FSElement> FSlist = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Directory> DIRList = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<File> Filelist = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Link> ProxyList = new ConcurrentLinkedQueue<>();

    public Directory(Directory parent, String name, int size, LocalDateTime ldt) {
        super(parent, name, size, ldt);
        this.parent = parent;
    }

    public ConcurrentLinkedQueue<FSElement> getChildren() {
        return this.FSlist;
    }

    public void appendChild(FSElement child) {
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
    }

    public int countChildren() {
        return FSlist.size();
    }

    public ConcurrentLinkedQueue<Directory> getSubDirectories() {
        return this.DIRList;
    }

    public ConcurrentLinkedQueue<File> getFiles() {
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

    public void accept(FSVisitor visitor) {
        visitor.visit(this);
        for (FSElement child : FSlist) {
            child.accept(visitor);
        }
    }
}
