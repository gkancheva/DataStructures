package exam_prep.sep_2018_scheduler.test.correctness;

import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.ThreadExecutor;
import exam_prep.sep_2018_scheduler.Task;

public class Test1
{
    @Test
    public void AddingTask_ThreadExecutor_CountShouldIncrease()
    {
        //Arrange
        Task task1 = new Task(52, 12, Priority.EXTREME);
        Task task2 = new Task(13, 66, Priority.HIGH);

        Scheduler executor = new ThreadExecutor();

        //Act
        executor.execute(task1);
        executor.execute(task2);

        //Assert
        Assert.assertEquals(2, executor.getCount());

    }
}
