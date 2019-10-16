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

public class Perf03 {


    //ContainsById
    @Test
    public void ContainsById_100000_ShouldWorkFast()
    {
        Arena ar = new RoyaleArena();
        int count = 100000;
        List<Battlecard> cds = new ArrayList<>();
        CardType[] statuses = new CardType[]
                {
                        CardType.MELEE,
                        CardType.RANGED,
                        CardType.SPELL,
                        CardType.BUILDING
                };
        Random rand = new Random();

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++)
        {
            int status = ThreadLocalRandom.current().nextInt(0, 4);
            Battlecard cd = new Battlecard(i, statuses[status],
                    String.valueOf(i),(double)i, (double)i);
            ar.add(cd);
            cds.add(cd);
        }

        long end = System.currentTimeMillis();
        Assert.assertEquals(count, ar.getCount());


        start = System.currentTimeMillis();

        for(Battlecard cd : cds)
        {
            Assert.assertEquals(true, ar.contains(cd));
        }

        end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 200);
    }

}
