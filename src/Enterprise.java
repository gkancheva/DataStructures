import java.util.*;

public class Enterprise implements IEnterprise {

    private List<Employee> employees;
    private Map<UUID, Employee> byId;
    private TreeMap<Date, Set<Employee>> byHireDate;
    private TreeMap<Double, List<Employee>> bySalary;

    public Enterprise() {
        this.employees = new ArrayList<>();
        this.byId = new HashMap<>();
        this.byHireDate = new TreeMap<>();
        this.bySalary = new TreeMap<>();
    }

    @Override
    public void add(Employee employee) {
        this.employees.add(employee);
        this.byId.put(employee.getId(), employee);
        this.byHireDate.putIfAbsent(employee.getHireDate(), new HashSet<>());
        this.byHireDate.get(employee.getHireDate()).add(employee);
        this.bySalary.putIfAbsent(employee.getSalary(), new LinkedList<>());
        this.bySalary.get(employee.getSalary()).add(employee);
    }

    @Override
    public boolean contains(UUID id) {
        if(!this.byId.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.byId.containsKey(id);
    }

    @Override
    public boolean contains(Employee employee) {
        if(!this.byId.containsKey(employee.getId())) {
            throw new IllegalArgumentException();
        }
        return this.contains(employee.getId());
    }

    @Override
    public boolean change(UUID id, Employee employee) {
        if(!this.byId.containsKey(id)) {
            return false;
        }
        Employee old = this.byId.get(id);
        this.byId.put(id, employee);
        if(!employee.getHireDate().equals(old.getHireDate())) {
            this.byHireDate.get(old.getHireDate()).remove(old);
            this.byHireDate.putIfAbsent(employee.getHireDate(), new LinkedHashSet<>());
            this.byHireDate.get(employee.getHireDate()).add(employee);
        }
        if(Double.compare(employee.getSalary(), old.getSalary()) != 0) {
            this.bySalary.get(old.getSalary()).remove(old);
            this.bySalary.putIfAbsent(employee.getSalary(), new LinkedList<>());
            this.bySalary.get(employee.getSalary()).add(employee);
        }
        return true;
    }

    @Override
    public boolean fire(UUID id) {
        if(!this.byId.containsKey(id)) {
            return false;
        }
        Employee employee = this.byId.remove(id);
        this.employees.remove(employee);
        this.byHireDate.get(employee.getHireDate()).remove(employee);
        this.bySalary.get(employee.getSalary()).remove(employee);
        return true;
    }

    @Override
    public boolean raiseSalary(int months, int percent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(Calendar.MONTH, months);
        SortedMap<Date, Set<Employee>> employees = this.byHireDate.headMap(calendar.getTime());
        if(employees.isEmpty()) {
           return false;
        }
        for (Set<Employee> employeeSet : employees.values()) {
            for (Employee employee : employeeSet) {
                double newSalary = employee.getSalary() * (percent / 100.00);
                employee.setSalary(newSalary);
                this.byId.put(employee.getId(), employee);
            }
        }
        return true;
    }

    @Override
    public int getCount() {
        return this.byId.size();
    }

    @Override
    public Employee getByUUID(UUID id) {
        if(!this.byId.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.byId.get(id);
    }

    @Override
    public Position positionByUUID(UUID id) {
        return null;
    }

    @Override
    public Iterable<Employee> getByPosition(Position position) {
        return null;
    }

    @Override
    public Iterable<Employee> getBySalary(double minSalary) {
        return null;
    }

    @Override
    public Iterable<Employee> getBySalaryAndPosition(double salary, Position position) {
        return null;
    }

    @Override
    public Iterable<Employee> searchBySalary(double minSalary, double maxSalary) {
        return null;
    }

    @Override
    public Iterable<Employee> searchByPosition(Iterable<Position> positions) {
        return null;
    }

    @Override
    public Iterable<Employee> allWithPositionAndMinSalary(Position position, double minSalary) {
        return null;
    }

    @Override
    public Iterable<Employee> searchByFirstName(String firstName) {
        return null;
    }

    @Override
    public Iterable<Employee> searchByNameAndPosition(String firstName, String lastName, Position position) {
        return null;
    }

    @Override
    public Iterator<Employee> iterator() {
        return this.employees.iterator();
    }
}
