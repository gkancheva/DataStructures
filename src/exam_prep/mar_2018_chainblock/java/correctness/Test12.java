package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import org.junit.Assert;
import org.junit.Test;

public class Test12 {
    @Test
    public void GetById_On_Empty_Chainblock_ShouldThrow()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        //Act
        //Assert
        boolean throwed = false;
        try{
            cb.getById(5);
        }catch(IllegalArgumentException ex){
            throwed = true;
        }
        Assert.assertTrue(throwed);
    }

}
