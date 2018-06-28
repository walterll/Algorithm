package com.leetcode;

import com.leetcode.Question110.TreeNode;

/***********************
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
 * 返回它的最小深度  2.
 * @author Walterll
 *
 */
//此题注意叶子的定义
public class Question111 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	/************************
	 * 第一次写的版本，直接参照104题最大深度的方法，采用递归，有两个问题
	 * 一是存在多余计算，即使已经找到最短节点还会继续递归直到所有节点都到底
	 * 二是无法解决根节点只有一个子树的问题，这个也是我对叶子的概念有所误解所致
	 * 例：      1
	 *     /
	 *    2 
	 * 这棵树的最小深度是2而不是1   
	 * @param root
	 * @return
	 */	
//	public int minDepth(TreeNode root) {
//        if (root == null)
//        	return 0;
//        int leftDepth = minDepth(root.left);
//        int rightDepth = minDepth(root.right);
//        return leftDepth < rightDepth ? leftDepth + 1 : rightDepth + 1;
//    }
	//第二次改了下，依然错误，果然对叶子节点概念不大熟，叶子节点是指没有子节点的节点，因此原来的这种计算最深节点的递归方法不合适
//	public int minDepth(TreeNode root) {
//        if (root == null)
//        	return 0;
//        if (root.left == null) 
//        	return childDepth(root.right) + 1;
//        if (root.right == null)
//        	return childDepth(root.left) + 1;
//        return childDepth(root);
//    }
//	public int childDepth(TreeNode node) {
//		if (node == null)
//        	return 0;
//        int leftDepth = minDepth(node.left);
//        int rightDepth = minDepth(node.right);
//        return leftDepth < rightDepth ? leftDepth + 1 : rightDepth + 1;
//	}
	//第三次修改，成绩前0%
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		if (root.left == null)
			return minDepth(root.right) + 1;
		if (root.right == null)
			return minDepth(root.left) + 1;
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		return left < right ? left + 1 : right + 1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
