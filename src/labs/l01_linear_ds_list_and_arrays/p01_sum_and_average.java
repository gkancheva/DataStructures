package labs.l01_linear_ds_list_and_arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p01_sum_and_average {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        if(input == null || input.trim().isEmpty()) {
            System.out.println("Sum=0; Average=0.00");
            return;
        }

        List<Integer> list = Arrays.stream(input.split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        System.out.println(String.format("Sum=%d; Average=%.2f", sum, (double)sum / list.size()));

    }
}