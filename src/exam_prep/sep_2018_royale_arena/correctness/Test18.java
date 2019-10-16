package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test18 {

    //GetAllInSwagRange
    @Test
    public void GetInSwagRange_ShouldReturn_CorrectBattlecardsByInsertionOrder()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "dragon", 8d, 1d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "raa", 7d, 2d);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "maga", 6d, 5.5);
        Battlecard cd4 = new Battlecard(12, CardType.SPELL, "shuba", 5d, 15.6);
        Battlecard cd5 = new Battlecard(15, CardType.SPELL, "tanuki", 5d, 7.8);
        Battlecard[] expected = new Battlecard[]
                {
                        cd3,cd5
                };
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        Battlecard[] actual = iterableToArray(RA.getAllInSwagRange(5.5, 7.8));
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
