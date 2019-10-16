package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test41 {


    //getByCardTypeAndMaximumDamage
    @Test
    public void getByCardTypeAndMaximumDamage_ShouldWorkCorrectly()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(2, CardType.SPELL, "valq", 14d, 5d);
        Battlecard cd2 = new Battlecard(1, CardType.SPELL, "valq", 14d, 5d);
        Battlecard cd3 = new Battlecard(4, CardType.SPELL, "valq", 15d, 6d);
        Battlecard cd4 = new Battlecard(3, CardType.SPELL, "valq", 15d, 7d);
        Battlecard cd5 = new Battlecard(8, CardType.SPELL, "valq", 18d, 8d);
        Battlecard[] expected = new Battlecard[]
                {
                        cd4, cd3, cd2, cd1
                };
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        //Assert
        Battlecard[] actual = iterableToArray(RA
                .getByCardTypeAndMaximumDamage(CardType.SPELL, 17));
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
