package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test20 {

    @Test
    public void GetAllInSwagRange_ShouldReturnEmptyEnumeration_On_EmptyRA()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        Battlecard[] actual = iterableToArray(RA.getAllInSwagRange(7.7, 7.75));
        //Assert
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
