package my.learning.dsa.p21;

public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode ln3 = new ListNode(4);
        ListNode ln2 = new ListNode(2, ln3);
        ListNode first = new ListNode(1, ln2);

        ListNode ln2_3 = new ListNode(3);
        ListNode ln2_2 = new ListNode(2, ln2_3);
        ListNode second = new ListNode(1, ln2_2);

        MergeTwoSortedLists r = new MergeTwoSortedLists();
        ListNode result = r.mergeTwoLists(first, second);
        while (result != null) {
            System.out.print(result.val + (result.next != null ? "->" : ""));
            result = result.next;
        }
    }

    /**
     * Iterative approach
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists_1(ListNode list1, ListNode list2) {
        ListNode ln = new ListNode();
        ListNode result = ln;
        ListNode first = list1, second = list2;

        while (first != null && second != null) {
            if (first.val < second.val) {
                ln.next = first;
                first = first.next;
            } else {
                ln.next = second;
                second = second.next;
            }
            ln = ln.next;
        }
        if (first != null) {
            ln.next = first;
        }
        if (second != null) {
            ln.next = second;
        }
        return result.next;
    }

    /**
     * Recursive Approach
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode result ;
        if (list1.val < list2.val) {
            result= list1;
            result.next = mergeTwoLists(list1.next, list2);
        } else {
            result= list2;
            result.next = mergeTwoLists(list1, list2.next);
        }
        return result;

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