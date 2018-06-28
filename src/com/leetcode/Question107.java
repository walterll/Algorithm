package com.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.Question104.TreeNode;

/*****************
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
 * 返回其自底向上的层次遍历为：
[
  [15,7],
  [9,20],
  [3]
]
 * @author Walterll
 *
 */
//此题是102的翻版，而102是中等难度，故此题先暂停一下，去做102
public class Question107 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (root == null) return null;
        List<List> lists = new ArrayList<>();
    	List<Integer> list = new ArrayList<>();
    	list.add(root.val);
    	lists.add(list);
    	List<Integer> childList = new ArrayList<>();
    	
        
    }
	public void childLevel(TreeNode node, List<Integer> list) {
		if (node != null)
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
