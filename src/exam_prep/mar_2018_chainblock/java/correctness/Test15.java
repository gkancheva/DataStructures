package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test15 {
    @Test
    public void GetAllOrderedByAmountDescendingThenById_ShouldReturnEmpty_OnEmptyCB()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        //Act
        //Assert
        List<Transaction> result = new ArrayList<>();
        Iterable<Transaction> res = cb.getAllOrderedByAmountDescendingThenById();
        for(Transaction tx : res){
            result.add(tx);
        }
        Transaction[] actual = new Transaction[result.size()];
        for(int i = 0 ; i < result.size(); i++){
            actual[i] = result.get(i);
        }
        Assert.assertEquals(new Transaction[0], actual);
    }

}
