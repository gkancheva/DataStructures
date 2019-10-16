package exam_prep.sep_2018_scheduler.test.correctness;


import org.junit.Test;
import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import java.util.ArrayList;
import java.util.List;

public class Test25
{

    @Test(expected = IllegalArgumentException.class)
    public void ChangePriority_ThreadExecutor_ShouldThrow_On_InvalidArgument()
    {

        //Arange
        Scheduler executor = new ThreadExecutor();

        //Act

        //Assert
        executor.changePriority(600, Priority.HIGH);


    }

    private Task[] iterableToArray(Iterable<Task> byPriority) {

        List<Task> tasks = new ArrayList<Task>();

        for(Task t : byPriority){
            tasks.add(t);
        }

        Task[] arr = new Task[tasks.size()];

        for(int i = 0 ; i < tasks.size(); i++){
            arr[i] = tasks.get(i);
        }

        return arr;
    }


}
