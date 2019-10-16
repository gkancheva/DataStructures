package exam_prep.jul_2017_organization;

public class Person implements Comparable<Person> {

    private String name;

    private double salary;

    public Person(String name, double salary) {
        this.setName(name);
        this.setSalary(salary);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Person other) {
        int compare = this.name.compareTo(other.getName());
        if(compare == 0) {
            return Double.compare(this.salary, other.getSalary());
        }
        return compare;
    }
}
