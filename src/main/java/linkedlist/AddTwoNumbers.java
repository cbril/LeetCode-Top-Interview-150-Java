package linkedlist;

/**
 * <a href="https://leetcode.com/problems/add-two-numbers/description">2. Add Two Numbers</a>
 */
public class AddTwoNumbers {

    /**
     * Returns the sum of the two numbers represented by the linked lists (first attempt)
     * @param l1 head node of a non-empty linked list representing an integer, with a single digit in each node and integers in reverse order
     * @param l2 head node of a non-empty linked list representing an integer, with a single digit in each node and integers in reverse order
     * @return the sum of the two integers from l1 and l2
     */
    public ListNode addTwoNumbersFirstAttempt(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode l1Node = l1;
        ListNode l2Node = l2;
        int decimalPlace = 0;
        while (l1Node != null || l2Node != null) {
            int l1Value = 0;
            if (l1Node != null) {
                l1Value = l1Node.val;
                l1Node = l1Node.next;
            }
            int l2Value = 0;
            if (l2Node != null) {
                l2Value = l2Node.val;
                l2Node = l2Node.next;
            }

            sum += (l1Value + l2Value) * (int) Math.pow(10, decimalPlace);
            decimalPlace++;
        }

        return intToLinkedList(sum);
    }

    /**
     * Returns the sum of the two numbers represented by the linked lists.
     * Second attempt, sum and creating new linked list can be done simultaneously.
     * @param l1 head node of a non-empty linked list representing an integer, with a single digit in each node and integers in reverse order
     * @param l2 head node of a non-empty linked list representing an integer, with a single digit in each node and integers in reverse order
     * @return the sum of the two integers from l1 and l2
     */
    public ListNode addTwoNumbersSecondAttempt(ListNode l1, ListNode l2) {
        ListNode headNode = null;
        ListNode prevNode = null;
        int carryOver = 0;
        while (l1 != null || l2 != null) {
            int l1Value = 0;
            if (l1 != null) {
                l1Value = l1.val;
                l1 = l1.next;
            }
            int l2Value = 0;
            if (l2 != null) {
                l2Value = l2.val;
                l2 = l2.next;
            }

            int sum = l1Value + l2Value + carryOver;
            carryOver = sum / 10;
            sum = sum % 10;

            ListNode newNode = new ListNode(sum);
            if (prevNode == null) {
                headNode = newNode;
            } else {
                prevNode.next = newNode;
            }
            prevNode = newNode;
        }
        if (carryOver > 0) {
            prevNode.next = new ListNode(carryOver);
        }
        return headNode;
    }

    /**
     * Converts int to linked list (in reverse order) and returns the head node of the linked list
     * e.g. 3401 = [1]-> [0]-> [4]-> [3]->
     */
    private ListNode intToLinkedList(int number) {
        String numberAsString = String.valueOf(number);
        ListNode headNode = null;
        ListNode prevNode = null;
        for (int i = numberAsString.length() - 1; i >= 0; i--) {
            int value = Integer.parseInt(String.valueOf(numberAsString.charAt(i)));
            ListNode newNode = new ListNode(value);
            if (prevNode != null) {
                prevNode.next = newNode;
            } else {
                headNode = newNode;
            }
            prevNode = newNode;
        }
        return headNode;
    }

    static void main() {
        AddTwoNumbers solution = new AddTwoNumbers();
        int num1 = 0;
        int num2 = 0;
        ListNode list1 = solution.intToLinkedList(num1);
        ListNode list2 = solution.intToLinkedList(num2);
        System.out.printf("%s  +  %s\n%s  +  %s\n%s\n\n", num1, num2, list1.toString(), list2.toString(), solution.addTwoNumbersSecondAttempt(list1, list2).toString());

        num1 = 342;
        num2 = 465;
        list1 = solution.intToLinkedList(num1);
        list2 = solution.intToLinkedList(num2);
        System.out.printf("%s  +  %s\n%s  +  %s\n%s\n\n", num1, num2, list1.toString(), list2.toString(), solution.addTwoNumbersSecondAttempt(list1, list2).toString());

        num1 = 9999999;
        num2 = 9999;
        list1 = solution.intToLinkedList(num1);
        list2 = solution.intToLinkedList(num2);
        System.out.printf("%s  +  %s\n%s  +  %s\n%s\n\n", num1, num2, list1.toString(), list2.toString(), solution.addTwoNumbersSecondAttempt(list1, list2).toString());
    }
}
