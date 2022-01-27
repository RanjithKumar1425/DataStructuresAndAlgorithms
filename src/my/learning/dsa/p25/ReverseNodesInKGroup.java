package my.learning.dsa.p25;

import java.util.Stack;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ReverseNodesInKGroup rn = new ReverseNodesInKGroup();

        ListNode ln5 = new ListNode(5);
        ListNode ln4 = new ListNode(4, ln5);
        ListNode ln3 = new ListNode(3, ln4);
        ListNode ln2 = new ListNode(2, ln3);
        ListNode first = new ListNode(1, ln2);
//        ListNode result = rn.reverseKGroup(first, 2); // [2,1,4,3,5]
        ListNode result = rn.reverseKGroup(first, 4); // [3,2,1,4,5]
        printlist(result);
    }

    private static void printlist(ListNode result) {
        while (result != null) {
            System.out.print(result.val + (result.next != null ? "->" : ""));
            result = result.next;
        }
        System.out.println();
    }

    /**
     * Iterative method : Using Stack to Reverse Nodes.
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup_1(ListNode head, int k) {

        ListNode result = new ListNode();
        ListNode dummy = result;
        ListNode current = head;
        Stack<ListNode> st = new Stack<>();
        while (current != null) {
            ListNode back = current;
            for (int i = 0; i < k && current != null; i++) {
                st.add(current);
                current = current.next;
            }
            if (st.size() == k) {
                while (!st.empty()) {
                    dummy.next = st.pop();
                    dummy = dummy.next;
                }
            } else {
                dummy.next = back;
            }
        }
        dummy.next = null;
        return result.next;
    }

    /**
     * Iterative Approach - Reverse without extra space.
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup_2(ListNode head, int k) {
        ListNode result = new ListNode();
        ListNode dummy = result;
        ListNode current = head;
        while (current != null) {
            ListNode back = current;
            int count = 0;
            while (count < k && current != null) {
                current = current.next;
                count++;
            }
            if (count != k) {
                dummy.next = back;
                break;
            }

            ListNode start = reverse(back, k);
            dummy.next = start;
            dummy = back;
        }
        return result.next;
    }

    /**
     * Reverse (head = [1->2->3->4->5] , 4)
     * <p>
     * pre = null
     * <p>
     * count = 0
     * pre = [1]
     * head = [2->3->4->5]
     * count = 1
     * pre = [2->1]
     * head = [3->4->5]
     * count = 2
     * pre = [3->2->1]
     * head = [4->5]
     * count = 3
     * pre = [4->3->2->1]
     * head = [5]
     * <p>
     * return 4->3->2->1
     *
     * @param head
     * @param k
     * @return
     */
    private ListNode reverse(ListNode head, int k) {
        ListNode prev = null;
        int count = 0;
        while (count < k) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            count++;
        }
        return prev;
    }

    /**
     * Recursive Approach
     *
     * reverseKNode (head = [1->2->3->4->5] , 4)
     *
     * 	current = [5] //after 4 jumps
     *
     * 	current =  reverseKNode (head = [5] , 4) => [5]
     *
     * 	prev = [1]
     *
     * 	head = reverse(head = [1->2->3->4->5] , 4) => [4->3->2->1]
     *
     * 	head = [4->3->2->1->5]
     *
     * return head
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head;
        int count = 0;
        while (count < k && current != null) {
            current = current.next;
            count++;
        }

        if (count == k) {
            current = reverseKGroup(current, k);
            ListNode tempHead= head;
            head = reverse(head, k);
            tempHead.next = current;
        }

        return head;

    }


}
