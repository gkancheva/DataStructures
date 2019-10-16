package labs.l03_basic_trees.p01_root_node;

import java.util.ArrayList;
import java.util.List;

public class Tree<T extends Comparable<T>> {

    private T value;
    private Tree<T> parent;
    private List<Tree<T>> children;

    public Tree() {
    }

    public Tree(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Tree(T value, List<Tree<T>> children) {
        this.value = value;
        this.children = children;
    }

    public T getValue() {
        return value;
    }

    public Tree<T> getParent() {
        return parent;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public void insert(T value, T key) {
        // TODO: 12/26/2018
    }

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        for (Tree<T> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Tree)) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        return this.getValue() == ((Tree)obj).getValue();
    }
}