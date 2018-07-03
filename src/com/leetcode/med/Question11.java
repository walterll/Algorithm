package com.leetcode.med;

/*********************
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 画 n 条垂直线，使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 注意：你不能倾斜容器，n 至少是2。
 * @author Walterll
 *
 */
//看了解答方法二，先计算最外侧的两个线段围成的面积，然后移动较短线段寻找是否存在更大的面积
//参考的代码中直接比较每次移动线段后围成的面积，反而速度更快，而且代码简洁许多，复习时自己写一下
public class Question11 {
	//第一版循环中移动左右边界时存在判断错误
//	public int maxArea(int[] height) {
//        int left = 0, right = height.length - 1;
////        int leftHeight = height[left], rightHeight = height[right];
//        int maxArea = Math.min(height[left], height[right]) * (right - left);
//        int originLeft = left, originRight = right;
//        while (left != right) {
//        	if (height[left] < height[right]) {
//        		left++;
////        		if (height[left] < height[right])	//第一版的内部判断有误，
////        			continue;
//        		if (height[left] < height[originLeft])
//        			continue;
//        		if (height[right] * (right - left) > maxArea) {
//        			maxArea = height[right] * (right - left);
//        			originLeft = left;
//        		}
//        	} else {
//        		right--;
////        		if (height[left] > height[right])
////        			continue;
//        		if (height[right] < height[originRight])
//        			continue;
//        		if (height[left] * (right - left) > maxArea) {
//        			maxArea = height[left] * (right - left);
//        			originRight = right;
//        		}
//        	}
//        }
//        return maxArea;
//    }
	//第二版，改了一些错误，速度不是很好，仅在70%左右
	public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
//        int leftHeight = height[left], rightHeight = height[right];
        int maxArea = Math.min(height[left], height[right]) * (right - left);
        int originLeft = left, originRight = right;
        while (left != right) {
        	//如果左边界比较小，则向右移动左边界以寻找更大的左边界
        	if (height[left] < height[right]) {
        		left++;
//        		if (height[left] < height[right])	//第一版的内部判断有误，
//        			continue;
        		if (height[left] < height[originLeft])	//左边界不是和右边界比较，而是同原左边界比较
        			continue;							//如果比原来还小那面积不可能比原来大
        		if (Math.min(height[left], height[right]) * (right - left) > maxArea) {
        			maxArea = Math.min(height[left], height[right]) * (right - left);
        			originLeft = left;
        		}
        	//右边界较小，向左移动右边界
        	} else {
        		right--;
//        		if (height[left] > height[right])
//        			continue;
        		if (height[right] < height[originRight])
        			continue;
        		if (Math.min(height[left], height[right]) * (right - left) > maxArea) {
        			maxArea = Math.min(height[left], height[right]) * (right - left);
        			originRight = right;
        		}
        	}
        }
        return maxArea;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
