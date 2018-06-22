package com.leetcode;

/*********************
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * 注意：
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * @author Walterll
 *
 */
//难度并不算很大，自己调试成功后一次通过，但是调试了较长时间，细节方面需要注意，如多处进位以及char到int的ascii码值得关系等
public class Question415 {
	public String addStrings(String num1, String num2) {
        if (num1.length() < num2.length())
        	return addStringsX(num1, num2);
        return addStringsX(num2, num1);
    }
	//参数中确定字符串的长短便于后序处理
	String addStringsX(String shortNum, String longNum) {
		char[] sum = new char[longNum.length() + 1];	//+1是因为两数相加可能最高位有进位
		int carry = 0;	//进位位
		for (int i = 1; i <= shortNum.length(); i++) {
			int sumNum = shortNum.charAt(shortNum.length() - i) 
						+ longNum.charAt(longNum.length() - i) 
						+ carry;
			if (sumNum > '0' + '9') {	//注意char转到int是用ascii码，+'0'是因为有两个ascii码相加
				sumNum -= ('0' + 10);
				carry = 1;
			}
			else {
				sumNum -= '0';
				carry = 0;
			}
			sum[longNum.length() + 1 - i] = (char) sumNum;
		}
		for (int i = shortNum.length() + 1; i <= longNum.length(); i++) {
			int sumNum = longNum.charAt(longNum.length() - i) + carry;
			if (sumNum > '9') {	//一个数字不需要+'0'
				sumNum -= 10;
				carry = 1;
			}
			else
				carry = 0;
			sum[longNum.length() + 1 - i] = (char) sumNum;
		}
		//最高位是否有进位
		if (carry == 1) {
			sum[0] = '1';
			return String.copyValueOf(sum);			
		}
		return String.copyValueOf(sum, 1, longNum.length());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question415 q = new Question415();
		System.out.println(q.addStrings("1853", "272"));
	}

}
