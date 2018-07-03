package com.leetcode.med;

/*********************
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * @author Walterll
 *
 */
//自己想的方法虽然成绩直接在前0%，但是需要2个循环，应该是因为速度太快没有区分度，所有人都是0ms
//复习时想一想只用一个循环的方法
public class Question75 {
	//自己想的用类似算法第5章给字符串排序的LSD算法的方法，计算出现频率，然后回写，但是需要2个循环
	public void sortColors(int[] nums) {
        int[] colors = new int[3];
        for (int i = 0; i < nums.length; i++)
        	colors[nums[i]]++;
        for (int i = 0; i < nums.length; i++) {
        	if (colors[0] > 0) {
        		nums[i] = 0;
        		colors[0]--;
        	}else if (colors[1] > 0) {
        		nums[i] = 1;
        		colors[1]--;
        	} else {
        	nums[i] = 2;
        	}
        }
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
