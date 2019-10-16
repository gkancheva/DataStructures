package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test11 {


    @Test(expected = IllegalArgumentException.class)
    public void getById_On_NonExistingElement_ShouldThrow()
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
        RA.removeById(5);
        //Assert
        RA.getById(5);
    }

}
