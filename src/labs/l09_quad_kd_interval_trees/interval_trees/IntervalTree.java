package labs.l09_quad_kd_interval_trees.interval_trees;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IntervalTree {

    private Node root;

    public void insert(double lo, double hi) {
        this.root = this.insert(this.root, lo, hi);
    }

    public void eachInOrder(Consumer<Interval> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public Interval searchAny(double lo, double hi) {
        Node current = this.root;
        while(current != null && !current.interval.intersects(lo, hi)) {
            if(current.left != null && current.left.max > lo) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current == null ? null : current.interval;
    }

    public Iterable<Interval> searchAll(double lo, double hi) {
        List<Interval> intervals = new ArrayList<>();
        this.searchAll(this.root, intervals, new Interval(lo, hi));

        return intervals;
    }

    private void searchAll(Node node, List<Interval> result, Interval interval) {
        if(node == null) {
            return;
        }
        if(node.left != null && node.left.max > interval.getLo()) {
            this.searchAll(node.left, result, interval);
        }

        if(node.interval.intersects(interval)) {
            result.add(node.interval);
        }

        if(node.right != null && node.right.interval.getLo() < interval.getHi()) {
            this.searchAll(node.right, result, interval);
        }
    }

    private void eachInOrder(Node node, Consumer<Interval> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.interval);
        this.eachInOrder(node.right, consumer);
    }

    private Node insert(Node node, double lo, double hi) {
        if (node == null) {
            return new Node(new Interval(lo, hi));
        }
        int cmp = Double.compare(lo, node.interval.getLo());
        if (cmp < 0) {
            node.left = insert(node.left, lo, hi);
        } else if (cmp > 0) {
            node.right = insert(node.right, lo, hi);
        }
        this.updateMax(node);
        return node;
    }

    private void updateMax(Node node) {
        double max = Math.max(this.getMax(node.left), this.getMax(node.right));
        node.max = Math.max(max, node.max);
    }

    private double getMax(Node node) {
        return node == null ? 0 : node.max;
    }

    private class Node {

        private Interval interval;
        private double max;
        private Node right;
        private Node left;

        public Node(Interval interval) {
            this.interval = interval;
            this.max = interval.getHi();
        }
    }
}
