package exam_prep.mar_2018_chainblock.java.performance;

import exam_prep.mar_2018_chainblock.chainblock.Chainblock;
import exam_prep.mar_2018_chainblock.chainblock.IChainblock;
import exam_prep.mar_2018_chainblock.chainblock.Transaction;
import exam_prep.mar_2018_chainblock.chainblock.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Perf12 {
    //GetByReceiverOrderedByAmountThenById
    @Test
    public void GetByReceiverOrderedByAmountThenById_ShouldWorkFast()
    {
        IChainblock cb = new Chainblock();
        List<Transaction> txs = new ArrayList<Transaction>();
        for (int i = 0; i < 100000; i++)
        {
            Transaction tx = new Transaction(i, TransactionStatus.Successfull,
                    String.valueOf(i), "to", i);
            cb.add(tx);
            txs.add(tx);
        }

        int count = cb.getCount();
        txs = txs.stream().sorted((x,y) -> {
            int compare = Double.compare(y.getAmount(),x.getAmount());
            if(compare == 0){
                return Integer.compare(x.getId(), y.getId());
            }
            return compare;
        }).collect(Collectors.toList());
        Assert.assertEquals(100000, count);
        long start = System.currentTimeMillis();

        Iterable<Transaction> all = cb.getByReceiverOrderedByAmountThenById("to");
        int c = 0;
        for (Transaction tx : all)
        {
            Assert.assertSame(tx, txs.get(c));
            c++;
        }

        long end = System.currentTimeMillis();
        long l1 = end - start;

        Assert.assertTrue(l1 < 200);
        Assert.assertEquals(100000, c);
    }

}
