package exam_prep.sep_2018_royale_arena.correctness;

import exam_prep.sep_2018_royale_arena.Arena;
import exam_prep.sep_2018_royale_arena.CardType;
import exam_prep.sep_2018_royale_arena.RoyaleArena;

import org.junit.Test;

public class Test34 {


    @Test(expected = IllegalArgumentException.class)
    public void getByCardTypeAndMaximumDamage_ShouldThrowOnEmpty_RA()
    {
        //Arrange
        Arena RA = new RoyaleArena();
        //Act
        //Assert

        RA.getByCardTypeAndMaximumDamage(CardType.MELEE,5);
    }



}
