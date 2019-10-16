package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test42 {

    @Test
    public void getByCardTypeAndMaximumDamage_ShouldWorkCorrectly_AfterRemove()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(2, CardType.SPELL, "valq", 14.8, 53d);
        Battlecard cd2 = new Battlecard(1, CardType.SPELL, "valq",14.8, 5d);
        Battlecard cd3 = new Battlecard(4, CardType.SPELL, "valq", 15.6, 6d);
        Battlecard cd4 = new Battlecard(3, CardType.SPELL, "valq", 15.6, 12d);
        Battlecard cd5 = new Battlecard(8, CardType.RANGED, "valq",17.8, 613d);
        Battlecard[] expected = new Battlecard[]
                {
                        cd2
                };
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        RA.removeById(cd1.getId());
        //Assert
        Battlecard[] actual = iterableToArray(RA
                .getByCardTypeAndMaximumDamage(CardType.SPELL, 15.0));
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
