package exam_prep.sep_2018_scheduler.test.performance;

import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Perf3
{
    @Test
    public void FindByIndex_ThreadExecutor_ShouldWorkFast()
    {
        // Arrange
        Scheduler executor = new ThreadExecutor();
        int count = 10000;
        List<Task> tasks = new ArrayList<Task>();

        for (int i = 0; i < count; i++)
        {
            tasks.add(new Task(i, i, Priority.HIGH));
            executor.execute(tasks.get(i));
        }

        // Act
        long start = System.currentTimeMillis();

        Random rand = new Random();
        for (int i = 0; i < 10000; i++)
        {
            int rnd = rand.nextInt(1500);
            Assert.assertEquals(tasks.get(rnd), executor.getByIndex(rnd));
        }
        // Assert
        long end = System.currentTimeMillis();
        long delta = end - start;
        Assert.assertTrue(delta < 150);

    }

}
