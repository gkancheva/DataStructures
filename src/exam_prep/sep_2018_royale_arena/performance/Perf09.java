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

public class Perf09 {

    //FindFirstLeastSwag
    @Test
    public void FindFirstLeastSwag_ShouldWorkFast()
    {
        Arena ar = new RoyaleArena();
        List<Battlecard> cds = new ArrayList<>();
        for (int i = 0; i < 100000; i++)
        {
            Battlecard cd = new Battlecard(i, CardType.SPELL,
                    String.valueOf(i), (double)i, (double)i);
            ar.add(cd);
            cds.add(cd);
        }
        cds = cds.stream().sorted((x,y) -> Double.compare(x.getSwag(), y.getSwag())).collect(Collectors.toList());
        List<Integer> ns = new ArrayList<>();
        List<List<Battlecard>> expected = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < 100; i++)
        {
            int n = ThreadLocalRandom.current().nextInt(0, 5000);
            ns.add(n);
            expected.add(cds.stream().limit(n).collect(Collectors.toList()));
        }

        int count = ar.getCount();
        cds = cds.stream().sorted((x,y) -> {
            int compare = Double.compare(y.getDamage(), x.getDamage());

            if(compare == 0){
                return Integer.compare(x.getId(), y.getId());
            }

            return compare;
        }).collect(Collectors.toList());
        Assert.assertEquals(100000, count);

        long start = System.currentTimeMillis();

        List<Iterable<Battlecard>> actual = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            Iterable<Battlecard> all = ar.findFirstLeastSwag(ns.get(i));
            actual.add(all);
        }
        long end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 300);

        for(int i = 0; i < 100; i++)
        {
            Assert.assertArrayEquals(iterableToArray(expected.get(i)), iterableToArray(actual.get(i)));
        }

    }
    public Battlecard[] iterableToArray(Iterable<Battlecard> iterable){

        List<Battlecard> list = new ArrayList<Battlecard>();

        for(Battlecard b : iterable){
            list.add(b);
        }

        Battlecard[] cards = new Battlecard[list.size()];

        for(int i = 0 ; i < cards.length; i++){
            cards[i] = list.get(i);
        }

        return cards;
    }


}
