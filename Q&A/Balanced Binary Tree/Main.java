class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    private int dfsHeight(TreeNode node) {
        if (node == null)
            return 0;

        int leftHeight = dfsHeight(node.left);
        if (leftHeight == -1)
            return -1;

        int rightHeight = dfsHeight(node.right);
        if (rightHeight == -1)
            return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }
}

public class Main {
    public static void main(String[] args) {

        // Creating sample tree
        // 1
        // / \
        // 2 3
        // /
        // 4

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        Solution sol = new Solution();
        boolean result = sol.isBalanced(root);

        System.out.println("Is tree balanced? " + result);
    }
}
