package exam_prep.mar_2018_chainblock.java.performance;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.Transaction;
import exam_prep.mar_2018_chainblock.chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Perf07 {
    //GetByTxStatus
    @Test
    public void GetByTransactionStatus_ShouldWorkFast()
    {
        IChainblock cb = new Chainblock();
        List<Transaction> txs = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++)
        {
            int amount = rand.nextInt(50000);
            Transaction tx = new Transaction(i, TransactionStatus.Successfull,
                    String.valueOf(i), String.valueOf(i), amount);

            cb.add(tx);
            txs.add(tx);
        }

        int count = cb.getCount();
        Assert.assertEquals(100000, count);
        long start = System.currentTimeMillis();

        Iterable<Transaction> byStatus = cb.getByTransactionStatus(
                TransactionStatus.Successfull);
        int c = 0;

        for (Transaction employee : byStatus)
        {
            c++;
        }

        long end = System.currentTimeMillis();
        long l1 = end - start;
        Assert.assertTrue(l1 < 150);
        Assert.assertEquals(100000, c);
    }
}
