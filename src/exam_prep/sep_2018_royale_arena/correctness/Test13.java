package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

public class Test13 {

    //ChangecdStatus
    @Test
    public void ChangeCardType_ShouldWorkCorrectly_On_Existingcd()
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
        RA.changeCardType(5, CardType.MELEE);
        //Assert
        Assert.assertEquals(CardType.MELEE, cd1.getType());
        Assert.assertEquals(3, RA.getCount());
    }

}
