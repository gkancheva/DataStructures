package exam_prep.sep_2018_scheduler.test.correctness;


import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

public class Test28
{

    @Test
    public void Enumerator_ThreadExecutor_ShouldReturnEmptyCollection()
    {

        //Arange
        Scheduler executor = new ThreadExecutor();

        Task task1 = new Task(52, 5, Priority.HIGH);
        Task task2 = new Task(56, 12, Priority.HIGH);

        Task[] expected = new Task[0];
        //Act
        Assert.assertEquals(0, executor.getCount());

        //Assert
        Task[] actual = iterableToArray(executor);

        Assert.assertArrayEquals(expected, actual);

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
