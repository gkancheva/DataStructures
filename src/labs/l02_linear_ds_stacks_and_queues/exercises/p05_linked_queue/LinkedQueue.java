package labs.l02_linear_ds_stacks_and_queues.exercises.p05_linked_queue;

public class LinkedQueue<E> {

    private int size;
    private QueueNode<E> head;
    private QueueNode<E> tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        QueueNode oldTail = this.tail;
        this.tail = new QueueNode(element);
        this.tail.prevNode = oldTail;
        if(this.size == 0) {
            this.head = this.tail;
        } else {
            oldTail.nextNode = this.tail;
        }
        this.size++;
    }

    public E dequeue() {
        if(this.size <= 0) {
            throw new IllegalArgumentException();
        }
        E value = this.head.value;
        this.head = this.head.nextNode;
        if(this.head != null) {
            this.head.prevNode = null;
        } else {
            this.tail = null;
        }
        this.size--;
        return value;
    }

    public E[] toArray() {
        E[] array = (E[]) new Object[this.size];
        int index = 0;
        QueueNode<E> currentNode = this.head;
        while (currentNode != null) {
            array[index++] = currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return array;
    }

    private class QueueNode<E> {
        private E value;

        private QueueNode<E> nextNode;
        private QueueNode<E> prevNode;

        public QueueNode(E value) {
            this.value = value;
        }
    }
}