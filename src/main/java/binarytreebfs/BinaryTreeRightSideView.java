package binarytreebfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.TreeNode;

/** 199. Binary Tree Right Side View https://leetcode.com/problems/binary-tree-right-side-view */
public class BinaryTreeRightSideView {

    // Key = tree level, value = visible element
    private Map<Integer, Integer> levelValue;

    public BinaryTreeRightSideView() {
        this.levelValue = new HashMap<>();
    }

    public void reset() {
        this.levelValue = new HashMap<>();
    }

    /**
     * DFS traversal of binary tree, right subtree first, then left. Populates the levelValue map.
     */
    private void traverseDfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        traverseDfs(node.right, level + 1);
        if (!levelValue.containsKey(level)) {
            levelValue.put(level, node.val);
        }
        traverseDfs(node.left, level + 1);
    }

    /**
     * Given a binary tree, imagine you are standing on the right side of it. Return a list
     * containing the elements you can see from here, from top to bottom. I know that this problem
     * is supposed to be a BFS problem, but my first idea was a solution using DFS, so I'll try
     * implementing that first. Do a DFS traversal, visiting the right subtree, then root, then left
     * subtree, and keep track of which level we're on and if we have seen any elements on this
     * level yet
     *
     * @param root the root node of the binary tree
     * @return the elements of the binary tree visible from the right, from top to bottom
     */
    public List<Integer> rightSideViewAttemptOne(TreeNode root) {
        traverseDfs(root, 0);
        List<Integer> visibleValues = new ArrayList<>();
        int i = 0;
        while (levelValue.containsKey(i)) {
            visibleValues.add(levelValue.get(i));
            i++;
        }
        return visibleValues;
    }

    private void traverseBfs(List<TreeNode> levelNodes, List<Integer> visibleElements) {
        if (levelNodes.isEmpty()) {
            return;
        }
        visibleElements.add(levelNodes.getLast().val);
        List<TreeNode> nextLevelNodes = new ArrayList<>();
        for (TreeNode node : levelNodes) {
            // nodes should be added from left to right
            if (node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if (node.right != null) {
                nextLevelNodes.add(node.right);
            }
        }
        traverseBfs(nextLevelNodes, visibleElements);
    }

    /**
     * Given a binary tree, imagine you are standing on the right side of it. Return a list
     * containing the elements you can see from here, from top to bottom. Since this is supposed to
     * be a BFS problem, solve it in a different way. Visit one level at a time. At each level, the
     * visible element will be the one furthest to the right.
     *
     * @param root the root node of the binary tree
     * @return the elements of the binary tree visible from the right, from top to bottom
     */
    public List<Integer> rightSideViewAttemptTwo(TreeNode root) {
        List<Integer> visibleElements = new ArrayList<>();
        traverseBfs(Collections.singletonList(root), visibleElements);
        return visibleElements;
    }

    public static void main(String[] args) {
        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();

        TreeNode binaryTree =
                new TreeNode(
                        1,
                        new TreeNode(2, null, new TreeNode(5)),
                        new TreeNode(3, null, new TreeNode(4)));
        System.out.printf(
                "Binary tree (rotated counter-clockwise):\n%s\nElements seen from the right:\n%s\n\n",
                TreeNode.treeToString(binaryTree), solution.rightSideViewAttemptTwo(binaryTree));
        solution.reset();

        binaryTree =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(4, new TreeNode(5), null), null),
                        new TreeNode(3, null, null));
        System.out.printf(
                "Binary tree (rotated counter-clockwise):\n%s\nElements seen from the right:\n%s\n\n",
                TreeNode.treeToString(binaryTree), solution.rightSideViewAttemptTwo(binaryTree));
        solution.reset();
    }
}
