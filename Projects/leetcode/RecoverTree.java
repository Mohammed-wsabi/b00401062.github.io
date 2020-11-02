package leetcode;


class RecoverTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static void swapVal(TreeNode n1, TreeNode n2) {
        int val = n1.val;
        n1.val = n2.val;
        n2.val = val;
    }

    public static void recoverTree(TreeNode root) {
        TreeNode curNode = root;
        TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
        ArrayList<TreeNode> candNodes = new ArrayList<>();
        while (curNode != null) {
            if (curNode.left == null) {
                if (curNode.val < preNode.val) {
                    candNodes.add(preNode);
                    candNodes.add(curNode);
                }
                preNode = curNode;
                curNode = curNode.right;
            } else {
                TreeNode tmpNode = curNode.left;
                while (tmpNode.right != null && tmpNode.right != curNode) {
                    tmpNode = tmpNode.right;
                }
                if (tmpNode.right != curNode) {
                    tmpNode.right = curNode;
                    curNode = curNode.left;
                } else {
                    tmpNode.right = null;
                    if (curNode.val < preNode.val) {
                        candNodes.add(preNode);
                        candNodes.add(curNode);
                    }
                    preNode = curNode;
                    curNode = curNode.right;
                }
            }
        }
        swapVal(candNodes.get(0), candNodes.get(candNodes.size() - 1));
    }
}
