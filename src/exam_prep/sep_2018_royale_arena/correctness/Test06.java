package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

public class Test06 {

    @Test
    public void contains_OnExistingElement_ShouldReturnTrue()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 6d, 5d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "joro", 7d, 5d);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 8d, 5d);
        //Act
        RA.add(cd1);
        RA.add(cd2);
        RA.add(cd3);
        //Assert
        Assert.assertTrue(RA.contains(cd1));
        Assert.assertFalse(RA.contains(new Battlecard(3, CardType.BUILDING, "ta", 6d, 52.2)));
        Assert.assertTrue(RA.contains(cd2));
        Assert.assertFalse(RA.contains(new Battlecard(0, CardType.RANGED, "b", 7d, 5d)));
    }

}
