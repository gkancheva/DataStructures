package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import org.junit.Assert;
import org.junit.Test;

public class Test43 {
    @Test
    public void GetByReceiverAndAmountRange_ShouldThrow_On_EmptyCB()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        //Act
        //Assert
        boolean threw = false;
        try{
            cb.getByReceiverAndAmountRange("pesho", 0, 20).iterator().next();
        }catch(IllegalArgumentException ex){
            threw = true;
        }
        Assert.assertTrue(threw);
    }

}
