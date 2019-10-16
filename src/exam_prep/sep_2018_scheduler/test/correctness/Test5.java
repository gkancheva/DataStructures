package exam_prep.sep_2018_scheduler.test.correctness;


import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

public class Test5
{

    @Test
    public void FindByIndex_ThreadExecutor_ShouldWorkCorrectly()
    {

        //Arrange
        Scheduler executor = new ThreadExecutor();
        Task task1 = new Task(5, 1, Priority.HIGH);
        Task task2 = new Task(6, 3, Priority.LOW);
        Task task3 = new Task(7, 6, Priority.LOW);
        Task task4 = new Task(8, 3, Priority.EXTREME);
        Task task5 = new Task(9, 5, Priority.MEDIUM);

        //Act
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task4);
        executor.execute(task5);
        executor.execute(task3);

        //Assert
        Assert.assertEquals(5, executor.getCount());
        Assert.assertSame(task1, executor.getByIndex(0));
        Assert.assertSame(task2, executor.getByIndex(1));
        Assert.assertSame(task4, executor.getByIndex(2));
        Assert.assertSame(task5, executor.getByIndex(3));
        Assert.assertSame(task3, executor.getByIndex(4));

    }


}
