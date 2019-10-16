package labs.l11_combining_data_structures.lab;

import java.util.*;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> peopleByEmail = new HashMap<>();;
    private Map<String, Set<Person>> peopleByDomain = new HashMap<>();
    private Map<String, Set<Person>> peopleByTownAndName = new TreeMap<>();
    private TreeMap<Integer, Set<Person>> peopleByAge = new TreeMap<>();
    private TreeMap<String, Set<Person>> peopleByAgeTown = new TreeMap<>();

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        Person person = new Person(email, name, age, town);
        String domain = email.substring(email.indexOf("@") + 1);

        if(this.peopleByEmail.containsKey(email)) {
            return false;
        }
        this.peopleByEmail.put(email, person);

        this.peopleByDomain.putIfAbsent(domain, new TreeSet<>());

        Set<Person> peopleByMail = this.peopleByDomain.get(domain);
        if(peopleByMail.contains(person)) {
            return false;
        }
        peopleByMail.add(person);

        this.peopleByTownAndName.putIfAbsent(town + name, new TreeSet<>());

        if(this.peopleByTownAndName.get(town + name).contains(person)) {
            return false;
        }
        this.peopleByTownAndName.get(town + name).add(person);

        this.peopleByAge.putIfAbsent(age, new TreeSet<>());
        if(this.peopleByAge.get(age).contains(person)) {
            return false;
        }
        this.peopleByAge.get(age).add(person);
        String townAge = town + age;
        this.peopleByAgeTown.putIfAbsent(townAge, new TreeSet<>());
        this.peopleByAgeTown.get(townAge).add(person);
        return true;
    }

    @Override
    public int getCount() {
        return this.peopleByEmail.size();
    }

    @Override
    public Person findPerson(String email) {
        return this.peopleByEmail.get(email);
    }

    @Override
    public boolean deletePerson(String email) {
        if(!this.peopleByEmail.containsKey(email)) {
            return false;
        }
        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        return this.peopleByDomain.getOrDefault(emailDomain, new TreeSet<>());
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        return this.peopleByTownAndName.getOrDefault(town + name, new TreeSet<>());
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        List<Person> result = new LinkedList<>();
        this.peopleByAge.subMap(startAge, endAge + 1)
                .forEach((key, value) -> result.addAll(value));
        return result;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        List<Person> result = new LinkedList<>();
        String start = town + startAge;
        String end = town + endAge;
        SortedMap<String, Set<Person>> set = this.peopleByAgeTown.subMap(start, end);
        for (Set<Person> people : set.values()) {
            for (Person p : people) {
                if(p.getAge() >= startAge && p.getAge() <= endAge) {
                    result.add(p);
                }
            }
        }
        return result;
    }
}
