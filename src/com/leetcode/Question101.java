package com.leetcode;

import com.leetcode.Question100.TreeNode;

/**********************
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
    1
   / \
  2   2
 / \ / \
3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
    1
   / \
  2   2
   \   \
   3    3
 * 说明:
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * @author Walterll
 *
 */
//想了一下依然使用了递归，注意观察对称的条件，成绩前10%，总结时可尝试一下使用迭代
public class Question101 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	//注意，空树也为对称
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
            return true;
        return isChildSymmetric(root.left, root.right);
    }
	public boolean isChildSymmetric(TreeNode left, TreeNode right) {
		if (left == null && right == null)
			return true;
		if (left == null || right == null)
			return false;
		if (left.val != right.val)
			return false;
		return (isChildSymmetric(left.left, right.right)
				&& isChildSymmetric(left.right, right.left));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
