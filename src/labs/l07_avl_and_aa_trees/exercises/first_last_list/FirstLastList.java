package labs.l07_avl_and_aa_trees.exercises.first_last_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {

    private ArrayList<T> elements;
    private Map<T, List<T>> map;

    public FirstLastList() {
        this.elements = new ArrayList<>();
        this.map = new TreeMap<>();
    }

    @Override
    public void add(T element) {
        this.elements.add(element);
        this.map.putIfAbsent(element, new ArrayList<>());
        this.map.get(element).add(element);
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        if(count > this.elements.size() || count < 0) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        int numberOfElements = Math.min(count, this.elements.size());
        for (int i = 0; i < numberOfElements; i++) {
            result.add(this.elements.get(i));
        }
        return result;
    }

    @Override
    public Iterable<T> last(int count) {
        if(count > this.elements.size() || count < 0) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        int start = this.elements.size() - count;
        for (int i = start; i < this.elements.size(); i++) {
            result.add(0, this.elements.get(i));
        }
        return result;
    }

    @Override
    public Iterable<T> min(int count) {
        if(count > this.elements.size() || count < 0) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        this.map.entrySet().stream()
                .sorted((x, y) -> x.getKey().compareTo(y.getKey()))
                .forEach(key -> result.addAll(key.getValue()));
        return result.stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<T> max(int count) {
        if(count > this.elements.size() || count < 0) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        this.map.entrySet().stream()
                .sorted((x, y) -> y.getKey().compareTo(x.getKey()))
                .forEach(key -> result.addAll(key.getValue()));
        return result.stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        this.elements.clear();
        this.map = new TreeMap<>();
    }

    @Override
    public int removeAll(T element) {
        int count = 0;
        for (int i = 0; i < this.elements.size(); i++) {
            if(this.elements.get(i).compareTo(element) == 0) {
                this.elements.remove(i);
                i--;
                count++;
            }
        }
        this.map.remove(element);
        return count;
    }
}