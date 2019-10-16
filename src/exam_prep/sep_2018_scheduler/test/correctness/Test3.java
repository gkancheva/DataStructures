package exam_prep.sep_2018_scheduler.test.correctness;

import exam_prep.sep_2018_scheduler.ThreadExecutor;
import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.Priority;

public class Test3
{
    @Test(expected = IllegalArgumentException.class)
    public void AddingExistingId_ThreadExecutor_ShouldThrowException()
    {

        //Arrange
        Scheduler executor = new ThreadExecutor();

        Task task1 = new Task(5, 1, Priority.EXTREME);
        Task task2 = new Task(6, 1, Priority.HIGH);
        Task task3 = new Task(12, 5, Priority.MEDIUM);
        Task task4 = new Task(12, 5, Priority.HIGH);

        //Act
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);

        //Assert
        Assert.assertEquals(3, executor.getCount());
        executor.execute(task4);
    }

}

