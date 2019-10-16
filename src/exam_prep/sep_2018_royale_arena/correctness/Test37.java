package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test37 {


    @Test(expected = IllegalArgumentException.class)
    public void GetAllByNameAndSwag_ShouldThrowOn_EmptyArena()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        //Assert

        RA.getAllByNameAndSwag();

    }

}
