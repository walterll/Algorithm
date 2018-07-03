package com.leetcode.med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**********************
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 * @author Walterll
 *
 */
//此题如何避免o(n3)复杂度是一个难点，查了网上的解答，才发现要先排序然后固定一个数用指针从最外层向内寻找另外两个数
//还有个难点就是如何避免重复，移动指针的判断需要仔细斟酌，例如判断两个指针是否相等是错误的，如（0，0，0）也是一个正确的解
//目前成绩是50%左右，复习的话可以看一下别人的代码尝试提高一下速度
public class Question15 {
	public List<List<Integer>> threeSum(int[] nums) {
		//先将数组排序
        Arrays.sort(nums);
        List<List<Integer>> threeNums = new ArrayList<>();
        //遍历数组，作为第一个数
        for (int i = 0; i < nums.length; i++) {
        	//第一次使用这种方法避免重复，但是这种方法会漏算
//        	if ((i + 1) < nums.length && nums[i] == nums[i + 1]) 
//        		continue;
//        	if ((i - 1) > 0 && nums[i - 1] == nums[i]) 
        	//当遇到与之前重复的数时，跳过这个数
        	if ((i - 1) >= 0 && nums[i - 1] == nums[i]) 	//修改之后的判断
        		continue;
        	//将左右指针指向第一个数右边一个数和数组最右边的数，移动指针来寻找合适的三元数
        	int left = i + 1, right = nums.length - 1;
        	while (left < right) {
//        		if (nums[left] == nums[left + 1]) {	//这样会漏算
        		//与第一个数同理，需要防止第二个数出现重复，不需要防止第三个数重复（前两个数不重复，第三个数一定不会重复）
        		if (left != i + 1 && nums[left] == nums[left - 1]) {	//多次修改之后的判断
        			left++;
        			continue;
        		}
        		//找到目标数，添加到list，将左右指针向内侧移动
        		if (nums[i] + nums[left] + nums[right] == 0) {
        			List<Integer> list = new ArrayList<>();
        			list.add(nums[i]);
        			list.add(nums[left]);
        			list.add(nums[right]);
        			threeNums.add(list);
        			left++;
        			right--;
        			//和小于0，将左指针向右移动
        		} else if (nums[i] + nums[left] + nums[right] < 0) {
        			left++;
        			//和大于0，将右指针向左移动
        		} else {
        			right--;
        		}
        	}
        }
        return threeNums;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question15 q = new Question15();
		int[] nums = new int[]{0,0,0,0,0};
		System.out.println(q.threeSum(nums));
	}

}
