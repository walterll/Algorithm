package com.leetcode.med;

/*********************
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * 示例：
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 * @author Walterll
 *
 */
public class Question3 {
	//使用的方法和解答2的类似，滑动窗口法，但是在发现重复字符后寻找字符在子串中位置的操作较为复杂和笨拙，不如解答的简洁
	//最坏情况复杂度为o(n2)，速度较解答2稍慢，更不如解答3优化的滑动窗口
	//复习的话尝试自己写一下优化的滑动窗口
	public int lengthOfLongestSubstring(String s) {
		boolean[] alp = new boolean[256];
		int maxLength = 0, totalMaxLength = 0;	//maxLength当前子串长度  ， totalMaxLength最大子串长度
		//遍历字符串
        for (int i = 0; i < s.length(); i++) {
        	//字符不在子串中
        	if (!alp[s.charAt(i)]) {
        		alp[s.charAt(i)] = true;
        		maxLength++;
//        		continue;
        		//字符在子串中
        	} else {
        		int backLength = 0;	//回退距离
        		//回退寻找重复的字符位置
        		for (int j = 1; j <= maxLength; j++) {
        			if (s.charAt(i) == s.charAt(i - j)) {
        				backLength = j;
        				break;
        			}
        		}
        		//将重复字符位置前的字符从子串中去除
        		for (int j = backLength + 1; j <= maxLength; j++) 
        			alp[s.charAt(i - j)] = false;
        		maxLength = backLength;
        	}
        	if (maxLength > totalMaxLength)
        		totalMaxLength = maxLength;
        }
        return totalMaxLength;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question3 q = new Question3();
		System.out.println(q.lengthOfLongestSubstring("bbbbb"));
	}

}
