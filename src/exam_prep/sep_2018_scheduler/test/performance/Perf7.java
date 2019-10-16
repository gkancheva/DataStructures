package exam_prep.sep_2018_scheduler.test.performance;

import exam_prep.sep_2018_scheduler.Priority;
import exam_prep.sep_2018_scheduler.Scheduler;
import exam_prep.sep_2018_scheduler.Task;
import exam_prep.sep_2018_scheduler.ThreadExecutor;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Perf7
{

    @Test
    public void ChangePriority_ThreadExecutor_ShouldWorkFast()
    {

        //Arange
        int items = 100_000;
        Scheduler executor = new ThreadExecutor();

        Map<Priority, List<Task>> dict = new HashMap<>();
        dict.put(Priority.LOW, new ArrayList<>());
        dict.put(Priority.MEDIUM, new ArrayList<Task>());
        dict.put(Priority.HIGH, new ArrayList<Task>());
        dict.put(Priority.EXTREME, new ArrayList<Task>());

        Priority[] priorities = new Priority[] { Priority.LOW, Priority.MEDIUM, Priority.HIGH, Priority.EXTREME };
        Random rand = new Random();

        //Act
        for (int i = 0; i < items; i++)
        {
            Task task = new Task(i, rand.nextInt(10000), priorities[rand.nextInt(4)]);
            dict.get(task.getTaskPriority()).add(task);
            executor.execute(task);
        }

        dict.put(Priority.LOW,
                dict.get(Priority.LOW).stream().sorted((x,y) -> Integer.compare(y.getId(), x.getId())).collect(Collectors.toList()));
        dict.put(Priority.MEDIUM,
                dict.get(Priority.MEDIUM).stream().sorted((x,y) -> Integer.compare(y.getId(), x.getId())).collect(Collectors.toList()));
        dict.put(Priority.HIGH,
                dict.get(Priority.HIGH).stream().sorted((x, y) -> Integer.compare(y.getId(), x.getId())).collect(Collectors.toList()));
        dict.put(Priority.EXTREME,
                dict.get(Priority.EXTREME).stream().sorted((x,y) -> Integer.compare(y.getId(), x.getId())).collect(Collectors.toList()));
        //Act

        Priority p1 = priorities[rand.nextInt(4)];
        Priority p2 = priorities[rand.nextInt(4)];

        int randomCount = ThreadLocalRandom.current().nextInt(5000, 6000);
        List<Task> p2Tasks = dict.get(p2)
                .stream()
                .skip(randomCount - (randomCount / 2))
                .limit(randomCount)
                .collect(Collectors.toList());
        List<Task> p1Tasks = dict.get(p1)
                .stream()
                .skip(randomCount - (randomCount / 2))
                .limit(randomCount)
                .collect(Collectors.toList());

        long start = System.currentTimeMillis();

        int min = Math.min(p1Tasks.size(), p2Tasks.size());
        for (int i = 0; i < min; i++)
        {
            executor.changePriority(p1Tasks.get(i).getId(), p2);
            executor.changePriority(p2Tasks.get(i).getId(), p1);
        }

        long end = System.currentTimeMillis();

        dict.get(p1).removeAll(p1Tasks);
        dict.get(p2).removeAll(p2Tasks);
        dict.get(p1).addAll(p2Tasks);
        dict.get(p2).addAll(p1Tasks);

        dict.put(p1, dict.get(p1).stream().sorted((x,y) -> Integer.compare(y.getId(), x.getId())).collect(Collectors.toList()));
        dict.put(p2, dict.get(p2).stream().sorted((x,y) -> Integer.compare(y.getId(), x.getId())).collect(Collectors.toList()));

        Assert.assertArrayEquals(iterableToArray(dict.get(p1)), iterableToArray(executor.getByPriority(p1)));
        Assert.assertArrayEquals(iterableToArray(dict.get(p2)), iterableToArray(executor.getByPriority(p2)));

        //Assert

        Assert.assertTrue(end - start < 200);

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
