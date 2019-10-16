package exam_prep.sep_2018_scheduler.test.correctness;

import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

public class Test7
{

    @Test(expected = IndexOutOfBoundsException.class)
    public void FindByIndex_ThreadExecutor_ShouldThrowWhenOutOfBounds()
    {

        //Arrange
        Scheduler executor = new ThreadExecutor();
        //Act
        Task task1 = new Task(5, 6, Priority.HIGH);
        Task task2 = new Task(6, 2, Priority.LOW);
        executor.execute(task1);
        executor.execute(task2);

        //Assert
        executor.getByIndex(-5);
        executor.getByIndex(5);

    }

}
