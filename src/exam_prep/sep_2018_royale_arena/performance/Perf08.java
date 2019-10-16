package exam_prep.sep_2018_royale_arena.performance;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Perf08 {

    //GetByNameOrderedBySwagDescending
    @Test
    public void GetByNameOrderedBySwagDescending_ShouldWorkFast()
    {
        Arena ar = new RoyaleArena();
        List<Battlecard> cds = new ArrayList<>();
        for (int i = 0; i < 100000; i++)
        {
            Battlecard cd = new Battlecard(i, CardType.SPELL,
                    "player",(double)i, (double)i);
            ar.add(cd);
            cds.add(cd);
        }

        int count = ar.getCount();
        cds = cds.stream().sorted((x,y) -> Double.compare(y.getSwag(), x.getSwag())).collect(Collectors.toList());
        Assert.assertEquals(100000, count);

        long start = System.currentTimeMillis();

        Iterable<Battlecard> all = ar.getByNameOrderedBySwagDescending("player");
        int c = 0;
        for (Battlecard cd : all)
        {
            Assert.assertSame(cd, cds.get(c));
            c++;
        }

        long end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 200);
        Assert.assertEquals(100000, c);
    }

}
