package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test16 {


    //Enumerator
    @Test
    public void RA_ShouldBeEnumeratedIn_InsertionOrder()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 5d, 5d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "joro", 6d, 5d);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 7d, 5d);
        Battlecard[] expected = new Battlecard[]
                {
                        cd1,cd3,cd2
                };
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        Battlecard[] actual = iterableToArray(RA);
        //Assert
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
