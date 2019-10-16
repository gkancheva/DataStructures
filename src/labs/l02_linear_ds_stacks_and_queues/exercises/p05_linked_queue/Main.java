package labs.l02_linear_ds_stacks_and_queues.exercises.p05_linked_queue;

public class Main {
    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue();
        queue.enqueue(2);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}