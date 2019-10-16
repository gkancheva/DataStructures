package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test27 {


    @Test(expected = IllegalArgumentException.class)
    public void GetByTypeAndDamageRangeOrderedByDamageThenById_ShouldThrow_On_EmptyRA()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        //Assert
        RA.getByTypeAndDamageRangeOrderedByDamageThenById(CardType.MELEE, 0, 20);
    }


}
