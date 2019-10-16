package exam_prep.sep_2018_royale_arena.performance;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Perf14 {


    //GetAllReceiversWithCardType
    @Test
    public void GetAllByNameAndSwag_ShouldWorkFast()
    {
        Arena ar = new RoyaleArena();
        Map<String, Battlecard> max = new HashMap<String, Battlecard>();
        CardType[] statuses = new CardType[]
                {
                        CardType.MELEE,
                        CardType.RANGED,
                        CardType.SPELL,
                        CardType.BUILDING
                };
        Random rand = new Random();


        for (int i = 0; i < 100000; i++)
        {
            int status = ThreadLocalRandom.current().nextInt(0, 4);
            String name = String.valueOf(ThreadLocalRandom.current().nextInt(0, 200));
            CardType TS = statuses[status];
            Battlecard cd = new Battlecard(i, TS,
                    name ,(double)i, (double)i);

            if (!max.containsKey(name))
            {
                max.put(name, cd);
            }
            if (max.get(name).getSwag() <= cd.getSwag())
            {
                max.put(name,cd);
            }

            ar.add(cd);
        }
        int count = ar.getCount();
        Assert.assertEquals(100000, count);


        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++)
        {
            Iterable<Battlecard> all = ar.getAllByNameAndSwag();
            for(Battlecard item : all)
            {
                Assert.assertSame(max.get(item.getName()), item);
            }
        }
        long end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 150);
    }
}
