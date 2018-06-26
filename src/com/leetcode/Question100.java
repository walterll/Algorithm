package com.leetcode;

import com.leetcode.Question404.TreeNode;

/*******************
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 示例 1:
 * 输入:       1         1
             / \       / \
            2   3     2   3
           [1,2,3],   [1,2,3]
 * 输出: true
 * 示例 2:
 * 输入:      1          1
            /           \
           2             2
         [1,2],     [1,null,2]
 * 输出: false
 * 示例 3:
 * 输入:       1         1
             / \       / \
            2   1     1   2
          [1,2,1],   [1,1,2]
 * 输出: false
 * @author Walterll
 *
 */
//使用迭代遍历两棵树，成绩为前10%
public class Question100 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null)
        	if (q != null)
        		return false;
        	else 
        		return true;
        if (q == null)
        	return false;
        if (p.val != q.val)
        	return false;
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
}
