package my.learning.dsa.p144;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode right = new TreeNode(2, new TreeNode(3), null);
        head.right = right;

        BinaryTreePreorderTraversal sol = new BinaryTreePreorderTraversal();
        System.out.println(sol.preorderTraversal(head)); // [1, 2, 3]
    }

    /**
     * Depth First Approach using Recursion
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result;
    }

    public void preorderTraversal(TreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
            preorderTraversal(root.left, result);
            preorderTraversal(root.right, result);
        }
    }


    /**
     * Iterative approach with Stack
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.add(node.right);
            if (node.left != null) stack.add(node.left);
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
     *  2.2 if there is loop, we remove the link. remove the link and process the top's right
     *  2.3 if not we link the right to top. add the value to result and process the left subtree
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
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

                if (node.right == current) {
                    node.right = null;
                    current = current.right;
                } else {
                    node.right = current;
                    result.add(current.val);
                    current = current.left;
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