package labs.l02_linear_ds_stacks_and_queues.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class p01_reverseNumbersWithStack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        if(input.trim().isEmpty()) {
            return;
        }
        String[] arr = input.trim().split(" ");
        List<Integer> numbers = Arrays.stream(arr)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Stack stack = new Stack();
        for (int i = 0; i < numbers.size(); i++) {
            stack.push(numbers.get(i));
        }

        while(!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }

    }
}