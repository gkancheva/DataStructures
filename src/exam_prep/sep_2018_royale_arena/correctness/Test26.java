package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.Battlecard;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test26 {
    @Test(expected = IllegalArgumentException.class)
    public void GetByTypeAndDamageRangeOrderedByDamageThenById_ShouldThrow_AfterRemovingReceiver()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        Battlecard cd1 = new Battlecard(5, CardType.SPELL, "joro", 1d, 5d);
        Battlecard cd2 = new Battlecard(6, CardType.RANGED, "joro",5.5, 5d);
        Battlecard cd3 = new Battlecard(7, CardType.MELEE, "joro", 5.5, 10d);
        //Act
        RA.add(cd1);
        RA.add(cd3);
        RA.add(cd2);
        RA.removeById(5);
        //Assert

        RA.getByTypeAndDamageRangeOrderedByDamageThenById(CardType.SPELL, 0, 20);

    }

}
