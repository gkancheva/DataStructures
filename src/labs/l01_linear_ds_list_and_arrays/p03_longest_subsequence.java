package labs.l01_linear_ds_list_and_arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p03_longest_subsequence {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = Arrays.asList(br.readLine().split(" "));
        List<Integer> nums = input.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int maxSubsequence = nums.get(0);
        int max = 1;
        for (int i = 0; i < nums.size(); i++) {
            int currentMax = 1;
            for (int j = i; j < nums.size() - 1; j++) {
                if(nums.get(j) == nums.get(j + 1)) {
                    currentMax++;
                } else {
                    break;
                }
            }
            if(currentMax > max) {
                max = currentMax;
                maxSubsequence = nums.get(i);
            }
        }

        for (int i = 0; i < max - 1; i++) {
            System.out.print(maxSubsequence + " ");
        }
        System.out.print(maxSubsequence);
    }
}