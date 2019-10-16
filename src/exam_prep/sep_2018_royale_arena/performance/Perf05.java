package exam_prep.sep_2018_royale_arena.performance;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Perf05 {

    //RemoveById
    @Test
    public void RemoveById_ShoudlWorkFast()
    {

        Arena ar = new RoyaleArena();
        List<Battlecard> cds = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++)
        {
            int amount = ThreadLocalRandom.current().nextInt(0, 60000);
            Battlecard cd = new Battlecard(i, CardType.SPELL,
                    String.valueOf(i),(double)i, (double)amount);
            ar.add(cd);
            cds.add(cd);
        }

        int count = ar.getCount();
        Assert.assertEquals(100000, count);


        long start = System.currentTimeMillis();

        for(Battlecard cd : cds)
        {
            ar.removeById(cd.getId());
        }

        long end = System.currentTimeMillis();
        long l1 = end - start;
        Assert.assertTrue(l1 < 200);
    }
}
