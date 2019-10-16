package exam_prep.sep_2018_scheduler.test.performance;

import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Perf5
{

    @Test
    public void Cycle_ThreadExecutor_ShouldRemoveFast()
    {
        //Arange
        int count = 100_000;

        Scheduler executor = new ThreadExecutor();

        Random rand = new Random();
        Priority[] priorities = new Priority[] { Priority.LOW, Priority.MEDIUM, Priority.HIGH, Priority.EXTREME };

        List<Task> tasks = new ArrayList<Task>();

        //Act
        for (int i = 1; i < count; i++)
        {
            Task t = new Task(i, i, priorities[rand.nextInt(4)]);
            tasks.add(t);
            executor.execute(t);
        }

        long start = System.currentTimeMillis();

        int totalCycles = 0;
        for(int i = 0; i < 1000; i++)
        {
            int cycles = rand.nextInt(100);
            executor.cycle(cycles);
            totalCycles += cycles;
        }
        List<Task> exepcted = tasks.stream().skip(totalCycles).collect(Collectors.toList());
        Assert.assertArrayEquals(iterableToArray(exepcted), iterableToArray(executor));

        long end = System.currentTimeMillis();

        //Assert
        Assert.assertTrue(end - start < 200);
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
