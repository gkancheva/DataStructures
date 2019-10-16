package exam_prep.sep_2018_scheduler.test.correctness;

import exam_prep.sep_2018_scheduler.Priority;

import exam_prep.sep_2018_scheduler.Scheduler;
import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

public class Test14
{

    @Test
    public void GetByConsumptionRange_ThreadExecutor_ShouldReturnAnEmptyCollection()
    {

        //Arange
        Scheduler executor = new ThreadExecutor();
        Scheduler executor2 = new ThreadExecutor();

        Task task1 = new Task(52, 5, Priority.HIGH);
        Task task2 = new Task(153, 7, Priority.LOW);

        //Act
        executor.execute(task1);
        executor.execute(task2);

        //Assert
        Assert.assertEquals(0, iterableToArray(executor2.getByConsumptionRange(5, 6, true)).length);
        Assert.assertArrayEquals(new Task[0], iterableToArray(executor.getByConsumptionRange(6, 6, true)));

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

