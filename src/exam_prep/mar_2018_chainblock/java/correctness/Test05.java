package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.Transaction;
import exam_prep.mar_2018_chainblock.chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

public class Test05 {
    //Contains
    @Test
    public void Contains_OnEmptyChainblock_ShouldReturnFalse()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        //Act
        //Assert
        Assert.assertFalse(cb.contains(5));
        Assert.assertFalse(cb.contains(new Transaction(3, TransactionStatus.Failed, "a", "b", 0.5)));
    }
}
