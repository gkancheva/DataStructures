package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import org.junit.Assert;
import org.junit.Test;

public class Test30 {
    @Test
    public void GetBySenderAndMinimumAmountDescending_ShouldThrowOnEmpty_CB()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        //Act
        //Assert
        boolean threw = false;
        cb.getBySenderAndMinimumAmountDescending("pesho",5);
        Assert.assertTrue(threw);
    }
}
