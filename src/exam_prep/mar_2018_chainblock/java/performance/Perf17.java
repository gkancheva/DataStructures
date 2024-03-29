package exam_prep.mar_2018_chainblock.java.performance;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.Transaction;
import exam_prep.mar_2018_chainblock.chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Perf17 {
    //GetAllReceiversWithTransactionStatus
    @Test
    public void GetAllReceiversWithTransactionStatus_ShouldWorkFast()
    {
        IChainblock cb = new Chainblock();
        List<Transaction> txs = new ArrayList<Transaction>();
        TransactionStatus[] statuses = new TransactionStatus[]
                {
                        TransactionStatus.Aborted,
                        TransactionStatus.Failed,
                        TransactionStatus.Successfull,
                        TransactionStatus.Unauthorized
                };
        Random rand = new Random();
        for (int i = 0; i < 100000; i++)
        {
            int status = rand.nextInt(4);
            TransactionStatus TS = statuses[status];
            Transaction tx = new Transaction(i, TS,
                    String.valueOf(i), String.valueOf(i), i);
            cb.add(tx);
            if (status == 2) txs.add(tx);
        }
        Collections.reverse(txs);
        int count = cb.getCount();
        Assert.assertEquals(100000, count);
        long start = System.currentTimeMillis();

        Iterable<String> all = cb.getAllReceiversWithTransactionStatus(TransactionStatus.Successfull);
        int c = 0;
        for (String tx : all)
        {
            Assert.assertEquals(tx, txs.get(c).getReceiver());
            c++;
        }

        long end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 150);
        Assert.assertEquals(txs.size(), c);
    }
}
