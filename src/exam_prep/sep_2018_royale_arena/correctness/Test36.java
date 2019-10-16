package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class Test36 {


    @Test
    public void GetAllByNameAndSwag_ShouldWorkCorrectly_AfterRemove()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(2, CardType.SPELL, "pesho", 5d, 14.8);
        Battlecard cd2 = new Battlecard(1, CardType.SPELL, "pesho", 5d, 14.9);
        Battlecard cd3 = new Battlecard(4, CardType.SPELL, "maru", 5d, 15.6);
        Battlecard cd4 = new Battlecard(3, CardType.SPELL, "pesho", 5d, 15.6);
        Battlecard cd5 = new Battlecard(8, CardType.RANGED, "pesho", 5d, 17.8);
        Battlecard[] expected = new Battlecard[]
                {
                        cd2, cd3
                };
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        RA.removeById(8);
        RA.removeById(3);
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
