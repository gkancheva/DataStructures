package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test21 {

    //FindFirstLeastSwag
    @Test
    public void FindFirstLeastSwag_ShouldWorkCorrectly()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 6d, 1d);
        Battlecard cd2 = new Battlecard(6, CardType.MELEE, "joro", 7d, 5.5);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 11d, 5.5);
        Battlecard cd4 = new Battlecard(12, CardType.BUILDING, "joro", 12d, 15.6);
        Battlecard cd5 = new Battlecard(15, CardType.BUILDING, "moro", 13d, 7.8);
        Battlecard[] expected = new Battlecard[]
                {
                        cd1,cd2,cd3,cd5
                };
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        Battlecard[] actual = iterableToArray(RA.findFirstLeastSwag(4));
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
