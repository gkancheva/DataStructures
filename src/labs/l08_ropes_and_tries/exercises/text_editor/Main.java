package labs.l08_ropes_and_tries.exercises.text_editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TextEditor editor = new TextEditorImpl();

        String line = br.readLine();

        while(!line.equals("end")) {
            try {
            String[] params = line.trim().split(" ");

            switch (params[0]) {
                case "login":
                    editor.login(params[1]);
                    break;
                case "logout":
                    editor.logout(params[1]);
                    break;
                case "users":
                    List<String> users = (List<String>) editor.users(params.length == 1 ? null : params[1]);
                    for (String user : users) {
                        System.out.println(user);
                    }
                    line = br.readLine();
                    continue;
            }

            switch (params[1]) {
                case "insert":
                    String strValue = line.split("\"")[1];
                    int atIndex = Integer.parseInt(params[2]);
                    editor.insert(params[0], atIndex, strValue);
                    break;
                case "prepend":
                    strValue = line.split("\"")[1];
                    editor.prepend(params[0], strValue);
                    break;
                case "substring":
                    atIndex = Integer.parseInt(params[2]);
                    int length = Integer.parseInt(params[3]);
                    editor.substring(params[0], atIndex, length);
                    break;
                case "delete":
                    int index = Integer.parseInt(params[2]);
                    int count = Integer.parseInt(params[3]);
                    editor.delete(params[0], index, count);
                    break;
                case "clear":
                    editor.clear(params[0]);
                    break;
                case "length":
                    System.out.println(editor.length(params[0]));
                    break;
                case "print":
                    String output = editor.print(params[0]);
                    if(!output.isEmpty()) {
                        System.out.println(output);
                    }
                    break;
                case "undo":
                    editor.undo(params[0]);
                    break;
                default:
                    break;
            }
            line = br.readLine();
            } catch (Exception e) {
                line = br.readLine();
            }

        }
    }
}