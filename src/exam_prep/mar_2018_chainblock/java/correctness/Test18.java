package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

public class Test18 {
    @Test
    public void ChangeTransactionStatus_On_NonExistingTranasction_ShouldThrow()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        //Act
        //Assert
        boolean threw = false;
        try{
            cb.changeTransactionStatus(6, TransactionStatus.Failed);
        }catch(IllegalArgumentException ex){
            threw = true;
        }
        Assert.assertTrue(threw);
    }

}
