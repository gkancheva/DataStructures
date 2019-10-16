package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test23 {
    @Test(expected = IllegalArgumentException.class)
    public void FindFirstLeastSwag_ShoudlThrowAfterRemove()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 5d, 1d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "joro", 6d, 5.5);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 7d, 5.5);
        Battlecard cd4 = new Battlecard(12, CardType.SPELL, "joro", 8d, 15.6);
        Battlecard cd5 = new Battlecard(15, CardType.RANGED, "joro", 12d, 7.8);
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        RA.removeById(5);
        RA.removeById(7);
        RA.removeById(6);
        RA.removeById(12);
        RA.removeById(15);
        //Assert

        RA.findFirstLeastSwag(1);
    }


}
