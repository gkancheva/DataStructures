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

public class Perf16 {
    @Test
    public void GetAllInAmountRange()
    {
        IChainblock cb = new Chainblock();
        List<Transaction> txs = new ArrayList<Transaction>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++)
        {
            int amount = rand.nextInt(1000);
            Transaction tx = new Transaction(i, TransactionStatus.Successfull,
                    "sender", "from", amount);
            cb.add(tx);
            if (amount >= 200 && amount <= 600) txs.add(tx);
        }
        int count = cb.getCount();
        Assert.assertEquals(100000, count);
        long start = System.currentTimeMillis();

        Iterable<Transaction> all = cb.getAllInAmountRange(200, 600);
        int c = 0;
        for (Transaction tx : all)
        {
            Assert.assertSame(tx, txs.get(c));
            c++;
        }

        long end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 150);
        Assert.assertEquals(txs.size(), c);
    }

}
