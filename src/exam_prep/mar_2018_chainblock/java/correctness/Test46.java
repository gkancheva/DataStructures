package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import org.junit.Test;

public class Test46 {
    @Test(expected = IllegalArgumentException.class)
    public void GetByReceiver_ShouldThrow_On_EmptyCB()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        //Act
        //Assert
        cb.getByReceiverOrderedByAmountThenById("pesho");
    }

    //GetBySenderAndMinimumAmountDescending
}
