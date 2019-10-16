package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

public class Test07 {


    //Count
    @Test
    public void Count_Should_IncreaseOnMultiple_Elements()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 3d, 5d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "joro", 8d, 5d);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 9d, 5d);
        //Act
        RA.add(cd1);
        RA.add(cd2);
        RA.add(cd3);
        //Assert
        Assert.assertEquals(3, RA.getCount());
    }

}
