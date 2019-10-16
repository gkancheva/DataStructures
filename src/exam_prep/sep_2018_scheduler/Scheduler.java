package exam_prep.sep_2018_scheduler;

public interface Scheduler extends Iterable<Task> {

    int getCount();

    void execute(Task t);
    boolean contains(Task t);

    Task getById(int id);
    Task getByIndex(int index);

    int cycle(int cycles);

    void changePriority(int id, Priority newPriority);

    Iterable<Task> getByConsumptionRange(int lo, int hi, boolean inclusive);
    Iterable<Task> getByPriority(Priority priority);
    Iterable<Task> getByPriorityAndMinimumConsumption(Priority priority, int lo);

}
