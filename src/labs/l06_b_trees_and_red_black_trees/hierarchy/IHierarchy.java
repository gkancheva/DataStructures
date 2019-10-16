package labs.l06_b_trees_and_red_black_trees.hierarchy;

public interface IHierarchy<T> extends Iterable<T> {

    int getCount();

    void add(T element, T child);

    void remove(T element);

    Iterable<T> getChildren(T element);

    T getParent(T element);

    boolean contains(T element);

    Iterable<T> getCommonElements(IHierarchy<T> other);

    int GetCount();

    void Add(T element, T child);

    void Remove(T element);

    Iterable<T> GetChildren(T element);

    T GetParent(T element);

    boolean Contains(T element);

    Iterable<T> GetCommonElements(IHierarchy<T> other);
}
