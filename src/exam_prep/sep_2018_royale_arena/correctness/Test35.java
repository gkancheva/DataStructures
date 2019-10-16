package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test35 {


    //GetAllByNameAndSwag
    @Test
    public void GetAllByNameAndSwag_ShouldWorkCorrectly_OnExistingName()
    {
/*        //Arrange
        exam_prep.sep_2018_royale_arena.Arena RA = new exam_prep.sep_2018_royale_arena.RoyaleArena();
        exam_prep.sep_2018_royale_arena.Battlecard cd1 = new exam_prep.sep_2018_royale_arena.Battlecard(2, exam_prep.sep_2018_royale_arena.CardType.SPELL, "valq", 5d, 14.8);
        exam_prep.sep_2018_royale_arena.Battlecard cd2 = new exam_prep.sep_2018_royale_arena.Battlecard(1, exam_prep.sep_2018_royale_arena.CardType.SPELL, "moro", 5d, 14.8);
        exam_prep.sep_2018_royale_arena.Battlecard cd3 = new exam_prep.sep_2018_royale_arena.Battlecard(4, exam_prep.sep_2018_royale_arena.CardType.SPELL, "boro", 5d, 15.6);
        exam_prep.sep_2018_royale_arena.Battlecard cd4 = new exam_prep.sep_2018_royale_arena.Battlecard(3, exam_prep.sep_2018_royale_arena.CardType.SPELL, "toro", 5d, 15.6);
        exam_prep.sep_2018_royale_arena.Battlecard cd5 = new exam_prep.sep_2018_royale_arena.Battlecard(8, exam_prep.sep_2018_royale_arena.CardType.RANGED, "toro", 6d, 17.8);
        exam_prep.sep_2018_royale_arena.Battlecard[] expected = new exam_prep.sep_2018_royale_arena.Battlecard[]
                {
                        cd1, cd2, cd3, cd5
                };
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        //Assert
        exam_prep.sep_2018_royale_arena.Battlecard[] actual = iterableToArray(RA.getAllByNameAndSwag());
        Assert.assertArrayEquals(expected, actual);*/

        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(2, CardType.SPELL, "valq", 5d, 14.8);
        Battlecard cd2 = new Battlecard(1, CardType.SPELL, "moro", 5d, 14.8);
        Battlecard cd3 = new Battlecard(4, CardType.SPELL, "boro", 5d, 15.6);
        Battlecard cd4 = new Battlecard(3, CardType.SPELL, "toro", 5d, 15.6);
        Battlecard cd5 = new Battlecard(8, CardType.RANGED, "toro", 6d, 17.8);
        Battlecard[] expected = new Battlecard[]
                {
                        cd1, cd2, cd3, cd5
                };
        //Act
        RA.add(cd1);
        RA.add(cd2);
        RA.add(cd3);
        RA.add(cd4);
        RA.add(cd5);
        //Assert
        Battlecard[] actual = iterableToArray(RA.getAllByNameAndSwag());
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
