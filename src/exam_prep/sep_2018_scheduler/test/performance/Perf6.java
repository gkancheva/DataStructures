package exam_prep.sep_2018_scheduler.test.performance;

import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.LinkedList;

public class Perf6
{
    @Test
    public void Contains_100000_Elements_ShouldExecuteFast()
    {
        // Arrange
        Scheduler executor = new ThreadExecutor();
        int count = 100000;
        LinkedList<Task> tasks = new LinkedList<Task>();

        for (int i = 0; i < count; i++)
        {
            tasks.addLast(new Task(i, i, Priority.HIGH));
            executor.execute(tasks.getLast());
        }

        // Act
        long start = System.currentTimeMillis();
        for(Task node : tasks)
        {
            Assert.assertTrue(executor.contains(node));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 250);
    }
}
