package com.leetcode;

import com.leetcode.Question104.TreeNode;

/*********************
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
 * 返回 false 。
 * @author Walterll
 *
 */
//使用104题算法的修改，增加了判断左右树的高度差，成绩为前0%
public class Question110 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public boolean isBalanced(TreeNode root) {
        if (root == null)
        	return true;
        if (maxDepth(root) == -1)
        	return false;
        return true;
    }
	//如果左右子树的高度差超过1，则返回-1
	public int maxDepth(TreeNode node) {
		if (node == null)
			return 0;
		int leftDepth = maxDepth(node.left);
		int rightDepth = maxDepth(node.right);
		if (leftDepth == -1 || rightDepth == -1)
			return -1;
		if (Math.abs(leftDepth - rightDepth) > 1)
			return -1;
		return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
