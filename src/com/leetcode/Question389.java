package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/*******************
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * 示例:
 * 输入：
 * s = "abcd"
 * t = "abcde"
 * 输出：
 * e
 * 解释：
 * 'e' 是那个被添加的字母。
 * @author Walterll
 *
 */
public class Question389 {
	//第一版，把字母存入HashMap，速度很慢
//	public char findTheDifference(String s, String t) {
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//        	if (map.containsKey(s.charAt(i)))
//        			map.put(s.charAt(i), (map.get(s.charAt(i)) + 1));
//        	else map.put(s.charAt(i), 1);
//        }
//        for (int i = 0; i < s.length(); i++) {
//        	if (map.containsKey(t.charAt(i))) {
//        		if (map.get(t.charAt(i)) == 0)
//        			return t.charAt(i);
//        		map.put(t.charAt(i), (map.get(t.charAt(i)) - 1));
//        	}
//        	else return t.charAt(i);
//        }
//        return t.charAt(s.length());
//    }
	//改用数组存储字符，速度提升到了前20%
	public char findTheDifference(String s, String t) {
		int[] character = new int[26];
		for (int i = 0; i < s.length(); i++) {
			character[s.charAt(i) - 'a']++;
			character[t.charAt(i) - 'a']--;
		}
		character[t.charAt(s.length()) - 'a']--;
		for (int i = 0; i < 26; i++)
			if (character[i] == -1)
				return (char) (i + 'a');
		return ' ';
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question389 q = new Question389();
		System.out.println(q.findTheDifference("difference", "fediefecnkr"));
	}

}
