package linkedlist;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public String toString() {
        String str = "";
        ListNode node = new ListNode(this.val, this.next);
        while (node != null) {
            str = str.concat("[" + node.val + "]-> ");
            node = node.next;
        }
        return str;
    }
}
