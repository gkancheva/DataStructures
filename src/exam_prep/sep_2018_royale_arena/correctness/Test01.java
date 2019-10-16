package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;
import org.junit.Assert;
import org.junit.Test;

public class Test01 {
    //addition
    @Test
    public void add_SingleElement_ShouldWorkCorrectly() {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd = new Battlecard(5, CardType.SPELL, "joro", 5d, 5d);
        //Act
        RA.add(cd);

        //Assert
        for (Battlecard b : RA) {
            Assert.assertSame(b, cd);
        }
    }
}
