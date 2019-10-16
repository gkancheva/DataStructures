package labs.l10_hash_tables_sets_maps.count_symbols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> symbolsMap = new TreeMap<>();

        String line = br.readLine();
        for (int i = 0; i < line.length(); i++) {
            char letter = line.charAt(i);
            if(!symbolsMap.containsKey(letter)) {
                symbolsMap.put(letter, 0);
            }
            int currentCunt = symbolsMap.get(letter) + 1;
            symbolsMap.put(letter, currentCunt);
        }

        for (Map.Entry<Character, Integer> entry : symbolsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " time/s");
        }
    }
}