package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

public class Test05 {

    //contains
    @Test
    public void contains_OnEmptyRoyaleArena_ShouldReturnFalse()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        //Assert
        Assert.assertFalse(RA.contains(new Battlecard(5, CardType.BUILDING, "kocho", 5d, 6.2)));
        Assert.assertFalse(RA.contains(new Battlecard(3, CardType.RANGED, "a", 6d, 0.5)));
    }

}
