package exam_prep.sep_2018_scheduler.test.performance;

import exam_prep.sep_2018_scheduler.ThreadExecutor;
import org.junit.Assert;
import org.junit.Test;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Task;

import java.util.LinkedList;

public class Perf2
{

    @Test
    public void FindById_ThreadExecutor_ShouldWorkFast()
    {
        // Arrange
        Scheduler executor = new ThreadExecutor();
        int count = 100000;
        LinkedList<Task> tasks = new LinkedList<Task>();

        for (int i = 0; i < count; i++) {
            tasks.addLast(new Task(i + 1, i, Priority.HIGH));
            executor.execute(tasks.getLast());
        }
        long start = System.currentTimeMillis();

        // Act
        for(Task node : tasks) {
            Assert.assertSame(node, executor.getById(node.getId()));
        }

        long end = System.currentTimeMillis();
        long delta = end - start;
        Assert.assertTrue(delta < 250);
    }

}
