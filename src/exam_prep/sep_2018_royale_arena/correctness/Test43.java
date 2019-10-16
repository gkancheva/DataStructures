package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test43 {

    @Test(expected = IllegalArgumentException.class)
    public void getByCardTypeAndMaximumDamage_ShouldThrowOnEmptyCollection()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(2, CardType.SPELL, "valq", 14.8, 6d);
        Battlecard cd2 = new Battlecard(1, CardType.SPELL, "valq", 14.8, 7d);
        Battlecard cd3 = new Battlecard(4, CardType.SPELL, "valq", 15.6, 8d);
        Battlecard cd4 = new Battlecard(3, CardType.SPELL, "valq", 15.6, 9d);
        Battlecard cd5 = new Battlecard(8, CardType.RANGED, "valq",17.8, 10d);
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        //Assert
        Battlecard[] actual = iterableToArray(RA
                .getByCardTypeAndMaximumDamage(CardType.BUILDING,5));
        Assert.assertArrayEquals(new Battlecard[0], actual);
        RA = new RoyaleArena();
        actual = iterableToArray(RA
                .getByCardTypeAndMaximumDamage(CardType.BUILDING,10));
        Assert.assertArrayEquals(new Battlecard[0], actual);
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
