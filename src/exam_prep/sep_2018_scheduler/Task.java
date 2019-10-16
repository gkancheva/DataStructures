package exam_prep.sep_2018_scheduler;

public class Task implements Comparable<Task> {

    private int id;
    private int consumption;
    private Priority taskPriority;


    public Task(int id, int consumption, Priority priority){

        this.id = id;
        this.consumption = consumption;
        this.taskPriority = priority;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public Priority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Priority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public int compareTo(Task o) {
        return Integer.compare(o.getId(), this.getId());
    }
}
