package exam_prep.sep_2018_royale_arena.performance;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Perf13 {


    @Test
    public void GetAllInSwagRange()
    {
        Arena ar = new RoyaleArena();
        List<Battlecard> cds = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++)
        {
            int amount = ThreadLocalRandom.current().nextInt(0, 1000);
            Battlecard cd = new Battlecard(i, CardType.SPELL,
                    "sender", 550d, (double)amount);
            ar.add(cd);
            if (amount >= 200 && amount <= 600) cds.add(cd);
        }
        int count = ar.getCount();
        Assert.assertEquals(100000, count);

        cds.sort(Comparator
                .comparing(Battlecard::getSwag)
                .thenComparing(Battlecard::getId));

        long start = System.currentTimeMillis();

        Iterable<Battlecard> all = ar.getAllInSwagRange(200, 600);
        int c = 0;
        for (Battlecard cd : all)
        {
            Assert.assertSame(cd, cds.get(c));
            c++;
        }

        long end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 150);
        Assert.assertEquals(cds.size(), c);
    }


}
