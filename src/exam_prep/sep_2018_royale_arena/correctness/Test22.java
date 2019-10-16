package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test22 {

    @Test(expected = IllegalArgumentException.class)
    public void FindFirstLeastSwag_OnNonExistantcds_ShouldThrow()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro",5d, 1d);
        Battlecard cd2 = new Battlecard(6, CardType.MELEE, "joro", 5d, 5.5);
        Battlecard cd3 = new Battlecard(7, CardType.MELEE, "joro", 10d, 5.5);
        Battlecard cd4 = new Battlecard(12, CardType.RANGED, "joro", 11d, 15.6);
        Battlecard cd5 = new Battlecard(15, CardType.SPELL, "joro", 16d, 7.8);
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        //Assert
        RA.findFirstLeastSwag(150);
    }

}
