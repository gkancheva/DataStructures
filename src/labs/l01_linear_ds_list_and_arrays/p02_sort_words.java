package labs.l01_linear_ds_list_and_arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p02_sort_words {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> words = Arrays.asList(reader.readLine().split(" "));

        words = words.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i));
            if(i != words.size() -1) {
                System.out.print(" ");
            }
        }


    }
}