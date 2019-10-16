package exam_prep.sep_2018_scheduler.test.performance;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Perf4
{
    @Test(timeout = 12000)
    public void GetByConsumptionRange_ThreadExecutor_ShouldWorkFast()
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
        List<Pair<Integer,Integer>> ranges = new ArrayList<>();
        List<List<Task>> tasks = new ArrayList<List<Task>>();
        for(int i = 0; i < 100; i++)
        {
            int lower = rand.nextInt(5000 - 2500) + 5000;
            int upper = rand.nextInt(9999 - 5005) + 9999;

            ranges.add(new Pair<Integer, Integer>(lower, upper));
            tasks.add(expected.stream()
                    .filter(x -> x.getConsumption() >= lower && x.getConsumption() <= upper)
                    .sorted((x,y) ->
                    {
                        int compare = Integer.compare(x.getConsumption(), y.getConsumption());
                        if (compare == 0) {
                            return x.getTaskPriority().compareTo(y.getTaskPriority());
                        }
                        return compare;
                    })
                    .collect(Collectors.toList()));
        }

        long start = System.currentTimeMillis();

        List<Iterable<Task>> actualTasks = new ArrayList<>();

        for(int i = 0; i < 100; i++)
        {
            Pair<Integer, Integer> range = ranges.get(i);
            actualTasks.add(executor.getByConsumptionRange(range.getKey(), range.getValue(), true));
        }

        long end = System.currentTimeMillis();

        //Assert
        Assert.assertTrue(end - start <  200);
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
