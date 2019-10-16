package labs.l02_linear_ds_stacks_and_queues.exercises;

import java.util.*;

public class p06_sequenceNtoM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        if(m < n) {
            return;
        }

        Deque<Element> queue = new ArrayDeque<>();
        queue.addFirst(new Element(n, null));
        Deque<Integer> stack = new ArrayDeque<>();

        while (true) {
            Element currentElement = queue.removeFirst();
            if (currentElement.value == m) {
                while (currentElement.prevElement != null) {
                    stack.push(currentElement.prevElement.value);
                    currentElement = currentElement.prevElement;
                }
                while(!stack.isEmpty()) {
                    System.out.print(stack.removeFirst() + " -> ");
                }
                System.out.println(m);
                return;
            }

            queue.addLast(new Element(currentElement.value + 1, currentElement));
            queue.addLast(new Element(currentElement.value + 2, currentElement));
            queue.addLast(new Element(currentElement.value * 2, currentElement));
        }
    }

    private static class Element {
        private int value;
        private Element prevElement;

        public Element() {

        }

        public Element(int value, Element prevElement) {
            this.value = value;
            this.prevElement = prevElement;
        }
    }
}