package exam_prep.sep_2018_royale_arena.performance;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class Perf01 {

    @Test
    public void add_100000_Battlecards_Should_WorkFast()
    {

        Arena ar = new RoyaleArena();

        int count = 100000;
        CardType[] statuses = new CardType[]
                {
                        CardType.MELEE,
                        CardType.RANGED,
                        CardType.SPELL,
                        CardType.BUILDING
                };
        Random rand = new Random();
        long start = System.currentTimeMillis();
        for(int i = 0; i < count; i++)
        {
            //int status = ThreadLocalRandom.current().nextInt(0, 4);
            ar.add(new Battlecard(i, CardType.SPELL,
                    String.valueOf(i),(double)i, (double)i));
        }

        long end = System.currentTimeMillis();
        Assert.assertEquals(count, ar.getCount());
        Assert.assertTrue(end - start < 400);
    }

}
