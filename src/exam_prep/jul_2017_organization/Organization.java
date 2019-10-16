package exam_prep.jul_2017_organization;

import java.util.*;

public class Organization implements IOrganization {

    private List<Person> employees;
    private Map<String, Set<Person>> byName;
    private TreeMap<Integer, Set<Person>> byNameLength;

    public Organization() {
        this.employees = new ArrayList<>();
        this.byName = new HashMap<>();
        this.byNameLength = new TreeMap<>();
    }

    @Override
    public int getCount() {
        return this.employees.size();
    }

    @Override
    public boolean contains(Person person) {
        if(!this.byName.containsKey(person.getName())) {
            return false;
        };
        return this.byName.get(person.getName()).contains(person);
    }

    @Override
    public boolean containsByName(String name) {
        return this.byName.containsKey(name);
    }

    @Override
    public void add(Person person) {
        this.employees.add(person);
        this.byName.putIfAbsent(person.getName(), new LinkedHashSet<>());
        this.byName.get(person.getName()).add(person);
        this.byNameLength.putIfAbsent(person.getName().length(), new LinkedHashSet<>());
        this.byNameLength.get(person.getName().length()).add(person);
    }

    @Override
    public Person getAtIndex(int index) {
        if(index < 0 || index > this.getCount()) {
            throw new IllegalArgumentException();
        }
        return this.employees.get(index);
    }

    @Override
    public Iterable<Person> getByName(String name) {
        if(!this.byName.containsKey(name)) {
            return new ArrayList<>();
        }
        return this.byName.get(name);
    }

    @Override
    public Iterable<Person> firstByInsertOrder() {
        return this.employees.subList(0, 1);
    }

    @Override
    public Iterable<Person> firstByInsertOrder(int count) {
        return this.employees.subList(0, Math.min(count, this.getCount()));
    }

    @Override
    public Iterable<Person> searchWithNameSize(int minLength, int maxLength) {
        List<Person> result = new ArrayList<>();
        SortedMap<Integer, Set<Person>> submap = this.byNameLength.subMap(minLength, maxLength + 1);
        for (Set<Person> value : submap.values()) {
            result.addAll(value);
        }
        return result;
    }

    @Override
    public Iterable<Person> getWithNameSize(int length) {
        Set<Person> result = this.byNameLength.get(length);
        if(result == null || result.size() == 0) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Person> peopleByInsertOrder() {
        return this.employees;
    }

    @Override
    public Iterator<Person> iterator() {
        return this.employees.iterator();
    }
}
