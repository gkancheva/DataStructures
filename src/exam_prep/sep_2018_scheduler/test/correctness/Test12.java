package exam_prep.sep_2018_scheduler.test.correctness;


import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

public class Test12
{

    @Test
    public void Cycle_ThreadExecutor_ShouldReturnCorrectRemovalCount()
    {

        //Arange
        Scheduler executor = new ThreadExecutor();

        Task task1 = new Task(5, 5, Priority.HIGH);
        Task task2 = new Task(6, 5, Priority.LOW);
        Task task3 = new Task(7, 12, Priority.MEDIUM);
        Task task4 = new Task(12, 5, Priority.HIGH);
        Task task5 = new Task(15, 3, Priority.HIGH);
        Task task6 = new Task(19, 2, Priority.EXTREME);
        Task task7 = new Task(23, 16, Priority.LOW);
        Task task8 = new Task(73, 6, Priority.LOW);

        //Act
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.execute(task4);
        executor.execute(task5);
        executor.execute(task6);
        executor.execute(task7);
        executor.execute(task8);

        //Assert
        Assert.assertEquals(8, executor.getCount());
        Task[] expectedStructure = new Task[] {
                task1, task2, task3, task4, task5, task6, task7, task8
        };
        Task[] actualStructure = iterableToArray(executor);
        Assert.assertArrayEquals(expectedStructure, actualStructure);

        int cycleCount = executor.cycle(5);
        Assert.assertEquals(5, cycleCount);
        cycleCount = executor.cycle(12);
        Assert.assertEquals(3, cycleCount);

        Assert.assertEquals(0, executor.getCount());

        expectedStructure = new Task[0];
        actualStructure = iterableToArray(executor);
        Assert.assertArrayEquals(expectedStructure, actualStructure);
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

