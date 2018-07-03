package com.leetcode.med;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*******************
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 * @author Walterll
 *
 */
public class Question18 {
	//使用了和15题相同的方法，只不过多一层循环，速度一般，成绩50%
	//复习的话研究下优化
	public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> sumGroups = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
        	if (i - 1 >= 0 && nums[i - 1] == nums[i]) 
        		continue;
        	for (int j = i + 1; j < nums.length; j++) {
        		if (j - 1 > i && nums[j - 1] == nums[j]) 
            		continue;
        		int left = j + 1, right = nums.length - 1;
        		while (left < right) {
        			if (left - 1 > j && nums[left - 1] == nums[left]) {
        				left++;
        				continue;
        			}
        			if (nums[i] + nums[j] + nums[left] + nums[right] == target) {
        				List<Integer> num = new ArrayList<>();
        				num.add(nums[i]);
        				num.add(nums[j]);
        				num.add(nums[left]);
        				num.add(nums[right]);
        				sumGroups.add(num);
        				left++;
        				right--;
        			} else if (nums[i] + nums[j] + nums[left] + nums[right] < target) {
        				left++;
        			} else {
        				right--;
        			}
        		}
        	}
        }
        return sumGroups;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question18 q = new Question18();
		System.out.println(q.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
	}

}
