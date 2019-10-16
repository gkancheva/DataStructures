package exam_prep.sep_2018_scheduler.test.correctness;

import org.junit.Test;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

public class Test11
{
    @Test(expected = IllegalArgumentException.class)
    public void Cycle_ThreadExecutor_ShouldThrowWhenEmpty()
    {

        //Arange
        Scheduler executor = new ThreadExecutor();

        //Act
        //Assert
        executor.cycle(1);
    }
}

