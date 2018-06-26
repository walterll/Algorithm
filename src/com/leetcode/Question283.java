package com.leetcode;

/********************
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * @author Walterll
 *
 */
//此题不难，一次通过超越100%,思路是遍历数组，记录遇到的零的数量，同时将非零值向前移动零的数量的位数
//需注意别忘了最后还要在数组尾部加上零
public class Question283 {
	public void moveZeroes(int[] nums) {
        int zeroNum = 0;
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] == 0) {
        		zeroNum++;
        		continue;
        	}
        	if (zeroNum > 0) {
        		nums[i - zeroNum] = nums[i];
        	}
        }
        for (int i = 1; i <= zeroNum; i++)
        	nums[nums.length - i] = 0;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question283 q = new Question283();
		int[] nums = new int[]{2,3,1,0,5,7,2,0,13,0,1,4,7};
		q.moveZeroes(nums);
	}

}
