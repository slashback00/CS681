package hw11.util;

import java.util.LinkedList;

import hw11.Directory;
import hw11.FSVisitor;
import hw11.File;
import hw11.Link;

public class FileCrawlingVisitor implements FSVisitor
{
    private LinkedList<File> files = new LinkedList<>();
    
    
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

    public LinkedList<File> getFiles()
    {
        return files;
    }
    

    
}
