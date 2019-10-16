package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test19 {

    @Test
    public void GetAllInSwagRange_ShouldReturn_EmptyCollectionOnNonExistingRange()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 2d, 1d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "joro", 3d, 2d);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 4d, 5.5);
        Battlecard cd4 = new Battlecard(12, CardType.SPELL, "joro", 5d, 15.6);
        Battlecard cd5 = new Battlecard(15, CardType.SPELL, "joro", 6d, 7.8);
        Battlecard[] expected = new Battlecard[0];
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        Battlecard[] actual = iterableToArray(RA.getAllInSwagRange(7.7, 7.75));
        //Assert
        Assert.assertArrayEquals(expected, actual);
        RA.removeById(12);
        RA.removeById(15);
        actual = iterableToArray(RA.getAllInSwagRange(7.8, 16));
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
