package exam_prep.sep_2018_scheduler.test.correctness;


import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.ThreadExecutor;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;

public class Test21
{

    @Test
    public void Contains_ThreadExecutor_ShouldWorkCorrectly_AfterCycle()
    {

        //Arrange
        Scheduler executor = new ThreadExecutor();

        Task task1 = new Task(52, 12, Priority.EXTREME);
        Task task2 = new Task(13, 8, Priority.HIGH);

        //Act
        executor.execute(task1);
        executor.execute(task2);

        executor.cycle(5);

        Assert.assertTrue(executor.contains(task2));

        executor.cycle(3);

        //Assert
        Assert.assertEquals(1, executor.getCount());
        Assert.assertFalse(executor.contains(task2));

    }

}
