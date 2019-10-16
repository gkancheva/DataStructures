package labs.l02_linear_ds_stacks_and_queues.exercises.p04_linked_stack;

import java.lang.reflect.Array;

public class LinkedStack<E> {

    private Node<E> firstNode;
    private int size;


    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        Node<E> node = new Node<>(element);
        node.nextNode = firstNode;
        firstNode = node;
        size++;
    }

    public E pop() {
        if(size == 0) {
            throw new UnsupportedOperationException();
        }
        E element = this.firstNode.value;
        this.firstNode = this.firstNode.nextNode;
        size--;
        return element;
    }

    public E[] toArray() {
        E e = this.firstNode.value;
        E[] result = (E[]) Array.newInstance(e.getClass(), this.size);
        Node<E> node = this.firstNode;
        int index = 0;
        do {
            result[index++] = node.value;
            node = node.nextNode;
        } while(node != null);

        return result;
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.nextNode = nextNode;
            this.value = value;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}