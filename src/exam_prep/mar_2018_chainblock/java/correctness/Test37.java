package exam_prep.mar_2018_chainblock.java.correctness;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.Transaction;
import exam_prep.mar_2018_chainblock.chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Test37 {
    //GetBySenderWithTransactionStatus
    @Test
    public void GetAllReceiversWithTransactionStatus_ShouldWorkCorrectly()
    {
        //Arrange
        IChainblock cb = new Chainblock();
        Transaction tx1 = new Transaction(5, TransactionStatus.Successfull, "joro", "pesho", 1);
        Transaction tx2 = new Transaction(6, TransactionStatus.Aborted, "joro", "pesho", 5.5);
        Transaction tx3 = new Transaction(7, TransactionStatus.Successfull, "joro", "pesho", 5.5);
        Transaction tx4 = new Transaction(12, TransactionStatus.Unauthorized, "joro", "pesho", 15.6);
        Transaction tx5 = new Transaction(15, TransactionStatus.Unauthorized, "moro", "vesho", 7.8);
        String[] expected = new String[]{
                "pesho", "vesho"
        };
        //Act
        cb.add(tx1);
        cb.add(tx3);
        cb.add(tx2);
        cb.add(tx4);
        cb.add(tx5);
        ArrayList<String> list = new ArrayList<String>();
        Iterable<String> res = cb.getAllReceiversWithTransactionStatus(TransactionStatus.Unauthorized);
        for(String tx : res){
            list.add(tx);
        }
        String[] actual = new String[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            actual[i] = list.get(i);
        }
        //Assert
        Assert.assertArrayEquals(expected, actual);
    }

}
