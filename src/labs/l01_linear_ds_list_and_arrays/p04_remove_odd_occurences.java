package labs.l01_linear_ds_list_and_arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p04_remove_odd_occurences {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> input = Arrays.asList(br.readLine().split(" "));
        List<Integer> nums = input.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();

        for (int i = 0; i < nums.size(); i++) {
            int currentNum = nums.get(i);
            int count = 0;
            for (int j = 0; j < nums.size(); j++) {
                if(currentNum == nums.get(j)) {
                    count++;
                }
            }
            if(count % 2 == 0) {
                result.add(currentNum + "");
            }
        }

        System.out.println(String.join(" ", result));

    }
}