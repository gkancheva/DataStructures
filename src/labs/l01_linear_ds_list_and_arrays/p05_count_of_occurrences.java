package labs.l01_linear_ds_list_and_arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class p05_count_of_occurrences {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> input = Arrays.asList(br.readLine().split(" "));
        List<Integer> nums = input.stream().map(Integer::parseInt).collect(Collectors.toList());

        Map<Integer, Integer> countOfOccurences = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int currentNum = nums.get(i);
            if(!countOfOccurences.containsKey(currentNum)) {
                countOfOccurences.put(currentNum, 0);
            }
            countOfOccurences.put(currentNum, countOfOccurences.get(currentNum) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countOfOccurences.entrySet()) {
            System.out.println(String.format("%d -> %d times", entry.getKey(), entry.getValue()));
        }
    }
}