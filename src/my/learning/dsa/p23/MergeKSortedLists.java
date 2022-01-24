package my.learning.dsa.p23;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode ln3 = new ListNode(5);
        ListNode ln2 = new ListNode(4, ln3);
        ListNode first = new ListNode(1, ln2);

        ListNode ln2_3 = new ListNode(4);
        ListNode ln2_2 = new ListNode(3, ln2_3);
        ListNode second = new ListNode(1, ln2_2);

        ListNode ln3_2 = new ListNode(6);
        ListNode third = new ListNode(2, ln3_2);

        MergeKSortedLists r = new MergeKSortedLists();
        ListNode result = r.mergeKLists(new ListNode[]{first, second, third});
        while (result != null) {
            System.out.print(result.val + (result.next != null ? "->" : ""));
            result = result.next;
        }
    }

    /**
     * Compare K nodes from list.
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode result = new ListNode();
        ListNode currentNode = result;
        HashMap<Integer, ListNode> nodes = new HashMap<>();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                nodes.put(i, lists[i]);
        }
        while (!nodes.isEmpty()) {
            ListNode processingNode = null;
            int index = -1;
            for (int i = 0; i < lists.length; i++) {
                if (nodes.containsKey(i)) {
                    ListNode cNodes = nodes.get(i);
                    if (processingNode == null) {
                        processingNode = cNodes;
                        index = i;
                    } else if (processingNode.val > cNodes.val) {
                        processingNode = cNodes;
                        index = i;
                    }
                }
            }
            currentNode.next = processingNode;
            currentNode = currentNode.next;

            if (processingNode.next == null) {
                nodes.remove(index);
            } else {
                nodes.put(index, processingNode.next);
            }
        }

        return result.next;
    }

    /**
     * Using Priority Queue for comparing K Nodes.
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode result = new ListNode();
        ListNode currentNode = result;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                pq.add(lists[i]);
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            currentNode.next = node;
            currentNode = currentNode.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }

        return result.next;
    }

    /**
     * Iterative Approach
     * <p>
     * Merge two List at a time for k-1 times
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_3(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        ListNode result = merge2List(lists[0], lists[1]);
        for (int i = 2; i <= lists.length - 1; i++) {
            result = merge2List(result, lists[i]);
        }
        return result;
    }

    /**
     * Divide and Conquer Approach
     * <p>
     * Apply Merge Sort approach to Merge the List
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] list, int start, int end) {
        if (start == end) return list[start];

        int mid = (start + end) / 2;
        ListNode left = mergeKLists(list, start, mid);
        ListNode right = mergeKLists(list, mid + 1, end);

        return merge2List(left, right);
    }

    private ListNode merge2List(ListNode list1, ListNode list2) {
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