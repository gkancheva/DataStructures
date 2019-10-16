package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test30 {

    @Test(expected = IllegalArgumentException.class)
    public void GetByName_ShouldThrow_On_EmptyRA()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        //Assert

        RA.getByNameOrderedBySwagDescending("pesho");
    }


}
