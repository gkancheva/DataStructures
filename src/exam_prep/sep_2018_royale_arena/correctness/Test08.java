package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;


public class Test08 {

    @Test
    public void Count_Should_Be_0_On_EmptyCollection()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        //Assert
        Assert.assertEquals(0, RA.getCount());
    }

}
