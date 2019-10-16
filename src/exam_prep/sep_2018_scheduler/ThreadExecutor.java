package exam_prep.sep_2018_scheduler;

import java.util.*;
import java.util.stream.Collectors;

public class ThreadExecutor implements Scheduler {

    private TreeMap<Integer, Task> tasksById;
    private Map<Priority, Set<Task>> tasksByPriority;
    private List<Task> tasks;

    public ThreadExecutor() {
        this.tasks = new ArrayList<>();
        this.tasksById = new TreeMap<>();
        this.tasksByPriority = new HashMap<>();
        this.tasksByPriority.put(Priority.LOW, new TreeSet<>());
        this.tasksByPriority.put(Priority.MEDIUM, new TreeSet<>());
        this.tasksByPriority.put(Priority.HIGH, new TreeSet<>());
        this.tasksByPriority.put(Priority.EXTREME, new TreeSet<>());
    }

    public int getCount() {
        return this.tasksById.size();
    }

    public void execute(Task t) {
        if(this.tasksById.containsKey(t.getId())) {
            throw new IllegalArgumentException();
        }
        this.tasksById.put(t.getId(), t);
        this.tasksByPriority.putIfAbsent(t.getTaskPriority(), new TreeSet<>());
        this.tasksByPriority.get(t.getTaskPriority()).add(t);
        this.tasks.add(t);
    }

    public boolean contains(Task t) {
        return this.tasksById.containsKey(t.getId());
    }

    public Task getById(int id) {
        if(!this.tasksById.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.tasksById.get(id);
    }

    public Task getByIndex(int index) {
        if(index < 0 || index > this.tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = this.tasks.get(index);
        if(!this.tasksById.containsKey(task.getId())) {
            return null;
        }
        return this.tasks.get(index);
    }

    public int cycle(int cycles) {
        if(this.tasks.size() == 0) {
            throw new IllegalArgumentException();
        }
        List<Task> byIndex = new ArrayList<>(this.tasks.size()/2);
        int count = 0;
        for (Task task : this.tasks) {
            int consumption = task.getConsumption() - cycles;
            if (consumption <= 0) {
                if(!tasksById.containsKey(task.getId())) {
                    continue;
                }
                this.tasksById.remove(task.getId());
                this.tasksByPriority.get(task.getTaskPriority()).remove(task);
                count++;
                continue;
            }
            task.setConsumption(task.getConsumption() - cycles);
            byIndex.add(task);
        }
        this.tasks = byIndex;
        return count;
    }

    public void changePriority(int id, Priority newPriority) {
        if(!this.tasksById.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        Task task = this.tasksById.get(id);
        this.tasksByPriority.get(task.getTaskPriority()).remove(task);
        task.setTaskPriority(newPriority);
        this.tasksByPriority.get(newPriority).add(task);
    }

    public Iterable<Task> getByConsumptionRange(int lo, int hi, boolean inclusive) {
        List<Task> result = new ArrayList<>(this.tasks.size());
        for (Task task : this.tasks) {
            if(!this.tasksById.containsKey(task.getId())) {
                continue;
            }
            int consumption = task.getConsumption();
            if (consumption > lo && consumption < hi || inclusive && (consumption == lo || consumption == hi) && consumption != 0) {
                result.add(task);
            }
        }

        return result.stream().filter(x -> x.getConsumption() != 0).sorted(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                int compare = Integer.compare(o1.getConsumption(), o2.getConsumption());
                if(compare == 0) {
                    return o1.getTaskPriority().ordinal() - o2.getTaskPriority().ordinal();
                }
                return compare;
            }
        }).collect(Collectors.toList());
    }

    public Iterable<Task> getByPriority(Priority priority) {
        return this.tasksByPriority.getOrDefault(priority, new TreeSet<>());
    }

    public Iterable<Task> getByPriorityAndMinimumConsumption(Priority priority, int lo) {
        return this.tasksByPriority.getOrDefault(priority, new TreeSet<>())
                .stream()
                .filter(x -> x.getConsumption() >= lo)
                .collect(Collectors.toList());
    }

    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}