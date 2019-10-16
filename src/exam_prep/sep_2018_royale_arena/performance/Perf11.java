package exam_prep.sep_2018_royale_arena.performance;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Perf11 {


    //GetByNameOrderedBySwagDescending
    @Test
    public void GetByNameAndSwagRange_ShouldWorkFast()
    {
        Arena ar = new RoyaleArena();
        List<List<Battlecard>> cds = new ArrayList<>();
        List<Pair<Integer, Integer>> ranges = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100; i++)
        {
            Pair<Integer, Integer> range = new Pair<Integer, Integer>(
                    ThreadLocalRandom.current().nextInt(100, 400),
                    ThreadLocalRandom.current().nextInt(500, 1000));
            ranges.add(range);
            List<Battlecard> cd = new ArrayList<>();
            for (int j = 0; j < 100; j++)
            {
                int amount = ThreadLocalRandom.current().nextInt(range.getKey(), range.getValue());
                Battlecard card = new Battlecard(i*100 + j, CardType.SPELL,
                        String.valueOf(i), (double)i, (double)amount);
                cd.add(card);
                ar.add(card);
            }
            cds.add(cd.stream().sorted(Comparator
                    .comparing(Battlecard::getSwag).reversed()
                    .thenComparing(Battlecard::getId)).collect(Collectors.toList()));
        }

        int count = ar.getCount();
        Assert.assertEquals(10000, count);

        long start = System.currentTimeMillis();

        List<Iterable<Battlecard>> results = new ArrayList<>();
        for(int i =0; i < 100; i++)
        {
            Pair<Integer, Integer> range = ranges.get(i);
            Iterable<Battlecard> all = ar.getByNameAndSwagRange(String.valueOf(i), range.getKey(), range.getValue());
            results.add(all);
        }
        long end = System.currentTimeMillis();
        int c = 0;
        for(int i = 0; i < 100; i++)
        {
            Assert.assertArrayEquals(iterableToArray(cds.get(i)), iterableToArray(results.get(i)));
            c++;
        }

        long l1 = end - start;

        Assert.assertTrue(l1 < 150);
        Assert.assertEquals(cds.size(), c);
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
