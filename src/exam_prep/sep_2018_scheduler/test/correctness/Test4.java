package exam_prep.sep_2018_scheduler.test.correctness;

import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.ThreadExecutor;
import exam_prep.sep_2018_scheduler.Task;

public class Test4
{
    @Test(expected = IllegalArgumentException.class)
    public void Count_ThreadExecutor_ShouldIncrase()
    {

        //Arrange
        Scheduler executor = new ThreadExecutor();

        Task task1 = new Task(5, 1, Priority.EXTREME);
        Task task2 = new Task(6, 1, Priority.HIGH);
        Task task3 = new Task(12, 5, Priority.MEDIUM);
        Task task4 = new Task(51, 15, Priority.HIGH);
        Task task5 = new Task(5, 1, Priority.HIGH);

        //Act
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        Assert.assertEquals(3, executor.getCount());
        executor.execute(task4);
        Assert.assertEquals(4, executor.getCount());

        //Assert
        Assert.assertEquals(4, executor.getCount());
        executor.execute(task5);


    }

}

