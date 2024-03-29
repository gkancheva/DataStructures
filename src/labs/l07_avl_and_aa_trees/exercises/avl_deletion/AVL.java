package labs.l07_avl_and_aa_trees.exercises.avl_deletion;

import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private Node<T> insert(Node<T> node, T item) {
        if (node == null) {
            return new Node<>(item);
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            node.left = this.insert(node.left, item);
        } else if (cmp > 0) {
            node.right = this.insert(node.right, item);
        }

        return node;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            return search(node.left, item);
        } else if (cmp > 0) {
            return search(node.right, item);
        }

        return node;
    }

    public void delete(T item) {
        this.root = delete(this.root, item);
    }

    private Node<T> delete(Node<T> node, T item) {
        if(node == null) {
            return null;
        }
        int compare = item.compareTo(node.value);
        if(compare < 0) {
            node.left = delete(node.left, item);
        } else if(compare > 0) {
            node.right = delete(node.right, item);
        } else {
            if(node.left == null) {
                return node.right;
            } else if(node.right == null) {
                return node.left;
            } else {
                Node<T> minNode = this.findMinNode(node.right);
                minNode.right = this.deleteMin(node.right);
                minNode.left = node.left;
                node = minNode;
            }
        }
        node = this.balance(node);
        this.updateHeight(node);
        return node;
    }

    private Node<T> findMinNode(Node<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return this.findMinNode(node.left);
    }

    public void deleteMin() {
        this.deleteMin(this.root);
    }

    private Node<T> deleteMin(Node<T> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node = this.balance(node);
        this.updateHeight(node);
        return node;
    }

    // BONUS
    public void deleteMax() {
        // TODO: 1/24/2019
    }

    private Node<T> balance(Node<T> node) {
        int balance = height(node.left) - height(node.right);
        if (balance > 1) {
            int childBalance = height(node.left.left) - height(node.left.right);
            if (childBalance < 0) {
                node.left = RotateLeft(node.left);
            }

            node = RotateRight(node);
        } else if (balance < -1) {
            int childBalance = height(node.right.left) - height(node.right.right);
            if (childBalance > 0) {
                node.right = RotateRight(node.right);
            }

            node = RotateLeft(node);
        }

        return node;
    }

    private Node<T> RotateRight(Node<T> node) {
        Node<T> left = node.left;
        node.left = left.right;
        left.right = node;

        updateHeight(node);

        return left;
    }

    private Node<T> RotateLeft(Node<T> node) {
        Node<T> right = node.right;
        node.right = right.left;
        right.left = node;

        updateHeight(node);

        return right;
    }

    private void updateHeight(Node<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
}
