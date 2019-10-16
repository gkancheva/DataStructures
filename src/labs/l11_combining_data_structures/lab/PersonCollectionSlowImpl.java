package labs.l11_combining_data_structures.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonCollectionSlowImpl implements PersonCollection {

    public List<Person> result = new ArrayList<>();

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        for (Person person : this.result) {
            if (person.getEmail().equals(email)) {
                return false;
            }
        }
        this.result.add(new Person(email, name, age, town));
        return true;
    }

    @Override
    public int getCount() {
        return this.result.size();
    }

    @Override
    public Person findPerson(String email) {
        for (Person person : this.result) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public boolean deletePerson(String email) {
        return this.result.remove(this.findPerson(email));
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.result) {
            String mail = person.getEmail();
            String domain = mail.substring(mail.indexOf("@") + 1);
            if(domain.equals(emailDomain)) {
                result.add(person);
            }
        }
        return result.stream().sorted((x,y) -> x.getEmail().compareTo(y.getEmail())).collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.result) {
            if(person.getName().equals(name) && person.getTown().equals(town)) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.result) {
            if(person.getAge() >= startAge && person.getAge() <= endAge) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.result) {
            if(person.getTown().equals(town) && person.getAge() >= startAge && person.getAge() <= endAge) {
                result.add(person);
            }
        }
        return result;
    }
}
