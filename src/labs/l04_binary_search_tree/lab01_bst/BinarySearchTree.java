package labs.l04_binary_search_tree.lab01_bst;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;


    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Node node) {
        this.copy(node);
    }

    private void copy(Node node) {
        if(node == null) {
            return;
        }
        this.insert(node.value);
        this.copy(node.left);
        this.copy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    //iterative
    public void insert(T value) {
        if(this.root == null) {
            this.root = new Node(value);
            return;
        }
        Node parent = null;
        Node current = this.root;
        while(current != null) {
            int compare = current.value.compareTo(value);
            if(compare > 0) {
                //current.value > value
                parent = current;
                current = current.left;
            } else if(compare < 0) {
                //current.value < value
                parent = current;
                current = current.right;
            } else {
                return;
            }
        }
        Node newNode = new Node(value);
        if(parent.value.compareTo(value) > 0){
            //parent.value > value
            parent.left = newNode;
        } else if(parent.value.compareTo(value) < 0) {
            //parent.value < value
            parent.right = newNode;
        }
    }

    public Node insert(Node node, T value) {
        if(node == null) {
            return new Node(value);
        }
        int compare = node.value.compareTo(value);
        if(compare > 0) {
            node.left = this.insert(node.left, value);
        } else {
            node.right = this.insert(node.right, value);
        }
        return node;
    }

    public boolean contains(T value) {
        Node current = this.root;
        while(current != null) {
            int compare = current.value.compareTo(value);
            if(compare > 0) {
                //curremt.value > value
                current = current.left;
            } else if(compare < 0) {
                //curremt.value > value
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;
        while(current != null) {
            if(current.value.compareTo(item) > 0) {
                current = current.left;
            } else if(current.value.compareTo(item) < 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer){
        if(node == null){
            return;
        }
        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T start, T end) {
        if(node == null) {
            return;
        }
        int nodeInLowerRange = start.compareTo(node.value);
        int nodeInHigherRage = end.compareTo(node.value);
        if(nodeInLowerRange < 0) {
            this.range(node.left, queue, start, end);
        }
        if(nodeInLowerRange <= 0 && nodeInHigherRage >= 0) {
            queue.add(node.value);
        }
        if(nodeInHigherRage > 0) {
            this.range(node.right, queue, start, end);
        }
    }

    public void deleteMin() {
        if(this.root == null) {
            return;
        }
        Node parent = null;
        Node min = this.root;
        while(min.left != null) {
            parent = min;
            min = parent.left;
        }
        if(parent == null) {
            this.root = min.right;
        } else {
            parent.left = min.right;
        }
    }


    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}


