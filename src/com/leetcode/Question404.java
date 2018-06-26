package com.leetcode;

/******************
 * 计算给定二叉树的所有左叶子之和。
 * 示例：
    3
   / \
  9  20
    /  \
   15   7
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * @author Walterll
 *
 */
//使用了递归方法遍历，用了一个辅助函数用于递归，成绩前15%.
//网上查了下都是直接用原函数写的，但是感觉使用辅助函数的阅读起来更简洁
public class Question404 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public int sumOfLeftLeaves(TreeNode root) {
        return sumOfChildLeftLeaves(root, 0);
    }
	//第一版，对树的定义理解有误，将父节点也算到叶子里了
//		3
//	   / \
//	  9  20
// 	/  \
// 15   7	比如这里面9就不算叶子
//	public int sumOfChildLeftLeaves(TreeNode node, int isLeft) {
//		if (node == null)
//			return 0;
//		return node.val * isLeft + sumOfChildLeftLeaves(node.left, 1) + sumOfChildLeftLeaves(node.right, 0);
//	}
	public int sumOfChildLeftLeaves(TreeNode node, int isLeft) {
		if (node == null)
			return 0;
		if ((node.left == null) && (node.right == null))
			return node.val * isLeft;
		return sumOfChildLeftLeaves(node.left, 1) + sumOfChildLeftLeaves(node.right, 0);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
