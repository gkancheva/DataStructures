package labs.l10_hash_tables_sets_maps.phonebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Contact> phoneBook = new HashMap<>();

        String line = br.readLine();

        while (!line.equals("search")) {
            String[] params = line.split("-");
            Contact contact = new Contact(params[0], params[1]);
            phoneBook.put(params[0], contact);
            line = br.readLine();
        }

        line = br.readLine();
        while (!line.equals("end")){
            if(!phoneBook.containsKey(line)){
                System.out.println(String.format("Contact %s does not exist.", line));
            } else {
                System.out.println(phoneBook.get(line));
            }
            line = br.readLine();
        }

    }

    static class Contact {
        private String name;
        private String phoneNumber;

        Contact(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return this.name + " -> " + this.phoneNumber;
        }
    }
}