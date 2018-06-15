package com.leetcode;

/*********************
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * @author Walterll
 *
 */
public class Question14 {
	//第一版，直接从头开始比较每个字符串的字符，遇到字符串结束或字符不同结束
	//可以说基本没有用算法，仅战胜59%的人。暂时没想出什么优化的方法
	public String longestCommonPrefix(String[] strs) {
		//第一次提交版本未加入此判断，当strs数组为空数组时，while(true)会无限循环
		if (strs.length == 0)
			return "";
		int commonNum = 0;
		while (true) {
			boolean common = true;
			for (String str : strs) {
				if (str.length() < (commonNum + 1)) {
					common = false;
					break;
				}
				if (strs[0].charAt(commonNum) != str.charAt(commonNum)) {
					common = false;
					break;
				}
			}
			if (!common) break;
			commonNum++;
		}
//		System.out.println(commonNum);
		return strs[0].substring(0, commonNum);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String[] str = new String[]{};
		Question14 q = new Question14();
		System.out.println(q.longestCommonPrefix(str));

	}

}
