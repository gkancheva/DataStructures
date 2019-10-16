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

public class Perf05 {
    //RemoveById
    @Test
    public void RemoveById_ShoudlWorkFast()
    {

        IChainblock cb = new Chainblock();
        List<Transaction> txs = new ArrayList<Transaction>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++)
        {
            int amount = rand.nextInt(60000);
            Transaction tx = new Transaction(i, TransactionStatus.Successfull,
                    String.valueOf(i), String.valueOf(i), amount);
            cb.add(tx);
            txs.add(tx);
        }

        int count = cb.getCount();
        Assert.assertEquals(100000, count);

        long start = System.currentTimeMillis();

        for (Transaction tx : txs)
        {
            cb.removeTransactionById(tx.getId());
        }
        try {

            long end = System.currentTimeMillis();
            long l1 = end - start;
            Assert.assertTrue(l1 < 200);
        } catch (Exception e) {

        }
    }
}
