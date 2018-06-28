package com.leetcode;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.Question111.TreeNode;

/********************
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * 示例 :
 * 给定二叉树
          1
         / \
        2   3
       / \     
      4   5    
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * @author Walterll
 *
 */
public class Question543 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	//注意最大直径的定义。每个节点的最大直径即是节点左右树的最大深度之和，整棵树的最大直径就是所有节点中直径最大的
	//所以还是用递归，计算每个节点的深度以及该节点的直径，存到一个ArrayList表中
	//成绩在前15%
	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;
		List<Integer> diameters = new ArrayList<>();
		diameterOfTreeNode(root, diameters);
		int maxDiameter = 0;
		for (Integer i : diameters) {
			if (i > maxDiameter)
				maxDiameter = i;
		}
		return maxDiameter;
    }
	public int diameterOfTreeNode(TreeNode node, List<Integer> diameters) {
		if (node == null)
			return 0;
		int left = diameterOfTreeNode(node.left, diameters);
		int right = diameterOfTreeNode(node.right, diameters);
		diameters.add(left + right);
		return left < right ? right + 1 : left + 1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
