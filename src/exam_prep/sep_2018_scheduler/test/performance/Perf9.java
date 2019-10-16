package exam_prep.sep_2018_scheduler.test.performance;

import exam_prep.sep_2018_scheduler.Scheduler;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.ThreadExecutor;
import exam_prep.sep_2018_scheduler.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Perf9
{
    @Test
    public void GetByPriorityAndMinimumConsumption_ThreadExecutor_ShouldWorkFast()
    {

        //Arange
        int items = 100000;

        Scheduler executor = new ThreadExecutor();

        List<Task> expected = new ArrayList<Task>();
        Priority[] priorities = new Priority[] { Priority.LOW, Priority.MEDIUM, Priority.HIGH, Priority.EXTREME };
        Random rand = new Random();

        //Act
        for (int i = 0; i < items; i++)
        {
            Task task = new Task(i, rand.nextInt(10000), priorities[rand.nextInt(4)]);
            expected.add(task);
            executor.execute(task);
        }

        //do
        List<Pair<Integer, Priority>> ranges = new ArrayList<Pair<Integer, Priority>>();
        List<List<Task>> tasks = new ArrayList<List<Task>>();
        for (int i = 0; i < 100; i++)
        {
            int lower = rand.nextInt(10000 - 1000) + 10000;
            Priority priority = priorities[rand.nextInt(4)];

            ranges.add(new Pair<>(lower, priority));
            tasks.add(expected.stream()
                    .filter(x -> x.getConsumption() >= lower && x.getTaskPriority() == priority)
                    .sorted((x,y) -> Integer.compare(y.getId(), x.getId()))
                    .collect(Collectors.toList())
            );
        }

        long start = System.nanoTime();

        List<Iterable<Task>> actualTasks = new ArrayList<Iterable<Task>>();

        for (int i = 0; i < 100; i++)
        {
            Pair<Integer, Priority> range = ranges.get(i);
            actualTasks.add(executor.getByPriorityAndMinimumConsumption(range.getValue(), range.getKey()));
        }

        long end = System.currentTimeMillis();
        long delta = end - start;
        //Assert
        Assert.assertTrue(delta < 200);
        Assert.assertEquals(tasks.size(), actualTasks.size());
        for(int i = 0 ; i < tasks.size(); i++){
            Assert.assertArrayEquals(iterableToArray(tasks.get(i)), iterableToArray(actualTasks.get(i)));
        }
    }


    private Task[] iterableToArray(Iterable<Task> byPriority) {

        List<Task> tasks = new ArrayList<Task>();

        for(Task t : byPriority){
            tasks.add(t);
        }

        Task[] arr = new Task[tasks.size()];

        for(int i = 0 ; i < tasks.size(); i++){
            arr[i] = tasks.get(i);
        }

        return arr;
    }

}
