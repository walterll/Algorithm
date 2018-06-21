package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/*********************
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 * @author Walterll
 *
 */
public class Question205 {
	//第一次尝试用HashMap做，但是发现这样无法判断：两个字符不能映射到同一个字符上
//	public boolean isIsomorphic(String s, String t) {
//        Map<Character, Character> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//        	if (map.containsKey(s.charAt(i)))
//        		if (t.charAt(i) != (char)map.get(s.charAt(i)))
//        			return false;
//        		else ;
//        	else map.put(s.charAt(i), t.charAt(i));
//        }
//        return true;
//    }
	//第二次决定用两个数组分别存储s到t和t到s的映射
	public boolean isIsomorphic(String s, String t) {
		int[] s2t = new int[256];	//s到t的映射
		int[] t2s = new int[256];	//t到s的映射
		for (int i = 0; i < s.length(); i++) {
			//已经存在s到t的映射
			if (s2t[s.charAt(i)] != 0) {
				//与t的字符不符
				if (s2t[s.charAt(i)] != (t.charAt(i)))
					return false;
			} else { //第一次遇到的字符
				//已经存在t到s的映射，则表明两个字符映射到了同一个字符上，例：s:ab  t:aa
				if (t2s[t.charAt(i)] != 0)
					return false;
				//存储两个映射
				s2t[s.charAt(i)] = t.charAt(i);
				t2s[t.charAt(i)] = s.charAt(i);
			}
		}
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question205 q = new Question205();
		System.out.println(q.isIsomorphic("abbs", "essa"));
	}

}
