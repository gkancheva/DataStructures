package exam_prep.mar_2018_chainblock.chainblock;

import java.util.*;
import java.util.stream.Collectors;

public class Chainblock implements IChainblock {

    private List<Transaction> transactions;
    private Map<Integer, Transaction> byId;
    private Map<TransactionStatus, List<Transaction>> byStatus;
    private Map<String, List<Transaction>> bySender;
    private Map<String, List<Transaction>> byReceiver;

    public Chainblock() {
        this.transactions = new LinkedList<>();
        this.byId = new HashMap<>();
        this.byStatus = new HashMap<>();
        this.byStatus.put(TransactionStatus.Aborted, new LinkedList<>());
        this.byStatus.put(TransactionStatus.Failed, new LinkedList<>());
        this.byStatus.put(TransactionStatus.Successfull, new LinkedList<>());
        this.byStatus.put(TransactionStatus.Unauthorized, new LinkedList<>());
        this.bySender = new HashMap<>();
        this.byReceiver = new HashMap<>();
    }

    private Comparator<Transaction> comparator = (o1, o2) -> {
        int compare = Double.compare(o2.getAmount(), o1.getAmount());
        if(compare == 0) {
            return o1.getId() - o2.getId();
        }
        return compare;
    };

    @Override
    public int getCount() {
        return this.byId.size();
    }

    @Override
    public void add(Transaction tx) {
        this.transactions.add(tx);
        this.byId.put(tx.getId(), tx);
        this.byStatus.get(tx.getStatus()).add(tx);
        this.bySender.putIfAbsent(tx.getSender(), new LinkedList<>());
        this.bySender.get(tx.getSender()).add(tx);
        this.byReceiver.putIfAbsent(tx.getReceiver(), new LinkedList<>());
        this.byReceiver.get(tx.getReceiver()).add(tx);
    }

    @Override
    public boolean contains(Transaction tx) {
        return this.contains(tx.getId());
    }

    @Override
    public boolean contains(int id) {
        return this.byId.containsKey(id);
    }

    @Override
    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        Transaction transaction = this.byId.get(id);
        if(transaction == null) {
            throw new IllegalArgumentException();
        }
        this.byStatus.get(transaction.getStatus()).remove(transaction);
        transaction.setStatus(newStatus);
        this.byId.put(id, transaction);
        this.byStatus.get(transaction.getStatus()).add(transaction);
    }

    @Override
    public void removeTransactionById(int id) {
        Transaction transaction = this.byId.remove(id);
        this.transactions.remove(transaction);
        this.byStatus.get(transaction.getStatus()).remove(transaction);
        this.bySender.get(transaction.getSender()).remove(transaction);
        this.byReceiver.get(transaction.getReceiver()).remove(transaction);
    }

    @Override
    public Transaction getById(int id) {
        if(!this.byId.containsKey(id)) {
            throw new IllegalArgumentException();
        }
        return this.byId.get(id);
    }

    @Override
    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        if(this.byStatus.get(status).isEmpty()) {
            throw new IllegalArgumentException();
        }
        return this.byStatus.get(status).stream()
                .sorted((x,y) -> Double.compare(y.getAmount(), x.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        if(!this.byStatus.containsKey(status)) {
            throw new IllegalArgumentException();
        }
        List<String> result = this.byStatus.get(status)
                .stream()
                .sorted((x,y) -> Double.compare(y.getAmount(), x.getAmount()))
                .map(Transaction::getSender)
                .collect(Collectors.toList());
        if(result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> result = this.byStatus.get(status)
                .stream()
                .sorted((x,y) -> Double.compare(y.getAmount(), x.getAmount()))
                .map(Transaction::getReceiver)
                .collect(Collectors.toList());
        if(result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.transactions.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        if(!this.bySender.containsKey(sender)) {
            throw new IllegalArgumentException();
        }
        List<Transaction> result = this.bySender.get(sender)
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if(result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        if(!this.byReceiver.containsKey(receiver)) {
            throw new IllegalArgumentException();
        }
        List<Transaction> result = this.byReceiver.get(receiver)
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        if(result.isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return result;
    }

    @Override
    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.byStatus.get(status)
                .stream().filter(x -> x.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        if(!this.bySender.containsKey(sender)) {
            throw new IllegalArgumentException();
        }
        List<Transaction> result = this.bySender.get(sender)
                .stream()
                .filter(x -> x.getAmount() > amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if(result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        if(!this.byReceiver.containsKey(receiver)) {
            throw new IllegalArgumentException();
        }
        List<Transaction> result = this.byReceiver.get(receiver)
                .stream()
                .filter(x -> x.getAmount() >= lo && x.getAmount() < hi)
                .sorted(comparator)
                .collect(Collectors.toList());
        if(result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        List<Transaction> result = this.transactions.stream()
                .filter(x -> x.getAmount() >= lo && x.getAmount() <= hi)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Iterator<Transaction> iterator() {
        return this.transactions.iterator();
    }
}
