package hw16.util;

import java.util.concurrent.ConcurrentLinkedQueue;

import hw16.Directory;
import hw16.FSVisitor;
import hw16.File;
import hw16.Link;

public class FileCrawlingVisitor implements FSVisitor
{
    private ConcurrentLinkedQueue<File> files = new ConcurrentLinkedQueue<>();
    
    
    public void visit(Link link) 
    {
        return;
    }

    public void visit(Directory dir) 
    {
        return;
    }

    public void visit(File file) 
    {
        files.add(file);
    }

    public ConcurrentLinkedQueue<File> getFiles()
    {
        return files;
    }
    

    
}
