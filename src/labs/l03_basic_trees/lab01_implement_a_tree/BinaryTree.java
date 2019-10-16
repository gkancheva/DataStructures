package labs.l03_basic_trees.lab01_implement_a_tree;

import java.util.function.Consumer;

public class BinaryTree<T> {

    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;


    public BinaryTree(T value) {
        this(value, null, null);
    }

    public BinaryTree(T value, BinaryTree<T> child) {

    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        String output = new String(new char[indent]).replace("\0", " ");
        builder.append(output).append(output).append(this.value).append("\n");
        if(this.leftChild != null) {
            this.leftChild.printIndentedPreOrder(indent + 1, builder);
        }
        if(this.rightChild != null){
            this.rightChild.printIndentedPreOrder(indent + 1, builder);
        }
        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {
        if(this.leftChild != null) {
            this.leftChild.eachInOrder(consumer);
        }
        consumer.accept(this.value);
        if(this.rightChild != null) {
            this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        if(this.leftChild != null) {
            this.leftChild.eachPostOrder(consumer);
        }
        if(this.rightChild != null) {
            this.rightChild.eachPostOrder(consumer);
        }
        consumer.accept(this.value);
    }
}
