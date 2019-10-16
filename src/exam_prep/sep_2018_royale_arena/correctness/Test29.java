package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test29 {



    @Test(expected = IllegalArgumentException.class)
    public void GetByName_On_NonExisting_Receiver_ShouldThrow()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(2, CardType.SPELL, "pesho", 53d, 1d);
        Battlecard cd2 = new Battlecard(1, CardType.SPELL, "mesho", 5d, 1d);
        Battlecard cd3 = new Battlecard(4, CardType.SPELL, "kalin", 6d, 15.6);
        Battlecard cd4 = new Battlecard(3, CardType.SPELL, "peshor", 6d, 15.6);
        Battlecard cd5 = new Battlecard(8, CardType.RANGED, "barko", 7d, 17.8);

        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.add(cd4);
        RA.add(cd5);
        //Assert

        RA.getByNameOrderedBySwagDescending("mecho");
    }

}
