package labs.l03_basic_trees.p01_root_node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int count = Integer.parseInt(br.readLine());

        Map<Integer, Tree<Integer>> map = new HashMap<>();

        fillTreeMap(count, map);

        //p01_findRootNode(map);
        //p02_printTree(map);
        //p03_printLeafNodes(map);
        //p04_middleNodes(map);
        //p05_deepestNode(map);
        //p06_longestPath(map);
        //p07_pathsWithGivenSum(map);
        p08_subtreesWithGivenSum(map);

    }

    private static void p08_subtreesWithGivenSum(Map<Integer, Tree<Integer>> map) throws IOException {
        int sum = Integer.parseInt(br.readLine());
        System.out.println("Subtrees of sum " + sum + ":");
        Tree<Integer> rootNode = findRootNode(map);
        findSubtrees(rootNode, sum);
    }


    private static int findSubtrees(Tree<Integer> node, int sum) {
        int currentSum = node.getValue();
        for (Tree<Integer> child : node.getChildren()) {
            currentSum += findSubtrees(child, sum);
        }
        if(currentSum == sum) {
            List<Integer> subtree = new ArrayList<>();
            getSubtree(node, subtree);
            System.out.println(subtree.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", ""));
        }
        return currentSum;
    }

    private static void getSubtree(Tree<Integer> node, List<Integer> result) {
        result.add(node.getValue());
        for (Tree<Integer> child : node.getChildren()) {
            getSubtree(child, result);
        }
    }

    private static String printTreeInPreOrder(Tree<Integer> subTree) {
        StringBuilder sb = new StringBuilder();
        sb.append(subTree.getValue()).append(" ");
        for (Tree<Integer> child : subTree.getChildren()) {
            sb.append(printTreeInPreOrder(child));
        }
        return sb.toString();
    }

    private static int calculateSumOfNodes(Tree<Integer> subTree) {
        int result = subTree.getValue();
        for (Tree<Integer> child : subTree.getChildren()) {
            result += calculateSumOfNodes(child);
        }
        return result;
    }

    private static void p07_pathsWithGivenSum(Map<Integer, Tree<Integer>> map) throws IOException {
        int sum = Integer.parseInt(br.readLine());

        System.out.println("Paths of sum " + sum + ":");

        Tree<Integer> rootNode = findRootNode(map);
        List<Tree<Integer>> subtrees = findLeavesNodes(rootNode);

        for (Tree<Integer> node : subtrees) {
            if (calculateSumToRoot(node) == sum) {
                String result = getPrintedPathToRoot(node);
                System.out.println(result);
            }
        }

    }

    private static List<Tree<Integer>> findLeavesNodes(Tree<Integer> rootNode) {
        List<Tree<Integer>> result = new ArrayList<>();
        for (Tree<Integer> child : rootNode.getChildren()) {
            if(child.getChildren().isEmpty()) {
                result.add(child);
            } else {
                result.addAll(findLeavesNodes(child));
            }
        }
        return result;
    }

    private static int calculateSumToRoot(Tree<Integer> tree) {
        int sum = 0;
        while(tree != null) {
            sum += tree.getValue();
            tree = tree.getParent();
        }
        return sum;
    }

    private static String getPrintedPathToRoot(Tree<Integer> tree) {
        Deque<Integer> stack = getPathToRoot(tree);
        return stack.toString()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(",", "");
    }

    private static void p06_longestPath(Map<Integer, Tree<Integer>> map) {
        System.out.println("Longest path: " + getPrintedPathToRoot(findDeepestNode(map)));
    }

    private static Deque<Integer> getPathToRoot(Tree<Integer> tree) {
        Deque<Integer> stack = new LinkedList<>();
        while(tree != null) {
            stack.push(tree.getValue());
            tree = tree.getParent();
        }
        return stack;
    }

    private static void p05_deepestNode(Map<Integer, Tree<Integer>> map) {
        System.out.println("Deepest node: " + findDeepestNode(map));

    }

    private static Tree<Integer> findDeepestNode(Map<Integer, Tree<Integer>> map) {
        List<Tree<Integer>> leafsOnly = map.values().stream()
                .filter(x -> x.getChildren().isEmpty())
                .collect(Collectors.toList());

        int counter = 0;
        Tree<Integer> deepestTree = null;

        for (Tree<Integer> tree : leafsOnly) {
            int depth = calculateDepth(tree);
            if(depth > counter) {
                deepestTree = tree;
                counter = depth;
            }
        }
        return deepestTree;
    }

    private static int calculateDepth(Tree<Integer> tree) {
        int depth = 0;
        while(tree != null) {
            depth++;
            tree = tree.getParent();
        }
        return depth;
    }


    private static void p04_middleNodes(Map<Integer, Tree<Integer>> map) {
        List<Integer> middlesNodes = map.values().stream()
                .filter( x -> x.getParent() != null && x.getChildren().size() > 0)
                .sorted((x,y) -> x.getValue().compareTo(y.getValue()))
                .map(Tree::getValue)
                .collect(Collectors.toList());

        System.out.println("Middle nodes: " +
                middlesNodes.toString().replaceAll("[\\[\\],]", ""));
    }

    private static void p03_printLeafNodes(Map<Integer, Tree<Integer>> map) {
        List<Tree<Integer>> leafNodes = map.values().stream()
                .filter(x -> x.getChildren().size() == 0)
                .sorted((x,y) -> x.getValue().compareTo(y.getValue()))
                .collect(Collectors.toList());

        System.out.print("Leaf nodes: ");

        for (Tree<Integer> leafNode : leafNodes) {
            System.out.print(leafNode.getValue() + " ");
        }
    }

    private static void p02_printTree(Map<Integer, Tree<Integer>> map) {
        Tree<Integer> rootNode = findRootNode(map);
        printCurrentNode(rootNode, 0);
    }

    private static void printCurrentNode(Tree<Integer> node, int i) {
        System.out.println(new String(new char[i]).replace("\0", " ") + node.getValue());
        for (Tree<Integer> tree : node.getChildren()) {
            printCurrentNode(tree, i + 2);
        }
    }

    private static void fillTreeMap(int nodeCount, Map<Integer, Tree<Integer>> map) throws IOException {
        for (int i = 0; i < nodeCount - 1; i++) {
            List<Integer> nums = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt)
                    .collect(Collectors.toList());
            int parent = nums.get(0);
            int child = nums.get(1);
            if(!map.containsKey(parent)) {
                map.put(parent, new Tree<>(parent));
            }
            if(!map.containsKey(child)) {
                map.put(child, new Tree<>(child));
            }

            map.get(child).setParent(map.get(parent));
            map.get(parent).getChildren().add(map.get(child));

        }
    }

    static Tree<Integer> getTreeNodeByValue(int value, Map<Integer, Tree<Integer>> map) {
        if(!map.containsKey(value)) {
            return new Tree<>(value);
        }
        return map.get(value);
    }

    private static void p01_findRootNode(Map<Integer, Tree<Integer>> map) {
        Tree<Integer> tree = findRootNode(map);
        System.out.println(tree != null ? "Root node: " + tree.getValue() : "");
    }

    private static Tree<Integer> findRootNode(Map<Integer, Tree<Integer>> map) {
        return map.values().stream().filter(x -> x.getParent() == null)
                .findFirst().orElse(null);
    }
}