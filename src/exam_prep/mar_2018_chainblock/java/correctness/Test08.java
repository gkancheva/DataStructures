package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import org.junit.Assert;
import org.junit.Test;

public class Test08 {
    @Test
    public void Count_Should_Be_0_On_EmptyCollection()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        //Act
        //Assert
        Assert.assertEquals(0, cb.getCount());
    }
}
