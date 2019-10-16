package labs.l08_ropes_and_tries.exercises.text_editor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TextEditorImpl implements TextEditor {

    private Trie<Deque<StringBuilder>> userInput;
    private Map<String, Boolean> loggedUsers;

    public TextEditorImpl() {
        this.userInput = new Trie<>();
        this.loggedUsers = new LinkedHashMap<>();
    }

    public void login(String username){
        if(isLoggedIn(username)) {
            this.loggedUsers.remove(username);
        }
        this.loggedUsers.put(username,true);
        Deque<StringBuilder> newDeck = new ArrayDeque<>();
        newDeck.push(new StringBuilder(""));
        this.userInput.insert(username, newDeck);
    }

    public void logout(String username){
        Deque<StringBuilder> deque = new ArrayDeque<>();
        deque.push(new StringBuilder());
        this.userInput.insert(username, deque);
        this.loggedUsers.remove(username);
    }

    public void prepend(String username, String string){
        this.insert(username,0,string);
    }

    public void insert(String username, int index, String string){
        if (isLoggedIn(username)){
            Deque<StringBuilder> temp = this.userInput.getValue(username);
            StringBuilder value = temp.isEmpty() ? new StringBuilder() : temp.peek();
            StringBuilder newValue = new StringBuilder(value);
            newValue.insert(index, string);
            temp.push(newValue);
            this.userInput.insert(username, temp);
        }
    }

    public void substring(String username, int startIndex, int length){
        if (isLoggedIn(username)){
            Deque<StringBuilder> temp = this.userInput.getValue(username);
            StringBuilder value = new StringBuilder(temp.peek().substring(startIndex, startIndex + length));
            temp.push(value);
            this.userInput.insert(username, temp);
        }
    }

    public void delete(String username, int startIndex, int length){
        if (isLoggedIn(username)){
            Deque<StringBuilder> temp = this.userInput.getValue(username);
            StringBuilder value = new StringBuilder(temp.peek());
            value = value.delete(startIndex, startIndex + length);
            temp.push(value);
            userInput.insert(username, temp);
        }
    }

    public void clear(String username){
        if (isLoggedIn(username)){
            Deque<StringBuilder> temp = this.userInput.getValue(username);
            temp.push(new StringBuilder(""));
            userInput.insert(username, temp);
        }
    }

    public int length(String username){
        if (isLoggedIn(username)){
            return this.userInput.getValue(username).peek().length();
        }
        return 0;
    }

    public String print(String username){
        if (isLoggedIn(username)){
            return this.userInput.getValue(username).peek().toString();
        }
        return "";
    }

    public void undo(String username){
        if (isLoggedIn(username)){
            this.userInput.getValue(username).pop();
        }
    }

    public Iterable<String> users(String prefix){
        List<String> output = new LinkedList<>();

        if (prefix == null) {
            for(String user : loggedUsers.keySet()){
                if (loggedUsers.get(user)){
                    output.add(user);
                }
            }
            return output;
        }

        List<String> usersByPrefix = StreamSupport.stream(userInput.getByPrefix(prefix).spliterator(), false).collect(Collectors.toList());
        for(String user : loggedUsers.keySet()){
            if (loggedUsers.get(user) && usersByPrefix.contains(user)){
                output.add(user);
            }
        }

        return output;
    }

    private boolean isLoggedIn(String username) {
        return this.loggedUsers.containsKey(username);
    }
}