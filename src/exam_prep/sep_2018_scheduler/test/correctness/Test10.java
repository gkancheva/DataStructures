package exam_prep.sep_2018_scheduler.test.correctness;


import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;


import org.junit.Assert;
import org.junit.Test;

public class Test10
{

    @Test(expected = IndexOutOfBoundsException.class)
    public void Cycle_ThreadExecutor_ShouldWorkCorrectly()
    {

        //Arange
        Scheduler executor = new ThreadExecutor();

        Task task1 = new Task(5, 5, Priority.HIGH);
        Task task2 = new Task(6, 5, Priority.LOW);
        Task task3 = new Task(7, 12, Priority.MEDIUM);
        Task task4 = new Task(12, 5, Priority.HIGH);
        Task task5 = new Task(15, 3, Priority.LOW);
        Task task6 = new Task(19, 2, Priority.LOW);

        //Act
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.execute(task4);
        executor.execute(task5);
        executor.execute(task6);

        Assert.assertEquals(6, executor.getCount());
        executor.cycle(3);
        Assert.assertEquals(4, executor.getCount());


        executor.cycle(5);

        //Assert
        Assert.assertEquals(1, executor.getCount());
        Assert.assertSame(task3, executor.getByIndex(0));
        executor.getByIndex(1);

    }

}
