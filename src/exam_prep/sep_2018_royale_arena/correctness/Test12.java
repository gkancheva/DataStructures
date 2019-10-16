package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test12 {


    @Test(expected = IllegalArgumentException.class)
    public void getById_On_Empty_RoyaleArena_ShouldThrow()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        //Assert
        RA.getById(5);
    }


}
