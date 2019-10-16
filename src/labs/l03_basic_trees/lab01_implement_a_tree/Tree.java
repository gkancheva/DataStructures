package labs.l03_basic_trees.lab01_implement_a_tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private T value;
    private List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        for (int i = 0; i < children.length; i++) {
            this.children.add(children[i]);
        }
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {
        String output = new String(new char[indent]).replace("\0", " ");
        builder.append(output).append(output).append(this.value).append("\n");
        for (Tree<T> child : children) {
            child.print(indent + 1, builder);
        }
        return builder.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> result = new ArrayList<>();
        this.dfs(this, result);
        return result;
    }

    private void dfs(Tree<T> tree, List<T> result) {
        for (Tree<T> child : tree.children) {
            this.dfs(child, result);
        }
        result.add(tree.value);
    }

    public Iterable<T> orderBFS() {
        List<T> result = new ArrayList<>();
        Deque<Tree<T>> queue = new LinkedList<>();
        queue.add(this);
        while(queue.size() > 0) {
            Tree<T> currentTree = queue.pop();
            result.add(currentTree.value);
            for (Tree<T> child : currentTree.children) {
                queue.add(child);
            }
        }
        return result;
    }

}