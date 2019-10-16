package labs.l06_b_trees_and_red_black_trees.hierarchy;

import java.util.*;
import java.util.stream.Collectors;

public class Hierarchy<T> implements IHierarchy<T> {

    private Node root;
    private Map<T, Node> nodesByValue;

    public Hierarchy(T element){
        this.root = new Node(element, null);
        this.nodesByValue = new LinkedHashMap<>();
        this.nodesByValue.put(element, this.root);
    }

    public void add(T parent, T child){
        if(!this.nodesByValue.containsKey(parent)) {
            throw new IllegalArgumentException();
        }
        if(this.nodesByValue.containsKey(child)) {
            throw new IllegalArgumentException();
        }
        Node parentNode = this.nodesByValue.get(parent);
        Node childNode = new Node(child, parentNode);
        this.nodesByValue.put(child, childNode);
        parentNode.children.add(childNode);
    }

    public int getCount() {
        return this.nodesByValue.size();
    }

    public void remove(T element){
        if(!this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException();
        }
        if(this.root.value == element) {
            throw new IllegalStateException();
        }
        Node nodeToRemove = this.nodesByValue.get(element);
        List<Node> children = nodeToRemove.children;
        Node parent = nodeToRemove.parent;
        parent.children.remove(nodeToRemove);
        for (Node child : children) {
            child.parent = parent;
            parent.children.add(child);
        }
        this.nodesByValue.remove(element);
    }

    public boolean contains(T element){
        return this.nodesByValue.containsKey(element);
    }

    public T getParent(T element){
        if(!this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException();
        }
        return this.nodesByValue.get(element) == this.root ? null
                : this.nodesByValue.get(element).parent.value;
    }

    public Iterable<T> getChildren(T element){
        if(!this.nodesByValue.containsKey(element)) {
            throw new IllegalArgumentException();
        }
        Node parent = this.nodesByValue.get(element);
        return parent.children.stream().map(x -> x.value)
                .collect(Collectors.toList());
    }

    public Iterable<T> getCommonElements(IHierarchy<T> other){
        List<T> list = new ArrayList<>();
        for (T value : this.nodesByValue.keySet()) {
            if(other.Contains(value)) {
                list.add(value);
            }
        }
        return list;
    }

    @Override
    public int GetCount() {
        return this.getCount();
    }

    @Override
    public void Add(T element, T child) {
        this.add(element, child);
    }

    @Override
    public void Remove(T element) {
        this.remove(element);
    }

    @Override
    public Iterable<T> GetChildren(T element) {
        return this.getChildren(element);
    }

    @Override
    public T GetParent(T element) {
        return this.getParent(element);
    }

    @Override
    public boolean Contains(T element) {
        return this.contains(element);
    }

    @Override
    public Iterable<T> GetCommonElements(IHierarchy<T> other) {
        return this.getCommonElements(other);
    }

    @Override
    public Iterator<T> iterator() {
       List<T> elements = new ArrayList<>();
       Deque<T> queue = new LinkedList<>();
       queue.push(this.root.value);
       while(!queue.isEmpty()) {
           T currentElement = queue.pollFirst();
           elements.add(currentElement);
           List<T> childrenValues = this.nodesByValue.get(currentElement)
                   .children.stream()
                   .map(x -> x.value).collect(Collectors.toList());
           queue.addAll(childrenValues);
       }
       return elements.iterator();
    }

    private class Node {

        public T value;
        public Node parent;
        public List<Node> children;

        public Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.children = new ArrayList<>();
        }
    }
}
