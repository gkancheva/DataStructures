package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

public class Test10 {

    //getById
    @Test
    public void getById_On_ExistingElement_ShouldWorkCorrectly()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 10d, 5d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "joro", 10d, 5d);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 10d, 5d);
        //Act
        RA.add(cd1);
        RA.add(cd2);
        RA.add(cd3);
        //Assert
        Assert.assertSame(cd1, RA.getById(5));
        Assert.assertNotSame(
                new Battlecard(53, CardType.RANGED, "a", 10d, 5d),
                RA.getById(7)
        );
    }

}
