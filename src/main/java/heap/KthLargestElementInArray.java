package heap;

import java.util.Arrays;

/**
 * 215. Kth Largest Element in an Array
 * https://leetcode.com/problems/kth-largest-element-in-an-array
 */
public class KthLargestElementInArray {

    private int getIndexOfLeftChild(int i) {
        return 2*i + 1;
    }

    private int getIndexOfRightChild(int i) {
        return 2*i + 2;
    }

    private int popHeap(int[] nums, int i, boolean isMinHeap) {
        int rootValue = nums[i];
        // The root value gets removed, so it needs to be replaced
        // Work down the heap, "bubbling up" the larger/smaller values
        int leftChildIndex = getIndexOfLeftChild(i);
        int rightChildIndex = getIndexOfRightChild(i);
        // if this is a leaf node
        if (leftChildIndex >= nums.length && rightChildIndex >= nums.length) {
            // This element would need to be removed, but we are working with a fixed length array of integers
            // so override the value to infinity
            if (isMinHeap) {
                nums[i] = Integer.MAX_VALUE;
            } else {
                nums[i] = Integer.MIN_VALUE;
            }
            return rootValue;
        }
        int topChildIndex = leftChildIndex;
        if (rightChildIndex < nums.length) { // if there is a right child
            if (isMinHeap) {
                if (nums[rightChildIndex] < nums[leftChildIndex]) {
                    topChildIndex = rightChildIndex;
                }
            } else {
                if (nums[rightChildIndex] > nums[leftChildIndex]) {
                    topChildIndex = rightChildIndex;
                }
            }
        }
        // replace root node value with the top child's value, then recursively do this down to the leaf nodes
        nums[i] = nums[topChildIndex];
        popHeap(nums, topChildIndex, isMinHeap);
        return rootValue;
    }

    private void popHeapAndPushNew(int[] nums, int newVal, boolean isMinHeap) {
        // Replace the root value with the new value
        nums[0] = newVal;
        // Restore heap properties by going down the tree
        heapifyDown(nums, 0, isMinHeap);
    }

    private void heapifyDown(int[] nums, int i, boolean isMinHeap) {
        // Traverse down the heap, swapping largest/smallest child node value with parent node value
        int leftChildIndex = getIndexOfLeftChild(i);
        int rightChildIndex = getIndexOfRightChild(i);

        // if this node is a leaf node, end recursion
        if (leftChildIndex >= nums.length && rightChildIndex >= nums.length) {
            return;
        }

        int topNodeIndex = i;
        if (isMinHeap) {
            if (leftChildIndex < nums.length && nums[leftChildIndex] < nums[topNodeIndex]) {
                topNodeIndex = leftChildIndex;
            }
            if (rightChildIndex < nums.length && nums[rightChildIndex] < nums[topNodeIndex]) {
                topNodeIndex = rightChildIndex;
            }
        } else {
            if (leftChildIndex < nums.length && nums[leftChildIndex] > nums[topNodeIndex]) {
                topNodeIndex = leftChildIndex;
            }
            if (rightChildIndex < nums.length && nums[rightChildIndex] > nums[topNodeIndex]) {
                topNodeIndex = rightChildIndex;
            }
        }
        if (topNodeIndex != i) {
            // Swap root node value with greatest/smallest child node value
            int temp = nums[i];
            nums[i] = nums[topNodeIndex];
            nums[topNodeIndex] = temp;
            heapifyDown(nums, topNodeIndex, isMinHeap);
        }
    }

    private void heapify(int[] nums, boolean isMinHeap) {
        System.out.printf("Array: %s\n", Arrays.toString(nums));
        int lastParentNodeIndex = nums.length/2 - 1;
        for (int i = lastParentNodeIndex; i >= 0; i--) {
            heapifyDown(nums, i, isMinHeap);
        }
        System.out.printf("Heap: %s\n", Arrays.toString(nums));
    }

    /**
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * @param nums an array of integers (unsorted)
     * @param k the position of the integer to find
     * @return The Kth largest integer in the array
     */
    public int findKthLargestAttemptOne(int[] nums, int k) {
        /*
        Convert the array to a max heap and then pop K root elements to find the Kth largest
        (technically it isn't sorting)
         */
        heapify(nums, false);
        int popped = 0;
        for (int i = 0; i < k; i++) {
            popped = popHeap(nums, 0, false);
        }
        return popped;
    }

    /**
     * Given an integer array nums and an integer k, return the kth largest element in the array.
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * @param nums an array of integers (unsorted)
     * @param k the position of the integer to find
     * @return The Kth largest integer in the array
     */
    public int findKthLargestAttemptTwo(int[] nums, int k) {
        // Create and continuously update a min heap of size K
        int[] minHeap = new int[k];
        for (int i = 0; i < k; i++) {
            minHeap[i] = nums[i];
        }
        heapify(minHeap, true);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap[0]) {
                popHeapAndPushNew(minHeap, nums[i], true);
            }
        }

        return minHeap[0];
    }

    public static void main(String[] args) {
        KthLargestElementInArray solution = new KthLargestElementInArray();
        int[] nums = {50, 30, 40, 15, 100, 20, 25, 99};
        int k = 5;
        System.out.printf("%s largest element: %s\n\n", k, solution.findKthLargestAttemptTwo(nums, k));
        // System.out.printf("%s largest element: %s\n\n", k, solution.findKthLargestAttemptOne(nums, k));

        nums = new int[]{1};
        k = 1;
        // System.out.printf("%s largest element: %s\n\n", k, solution.findKthLargestAttemptOne(nums, k));
        System.out.printf("%s largest element: %s\n\n", k, solution.findKthLargestAttemptTwo(nums, k));

        nums = new int[]{3,6};
        k = 2;
        System.out.printf("%s largest element: %s\n\n", k, solution.findKthLargestAttemptTwo(nums, k));
        // System.out.printf("%s largest element: %s\n\n", k, solution.findKthLargestAttemptOne(nums, k));
    }
}
