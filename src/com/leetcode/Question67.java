package com.leetcode;

/*******************
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * @author Walterll
 *
 */
//此题与415类似，代码几乎相同，可以一起总结
public class Question67 {
	public String addBinary(String a, String b) {
        if (a.length() < b.length())
        	return add(a, b);
        return add(b, a);
    }
	public String add(String shortStr, String longStr) {
		int size = longStr.length() + 1;
		int carry = 0;
		char[] sumStr = new char[size];
		for (int i = 1; i <= shortStr.length(); i++) {
			int sum = shortStr.charAt(shortStr.length() - i)
					+ longStr.charAt(longStr.length() - i)
					+ carry;
			if (sum > ('0' + '1')) {
				sum -= ('0' + 2);
				carry = 1;
			} else {
				sum -= '0';
				carry = 0;
			}
			sumStr[size - i] = (char) sum;
		}
		for (int i = shortStr.length() + 1; i <= longStr.length(); i++) {
			int sum = longStr.charAt(longStr.length() - i) + carry;
			if (sum > '1') {
				sum -= 2;
				carry = 1;
			} else {
				carry = 0;
			}
			sumStr[size - i] = (char) sum;
		}
		if (carry == 0)
			return String.copyValueOf(sumStr, 1, size - 1);
		sumStr[0] = '1';
		return String.copyValueOf(sumStr);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question67 q = new Question67();
		System.out.println(q.addBinary("0", "1"));
	}

}
