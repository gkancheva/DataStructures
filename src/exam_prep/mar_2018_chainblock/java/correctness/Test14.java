package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.Transaction;
import exam_prep.mar_2018_chainblock.chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

public class Test14 {
    @Test
    public void ChangeTransactionStatus_OnMultipleTransactions_ShouldWorkCorrectly()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        Transaction tx1 = new Transaction(5, TransactionStatus.Successfull, "joro", "pesho", 5);
        Transaction tx2 = new Transaction(6, TransactionStatus.Successfull, "joro", "pesho", 5);
        Transaction tx3 = new Transaction(7, TransactionStatus.Successfull, "joro", "pesho", 5);
        //Act
        cb.add(tx1);
        cb.add(tx2);
        cb.add(tx3);
        cb.changeTransactionStatus(7, TransactionStatus.Unauthorized);
        cb.changeTransactionStatus(5, TransactionStatus.Aborted);
        cb.changeTransactionStatus(6, TransactionStatus.Successfull);
        //Assert
        Assert.assertEquals(3, cb.getCount());
        Assert.assertEquals(tx1.getStatus(), TransactionStatus.Aborted);
        Assert.assertEquals(tx3.getStatus(), TransactionStatus.Unauthorized);
        Assert.assertEquals(tx2.getStatus(), TransactionStatus.Successfull);
    }
}
