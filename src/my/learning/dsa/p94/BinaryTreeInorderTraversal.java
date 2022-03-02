package my.learning.dsa.p94;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode right = new TreeNode(2, new TreeNode(3), null);
        head.right = right;

        BinaryTreeInorderTraversal sol = new BinaryTreeInorderTraversal();
        System.out.println(sol.inorderTraversal(head)); // [1, 3, 2]
    }

    /**
     * Depth First Approach using Recursion
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    public void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversal(root.left, result);
            result.add(root.val);
            inorderTraversal(root.right, result);
        }
    }

    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.add(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    /**
     * Morris Traversal
     *
     * In order to traverse the tree without extra space.
     * We need to have provision to loop back to root after we have processed the entire Left subtree.
     *
     * To Achieve this we create temporary link between the last Right most node of left node and root before
     * we process the left subtree.
     *
     * When we revisit the root again and find the temporary link, we remove it
     *
     * Steps:
     * 1. if there is no left tree, we can print the top and iterate the Right subtree.
     * 2. if left subtree is present
     *  2.1 we first try to find the right most node or when right is equal to top.
     *      when we revisit , we will find a loop, so we check if right is not equal to current
     *  2.2 if there is loop, we remove the link. add the top to result and process the top's right
     *  2.3 if not we link the right to top. and process the left subtree
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                TreeNode node = current.left;
                while (node.right != current && node.right != null) {
                    node = node.right;
                }

                if (node.right == null) {
                    node.right = current;
                    current = current.left;
                } else {
                    node.right = null;
                    result.add(current.val);
                    current = current.right;
                }

            }
        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
