package trees.traversal;

import java.util.Stack;

public class Inorder {

    TreeNode root;

    static class TreeNode {
        String data;
        TreeNode left, right;

        TreeNode(String value) {
            this.data = value;
            left = right = null;
        }
    }

        /**
         * traverse the binary tree on InOrder traversal algorithm
         */
        public void inOrder() {
            inOrder(root);
        }

        private void inOrder(TreeNode node) {
            if (node == null) {
                return;
            }

            inOrder(node.left);
            System.out.printf("%s ", node.data);
            inOrder(node.right);
        }


        public void preOrderWithoutRecursion() {
            Stack<TreeNode> nodes = new Stack<>();
            nodes.push(root);

            while (!nodes.isEmpty()) {
                TreeNode current = nodes.pop();
                System.out.printf("%s ", current.data);

                if (current.right != null) {
                    nodes.push(current.right);
                }
                if (current.left != null) {
                    nodes.push(current.left);
                }
            }
        }

        public void postOrder() {
            postOrder(root);
        }

        private void postOrder(TreeNode node) {
            if (node == null) {
                return;
            }

            postOrder(node.left);
            postOrder(node.right);
            System.out.printf("%s ", node.data);
        }
        /*
         *
         * input:
         *      40
         *     /  \
         *    20   50
         *   / \    \
         *  10  30   60
         * /   /  \
         * 5  67  78
         *
         * output: 5 10 20 30 40 50 60 67 78
         */
        public void create() {
/*            root = new TreeNode("40");
            root.left = new TreeNode("20");
            root.left.left = new TreeNode("10");
            root.left.left.left = new TreeNode("5");

            root.left.right = new TreeNode("30");
            root.right = new TreeNode("50");
            root.right.right = new TreeNode("60");
            root.left.right.left = new TreeNode("67");
            root.left.right.right = new TreeNode("78");*/

            /*
             *
             * input:
             *         A
             *      /    \
             *     B      C
             *   /  \    / \
             *   D   E  F  G
             *  / \       /
             * H   I     J
             *
             */


            root = new TreeNode("A");
            root.left = new TreeNode("B");
            root.left.left = new TreeNode("D");
            root.left.left.left = new TreeNode("H");

            root.left.left.right = new TreeNode("I");
            root.left.right = new TreeNode("E");
            root.right = new TreeNode("C");
            root.right.left = new TreeNode("F");
            root.right.right = new TreeNode("G");
            root.right.right.left = new TreeNode("J");

        }



        public static void main(String[] args) throws Exception {
                // construct the binary tree
                Inorder bt = new Inorder();
                bt.create();

                System.out.print("preOrder: ");
                bt.preOrderWithoutRecursion();
                System.out.println();
                System.out.print("InOrder:    ");
                bt.inOrder();
                System.out.println();
                System.out.print("postOrder:  ");
                bt.postOrder();
        }
}
