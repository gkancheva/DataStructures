package exam_prep.sep_2018_scheduler.test.performance;

import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.Random;


public class Perf1
{
    @Test
    public void Execute_ThreadExecutor_ShouldWorkFast()
    {

        //Arange

        int items = 80000;

        Scheduler executor = new ThreadExecutor();

        //Act
        long start = System.currentTimeMillis();
        Random rand = new Random();
        for (int i = 0; i < items; i++)
        {
            executor.execute(new Task(i, rand.nextInt(2000), Priority.EXTREME));
        }

        long end = System.currentTimeMillis();

        //Assert
        long elapsed = end - start;

        Assert.assertTrue(elapsed < 350);

    }

}
