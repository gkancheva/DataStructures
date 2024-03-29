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

public class Perf06 {
    //GetById
    @Test
    public void GetById_ShouldWorkFast()
    {

        IChainblock cb = new Chainblock();
        TransactionStatus[] statuses = new TransactionStatus[]
                {
                        TransactionStatus.Aborted,
                        TransactionStatus.Failed,
                        TransactionStatus.Successfull,
                        TransactionStatus.Unauthorized
                };
        Random rand = new Random();
        List<Transaction> txs = new ArrayList<Transaction>();
        for (int i = 0; i < 100000; i++)
        {
            int status = rand.nextInt(4);
            Transaction tx = new Transaction(i, statuses[status],
                    String.valueOf(i), String.valueOf(i), i);
            cb.add(tx);
            txs.add(tx);
        }

        int count = cb.getCount();
        Assert.assertEquals(100000, count);

        long start = System.currentTimeMillis();

        for (Transaction tx : txs)
        {
            Assert.assertSame(tx, cb.getById(tx.getId()));
        }

        long end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 150);
    }
}
