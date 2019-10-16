package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test24 {


    //GetByTypeAndDamageRangeOrderedByDamageThenById
    @Test
    public void GetByTypeAndDamageRangeOrderedByDamageThenById_ShouldWorkCorrectly_On_CorrectRange()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 1d, 5d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "joro", 5.5, 6d);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 5.5, 7d);
        Battlecard cd4 = new Battlecard(12, CardType.SPELL, "joro", 15.6, 10d);
        Battlecard cd5 = new Battlecard(15, CardType.RANGED, "joro", 7.8, 6d);
        Battlecard[] expected = new Battlecard[]
                {
                        cd4, cd2, cd3, cd1
                };
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        //Assert
        Battlecard[] actual = iterableToArray(RA
                .getByTypeAndDamageRangeOrderedByDamageThenById(CardType.SPELL, 0d, 20d));
        Assert.assertArrayEquals(expected, actual);

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
