package com.leetcode;

/**********************
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 案例:
 * s = "leetcode"
 * 返回 0.
 * s = "loveleetcode",
 * 返回 2.
 * 注意事项：您可以假定该字符串只包含小写字母。
 * @author Walterll
 *
 */
//用了较简单的方方，遍历字符串，用一个数组记录字母出现次数，排名前20%,暂时不太清楚更快的方法
public class Question387 {
	public int firstUniqChar(String s) {
        int[] isUniq = new int[26];
        for (int i = 0; i < s.length(); i++) {
        	isUniq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
        	if (isUniq[s.charAt(i) - 'a'] == 1)
        		return i;
        }
        return -1;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question387 q = new Question387();
		System.out.println(q.firstUniqChar("thisisthefirstexam"));
	}

}
