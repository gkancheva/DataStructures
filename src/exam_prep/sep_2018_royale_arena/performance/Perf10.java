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
import java.util.stream.Collectors;

public class Perf10 {



    //GetByCardTypeAndMaximumAmount
    @Test
    public void GetByCardTypeAndMaximumDamage_ShouldWorkFast()
    {
        Arena ar = new RoyaleArena();
        List<Battlecard> cds = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++)
        {
            int amount = ThreadLocalRandom.current().nextInt(0, 1000);
            Battlecard cd = new Battlecard(i, CardType.SPELL,
                    String.valueOf(i),(double)amount, (double)i);
            ar.add(cd);
            if (amount <= 500) cds.add(cd);
        }
        cds = cds.stream().sorted((x,y) -> {
            int compare = Double.compare(y.getDamage(), x.getDamage());

            if(compare == 0){
                return Integer.compare(x.getId(), y.getId());
            }

            return compare;
        }).collect(Collectors.toList());
        int count = ar.getCount();
        Assert.assertEquals(100000, count);

        long start = System.currentTimeMillis();

        Iterable<Battlecard> all = ar.getByCardTypeAndMaximumDamage(
                CardType.SPELL, 500);
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
