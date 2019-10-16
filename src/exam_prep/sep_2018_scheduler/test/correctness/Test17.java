package exam_prep.sep_2018_scheduler.test.correctness;
import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;


public class Test17
{

    @Test
    public void GetByPriority_ThreadExecutor_ShouldWorkCorrectly()
    {

        //Arange
        Scheduler executor = new ThreadExecutor();

        Task task1 = new Task(52, 5, Priority.HIGH);
        Task task2 = new Task(56, 12, Priority.HIGH);
        Task task3 = new Task(58, 12, Priority.LOW);
        Task task4 = new Task(100, 51, Priority.HIGH);
        Task task5 = new Task(600, 15, Priority.MEDIUM);
        Task task6 = new Task(12, 5, Priority.EXTREME);
        Task task7 = new Task(125, 6, Priority.MEDIUM);
        Task task8 = new Task(0, 8, Priority.LOW);
        Task[] expected = new Task[]
                {
                        task4, task2, task1
                };

        //Act
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.execute(task4);
        executor.execute(task5);
        executor.execute(task6);
        executor.execute(task7);
        executor.execute(task8);
        Assert.assertEquals(8, executor.getCount());

        //Assert
        Task[] actual = iterableToArray(executor.getByPriority(Priority.HIGH));

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

