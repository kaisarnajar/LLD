package FileSystem.Node;

import java.time.LocalDateTime;
import java.util.*;

public abstract class FileSystemNode {
    protected String name;
    private Map<String, FileSystemNode> children;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public FileSystemNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void addChild(String name, FileSystemNode fileSystemNode) {
        children.put(name, fileSystemNode);
        modifiedAt = LocalDateTime.now();
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    public FileSystemNode getChild(String name) {
        return children.get(name);
    }

    public boolean removeChild(String name) {
        if (hasChild(name)) {
            children.remove(name);
            return true;
        }
        return false;
    }

    public abstract boolean isFile();

    public abstract void display(int depth);

    public String getName() {
        return name;
    }

    public Collection<FileSystemNode> getChildren() {
        return children.values();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    // Update the modification timestamp
    protected void updateModifiedTime() {
        this.modifiedAt = LocalDateTime.now();
    }
}
