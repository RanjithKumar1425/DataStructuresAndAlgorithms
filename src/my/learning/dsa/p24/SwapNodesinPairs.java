package my.learning.dsa.p24;

public class SwapNodesinPairs {
    public static void main(String[] args) {
        SwapNodesinPairs sn = new SwapNodesinPairs();
        ListNode ln5 = new ListNode(5);
        ListNode ln4 = new ListNode(4,ln5);
        ListNode ln3 = new ListNode(3, ln4);
        ListNode ln2 = new ListNode(2, ln3);
        ListNode first = new ListNode(1, ln2);
        ListNode result = sn.swapPairs(first);
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
     * Iterative Approach
     *
     * @param head
     * @return
     */
    public ListNode swapPairs_1(ListNode head) {
        ListNode result = new ListNode();
        ListNode node = result;
        ListNode ch = head;
        while (ch != null && ch.next != null) {
            ListNode next = ch.next.next;
            node.next = ch.next;
            node.next.next = ch;
            node = ch;
            ch = next;
        }
        node.next = ch;
        return result.next;
    }

    /**
     * Recursive Approach
     *
     * swapNode([1-2-3-4-5]) => 1->2->3->4->5
     * 		swapNode([3-4-5]) => 4->3->5
     * 				swapNode([5]) => 5
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if ((head == null) || (head.next == null))
            return head;
        ListNode ln = head.next;
        head.next = swapPairs(ln.next);
        ln.next = head;
        return ln;
    }
}
