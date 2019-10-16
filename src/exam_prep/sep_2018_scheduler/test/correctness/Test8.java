package exam_prep.sep_2018_scheduler.test.correctness;

import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

public class Test8
{

    @Test
    public void FindById_ThreadExecutor_ShouldWorkCorrectly()
    {

        //Arrange
        Scheduler executor = new ThreadExecutor();
        Task task1 = new Task(5, 6, Priority.HIGH);
        Task task2 = new Task(6, 2, Priority.LOW);
        Task task3 = new Task(7, 4, Priority.LOW);
        Task task4 = new Task(0, 56, Priority.EXTREME);
        Task task5 = new Task(0, 56, Priority.EXTREME);

        //Act
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.execute(task4);

        Task result1 = executor.getById(5);
        Task result2 = executor.getById(6);
        Task result3 = executor.getById(7);
        Task result4 = executor.getById(0);

        //Assert
        Assert.assertSame(result1, task1);
        Assert.assertSame(result2, task2);
        Assert.assertSame(result3, task3);
        Assert.assertSame(result4, task4);
        Assert.assertNotSame(result4, task5);
    }


}

