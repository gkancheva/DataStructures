package labs.l06_b_trees_and_red_black_trees.lab;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class RedBlackTree<T extends Comparable<T>> {
    private Node root;
    private int nodesCount;

    public RedBlackTree(){
    }

    private RedBlackTree(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNodesCount() {
        return this.nodesCount;
    }

    public void insert(T value) {
        this.nodesCount++;
        if(this.root == null) {
            this.root = new Node(value);
            this.root.isRed = false;
        } else {
            this.root = this.insert(value, this.root, null);
        }
    }

    public Node insert(T value, Node node, Node parent) {
        if(node == null) {
            node = new Node(value);
        } else if(value.compareTo(node.value) < 0) {
            node = this.insert(value, node.left, node);
        } else if(value.compareTo(node.value) > 0) {
            node = this.insert(value, node.right, node);
        }

        if(parent == null) {
            return node;
        }
        if(parent.value.compareTo(node.value) < 0) {
            parent.right = node;
        } else if(parent.value.compareTo(node.value) > 0) {
            parent.left = node;
        }

        if(this.isRed(parent.right) && !this.isRed(parent.left)) {
            parent = this.leftRotation(parent);
        }

        if (this.isRed(parent.left) && this.isRed(parent.left.left) && !this.isRed(parent.right)) {
            parent = this.rightRotation(parent);
        }

        if(this.isRed(parent.left) && this.isRed(parent.right)) {
            parent = this.flipColors(parent);
        }

        return parent;
    }

    private Node rightRotation(Node node) {
        node.isRed = true;
        Node temp = node.left;
        temp.isRed = false;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    private Node flipColors(Node node) {
        node.isRed = true;
        node.left.isRed = false;
        node.right.isRed = false;
        return node;
    }

    private Node leftRotation(Node node) {
        node.isRed = true;
        Node temp = node.right;
        temp.isRed = false;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }

    private boolean isRed(Node node) {
        if(node == null) {
            return false;
        }
        return node.isRed;
    }

    public boolean contains(T value) {
        Node current = this.root;
        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public RedBlackTree<T> search(T item) {
        Node current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return new RedBlackTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
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

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node min = this.root;
        Node parent = null;

        while (min.left != null) {
            parent = min;
            parent.childrenCount--;
            min = min.left;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = min.right;
        }

        this.nodesCount--;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node max = this.root;
        Node parent = null;

        while (max.right != null) {
            parent = max;
            max = max.right;
        }

        if (parent == null) {
            this.root = this.root.left;
        } else {
            parent.right = max.left;
        }
    }

    public T ceil(T element) {
        return this.select(this.rank(element) + 1);
    }

    public T floor(T element) {
        return this.select(this.rank(element) - 1);
    }

    public void delete(T key) {
        this.root = deleteRecursive(this.root, key);
    }

    private Node deleteRecursive(Node root, T key) {
        if (root == null) {
            return root;
        }

        if (key.compareTo(root.value) < 0) {
            root.left = deleteRecursive(root.left, key);
        }
        else if (key.compareTo(root.value) > 0) {
            root.right = deleteRecursive(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value = minValue(root.right);

            root.right = deleteRecursive(root.right, root.value);
        }

        return root;
    }

    public int rank(T element) {
        return this.rank(element, this.root);
    }

    private int rank(T element, Node node) {
        if (node == null) {
            return 0;
        }

        int compare = element.compareTo(node.value);

        if (compare < 0) {
            return this.rank(element, node.left);
        }

        if (compare > 0) {
            return 1 + this.size(node.left) + this.rank(element, node.right);
        }

        return this.size(node.left);
    }

    public T select(int rank) {
        Node node = this.select(rank, this.root);
        if (node == null) {
            throw new IllegalArgumentException("ERROR");
        }

        return node.value;
    }

    private Node select(int rank, Node node) {
        if (node == null) {
            return null;
        }

        int leftCount = this.size(node.left);
        if (leftCount == rank) {
            return node;
        }

        if (leftCount > rank) {
            return this.select(rank, node.left);
        } else {
            return this.select(rank - (leftCount + 1), node.right);
        }
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        return node.childrenCount;
    }

    class Node {
        private T value;
        private Node left;
        private Node right;
        private boolean isRed = true;

        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
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

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}

//    public T ceil(T element) {
//        return ceil(this.root, element);
//    }
//
//    private T ceil(Node node, T input) {
//        if (node == null) {
//            return null;
//        }
//
//        if (node.value == input) {
//            return node.value;
//        }
//
//        if (node.value.compareTo(input) < 0) {
//            return ceil(node.right, input);
//        }
//
//        T ceil = ceil(node.left, input);
//        return (ceil != null) ? ceil : node.value;
//    }
//
//    public T floor(T element) {
//        return floor(this.root, element);
//    }
//
//    private T floor(Node node, T input) {
//        if (node == null) {
//            return null;
//        }
//
//        if (node.value == input) {
//            return node.value;
//        }
//
//        if (node.value.compareTo(input) > 0) {
//            return floor(node.left, input);
//        }
//
//        T floor = floor(node.right, input);
//        return (floor != null) ? floor : node.value;
//    }


