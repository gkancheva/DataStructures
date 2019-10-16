package exam_prep.sep_2018_scheduler.test.correctness;

import org.junit.Test;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

public class Test6
{

    @Test(expected = IndexOutOfBoundsException.class)
    public void FindByIndex_ThreadExecutor_ShouldThrowWhenEmpty()
    {

        //Arrange
        Scheduler executor = new ThreadExecutor();
        //Act
        //Assert
        executor.getByIndex(0);
    }

}
