package exam_prep.sep_2018_scheduler.test.correctness;

import exam_prep.sep_2018_scheduler.ThreadExecutor;
import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;

public class Test9
{
    @Test(expected = IllegalArgumentException.class)
    public void FindById_ThreadExecutor_OnNonExistingId_ShouldThrow()
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

        //Assert
        Assert.assertEquals(4, executor.getCount());
        executor.getById(12);

    }
}
