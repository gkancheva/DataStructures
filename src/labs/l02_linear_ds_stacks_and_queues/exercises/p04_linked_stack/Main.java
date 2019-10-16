package labs.l02_linear_ds_stacks_and_queues.exercises.p04_linked_stack;

public class Main {
    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack();

        stack.push(5);
        stack.push(13);

        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

//        stack.push(5);
//        stack.push(13);

        Integer[] arr = stack.toArray();
        System.out.println(arr.length);
    }
}