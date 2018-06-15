package com.leetcode;

/************************
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。
 * 根据这个假设，如果反转后的整数溢出，则返回 0。
 * @author Walterll
 *
 */
public class Question7 {
	public int reverse(int x) {
		int p = 0;
		double inverseX = 0;	//double为64位浮点数，确保不会溢出
								//如使用float虽然也不会溢出，但是在大整数时会存在精度问题造成错误
								//输入：-2147483412	输出：-2143847424
        while (x != 0) {
        	p = x % 10;
        	x /= 10;
        	inverseX = inverseX * 10 + p;
        }
        if ((inverseX > Integer.MAX_VALUE) || (inverseX < Integer.MIN_VALUE))
        	inverseX = 0;
        return (int)inverseX;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question7 q = new Question7();
		System.out.println(q.reverse(2134441268));
	}

}
