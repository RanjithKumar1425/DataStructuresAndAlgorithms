package my.learning.dsa.p19;

import java.util.Stack;

public class RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        ListNode ln5 = new ListNode(5);
        ListNode ln4 = new ListNode(4, ln5);
        ListNode ln3 = new ListNode(3, ln4);
        ListNode ln2 = new ListNode(2, ln3);
        ListNode ln1 = new ListNode(1, ln2);

        RemoveNthNodeFromEnd r = new RemoveNthNodeFromEnd();
        ListNode result = r.removeNthFromEnd(ln1, 2);
        while (result != null) {
            System.out.print(result.val + (result.next != null ? "->" : ""));
            result = result.next;
        }

    }

    /**
     * Using Stack
     * 1. stack the nodes in Stack. and count the no of nodes.
     * 2. pop from stack for n times
     * 3. Change the next pointer
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd_1(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;
        int totalNodes = 0;
        while (curr != null) {
            totalNodes++;
            stack.add(curr);
            curr = curr.next;
        }

        if (n == totalNodes) {
            return head.next;
        } else {
            int count = 0;
            while (count < n) {
                count++;
                stack.pop();
            }
            ListNode removeNode = stack.pop();
            removeNode.next = removeNode.next.next;
            return head;
        }

    }

    /**
     * Two Pointer Approach
     * 1. Have two pointers slow and fast.
     * 2. Move fast pointer n time
     * 3. Move fast and slow pointer unless fast pointer reaches end.
     * 4. Swap next pointer of slow pointer
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode();
        start.next = head;
        ListNode slow = start, fast = start;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return start.next;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}