package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Assert;
import org.junit.Test;

public class Test14 {


    @Test
    public void ChangeCardType_OnMultipleBattlecards_ShouldWorkCorrectly()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 5d, 5d);
        Battlecard cd2 = new Battlecard(6, CardType.SPELL, "joro", 6d, 5d);
        Battlecard cd3 = new Battlecard(7, CardType.SPELL, "joro", 7d, 5d);
        //Act
        RA.add(cd1);
        RA.add(cd2);
        RA.add(cd3);
        RA.changeCardType(7, CardType.BUILDING);
        RA.changeCardType(5, CardType.MELEE);
        RA.changeCardType(6, CardType.SPELL);
        //Assert
        Assert.assertEquals(3, RA.getCount());
        Assert.assertEquals(cd1.getType(), CardType.MELEE);
        Assert.assertEquals(cd3.getType(), CardType.BUILDING);
        Assert.assertEquals(cd2.getType(), CardType.SPELL);
    }
}
