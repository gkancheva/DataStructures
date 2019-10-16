package labs.l05_priority_queue_heaps.exercises;

import java.util.*;

public class AStar {

    private char[][] maze;
    private Map<Node, Node> path;
    private PriorityQueue<Node> pQueue;

    public AStar(char[][] map) {
        this.maze = map;
        this.path = new HashMap<>();
        this.pQueue = new PriorityQueue<>();
    }

    public static int getH(Node current, Node goal) {
        int deltaRow = Math.abs(current.getRow() - goal.getRow());
        int deltaCow = Math.abs(current.getCol() - goal.getCol());
        return deltaRow + deltaCow;

    }

    public Iterable<Node> getPath(Node start, Node goal) {
        Node current = start;
        this.pQueue.enqueue(current);
        List<Node> path = new ArrayList<>();
        while(this.pQueue.size() != 0) {
            current = this.pQueue.dequeue();
            if(this.maze[current.getRow()][current.getCol()] == '*') {
                break;
            }
            checkAdjacentNode(new Node(current.getRow() + 1, current.getCol()), goal, start, current);
            checkAdjacentNode(new Node(current.getRow(), current.getCol() + 1), goal, start, current);
            checkAdjacentNode(new Node(current.getRow() - 1, current.getCol()), goal, start, current);
            checkAdjacentNode(new Node(current.getRow(), current.getCol() - 1), goal,start, current);
        }
        if(this.path.containsKey(goal)) {
            path.add(current);
            while(true) {
                if(this.path.containsKey(current)) {
                    Node parent = this.path.get(current);
                    path.add(parent);
                    current = parent;
                } else {
                    break;
                }
            }
            Collections.reverse(path);
        } else {
            path.add(null);
        }
        return path;
    }

    private void checkAdjacentNode(Node node, Node goal, Node start, Node parent) {
        try {
            if(this.maze[node.getRow()][node.getCol()] != 'W' && this.maze[node.getRow()][node.getCol()] != 'P' && !this.path.containsKey(node)) {
                node.setF(getH(node, goal) + getH(node, start));
                this.pQueue.enqueue(node);
                this.path.put(node, parent);
            }
        } catch (IndexOutOfBoundsException e) {

        }

    }
}
