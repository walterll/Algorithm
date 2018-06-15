package com.leetcode;

/*********************
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 * 输入: 121
 * 输出: true
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 * @author Walterll
 *
 */
public class Question9 {
	//第一次提交，方法是将每一位都存入一个数组然后比较数组中的值，虽然通过，但是仅超过60%，思考后发现可以直接用第七题的方法而无需构造一个数组比较
//	public boolean isPalindrome(int x) {
//        if (x < 0) return false;
//        if (x == 0) return true;
//        int[] num = new int[10];
//        int size = 0;
//        while (x > 0) {
//        	num[size] = x % 10;
//        	x /= 10;
//        	size++;
//        }
//        //第一次写的时候由于size++放在while的最后，所以最后会指向最大位+1，因此以下判断法会出错，因为num[size]是0,num[size-1]才是最高位
////        for (int i = 0; i <= size / 2; i++) 
////        	if (num[i] != num[size - i]) return false;
//        //第二版
//        for (int i = 0; i <= size / 2; i++) 
//        	if (num[i] != num[size - i - 1]) return false;
////        System.out.println(num[i]);
//        return true;
//    }
	//第二版，使用Question7的方法，获得反转数，然后和原始数比较，超过82%
	//查看参考答案可知，可以只计算x的后一半回文，来和前一半比较，关键是如何得知到达一半位置，答案是通过比较得到的回文和剩余的数字大小即可
//	public boolean isPalindrome(int x) {
//		double originNum = x;
//		if (x < 0) return false;
//		if (x == 0) return true;
//		int p = 0;
//		double inverseX = 0;	//double为64位浮点数，确保不会溢出
//								//如使用float虽然也不会溢出，但是在大整数时会存在精度问题造成错误
//								//输入：-2147483412	输出：-2143847424
//        while (x != 0) {
//        	p = x % 10;
//        	x /= 10;
//        	inverseX = inverseX * 10 + p;
//        }
//        if (originNum == inverseX) return true;
//        return false;
//	}
	//转换成String的方法，速度最慢
	public boolean isPalindrome(int x) {
		if (x < 0) return false;
		String num = Integer.toString(x);
		for (int i = 0; i < num.length() / 2; i++)
			if (num.charAt(i) != num.charAt(num.length() - i - 1)) return false;
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question9 q = new Question9();
		System.out.println(q.isPalindrome(644221446));
	}

}
