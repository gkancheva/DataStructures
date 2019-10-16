package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test15 {

    @Test(expected = IllegalArgumentException.class)
    public void ChangeCardType_On_NonExistingTranasction_ShouldThrow()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        //Assert
        RA.changeCardType(6, CardType.RANGED);
    }

}
