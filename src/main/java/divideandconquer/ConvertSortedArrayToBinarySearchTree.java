package divideandconquer;

import java.util.Arrays;
import utils.TreeNode;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree
 */
class ConvertSortedArrayToBinarySearchTree {

    /**
     * Recursively build a balanced BST from a sorted array of integers.
     *
     * @param nums The non-empty sorted array containing all integers that will be in the final BST
     * @param startIdx The starting index (inclusive) of array elements to build the subtree with
     * @param endIdx The ending index (exclusive) of array elements to build the subtree with
     * @return The root node of the final balanced BST
     */
    private static TreeNode buildSubtree(int[] nums, int startIdx, int endIdx) {
        // Because the array is already sorted, we can easily make a balanced BST by
        // 1. Selecting the element in the middle of the array as the root node
        // 2. Building the left subtree with the elements in the first half of the array
        // 3. Building the right subtree with the elements in the last half of the array
        // 4. Repeating the above until there's only one element remaining

        int length = endIdx - startIdx;
        // Exit condition
        if (length == 0) {
            return null;
        }

        int midIdx = startIdx + (length / 2);
        TreeNode rootNode = new TreeNode(nums[midIdx]);
        rootNode.left = buildSubtree(nums, startIdx, midIdx);
        rootNode.right = buildSubtree(nums, midIdx + 1, endIdx);
        return rootNode;
    }

    /**
     * Build a balanced BST from a sorted array of integers.
     *
     * @param nums array of integers sorted in strictly ascending order
     * @return the root node of a balanced binary search tree containing the integers
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        return buildSubtree(nums, 0, nums.length);
    }

    public static void main(String[] args) {
        int[] nums = new int[] {100};
        System.out.printf(
                "Numbers: %s\nBalanced BST:\n%s\n\n",
                Arrays.toString(nums),
                TreeNode.treeToString(ConvertSortedArrayToBinarySearchTree.sortedArrayToBST(nums)));

        nums = new int[] {1, 2};
        System.out.printf(
                "Numbers: %s\nBalanced BST:\n%s\n\n",
                Arrays.toString(nums),
                TreeNode.treeToString(ConvertSortedArrayToBinarySearchTree.sortedArrayToBST(nums)));

        nums = new int[] {-10, -3, 0, 5, 9};
        System.out.printf(
                "Numbers: %s\nBalanced BST:\n%s\n\n",
                Arrays.toString(nums),
                TreeNode.treeToString(ConvertSortedArrayToBinarySearchTree.sortedArrayToBST(nums)));

        nums = new int[] {-100, -100, -50, -50};
        System.out.printf(
                "Numbers: %s\nBalanced BST:\n%s\n\n",
                Arrays.toString(nums),
                TreeNode.treeToString(ConvertSortedArrayToBinarySearchTree.sortedArrayToBST(nums)));
    }
}
