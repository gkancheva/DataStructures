package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;


public class Test02 {

    @Test
    public void add_SingleElement_ShouldIncreaseCountTo1()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd = new Battlecard(5, CardType.SPELL, "joro", 5d, 5d);
        //Act
        RA.add(cd);

        //Assert
        for(Battlecard b : RA)
        {
            Assert.assertSame(b, cd);
        }

        Assert.assertEquals(1, RA.getCount());
    }

}
