package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.Transaction;
import exam_prep.mar_2018_chainblock.chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;


public class Test01
{

    //addition
    @Test
    public void add_SingleElement_ShouldWorkCorrectly()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        Transaction tx = new Transaction(5, TransactionStatus.Successfull, "joro", "pesho", 5);
        //Act
        cb.add(tx);

        //Assert
        for (Transaction transaction : cb) {
            Assert.assertSame(transaction, tx);
        }
    }

}