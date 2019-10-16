package labs.l02_linear_ds_stacks_and_queues.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class p02_calculateSequence {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());

        int count = 50;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(number);
        List<Integer> list = new ArrayList<>();
        list.add(number);
        while ((list.size() < count)){
                int firstItem = queue.peek() + 1;
            queue.add(firstItem);
            int secondItem = (queue.peek() * 2) + 1;
            queue.add(secondItem);
            int thirdItem = firstItem + 1;
            queue.add(thirdItem);

            queue.poll();
            list.add(firstItem);
            list.add(secondItem);
            list.add(thirdItem);
        }

        for (int i = 0; i < 49; i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.print(list.get(49));

    }
}