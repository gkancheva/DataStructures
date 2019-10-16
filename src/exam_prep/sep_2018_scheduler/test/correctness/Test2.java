package exam_prep.sep_2018_scheduler.test.correctness;

import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

public class Test2
{

    @Test
    public void Addition_ThreadExecutor_ShouldAddCorrectTasks()
    {
        //Arrange
        Scheduler executor = new ThreadExecutor();
        Task task1 = new Task(5, 1, Priority.EXTREME);
        Task task2 = new Task(6, 5, Priority.HIGH);
        Task task3 = new Task(12, 12, Priority.LOW);

        //Act
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);

        //Assert
        Task result1 = executor.getByIndex(0);
        Task result2 = executor.getByIndex(1);
        Task result3 = executor.getByIndex(2);

        Assert.assertSame(task1, result1);
        Assert.assertSame(task2, result2);
        Assert.assertSame(task3, result3);

    }

}
