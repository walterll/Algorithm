package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**********************
 * 统计所有小于非负整数 n 的质数的数量。
 * 示例:
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * @author Walterll
 *
 */
//上网查了质数高效的计算方法，最高效的是称为厄拉多塞筛法的方法，现将全部数字存储，然后从2开始将2的倍数删除，如2，4，6，8，10...
//接着从3，5，7，11。。。因为剩余的最小的数一定是质数，
//注意，只需要计算到n的根号为止，因为n的因数最大为n开方，如果n开方以内都不是n的因数，则n一定是质数
//即要找100以下的质数时，只需要计算到10为止，最后一轮删去7的倍数，7，49
//复习时总结，另外目前性能依然不够高，有待提升
public class Question204 {
	//第一次写的版本，考虑用HashSet存储数，但是后面删减的逻辑是错误的
//	public int countPrimes(int n) {
//        Set<Integer> set = new HashSet<>();
//        for (int i = 2; i <= n; i++)
//        	set.add(i);
//        int count = 0;
//        for (int i = 2; i < Math.sqrt(n) + 1; i++) {
//        	for (int j = 1; i * j <= n; j++)		//此处删减有误，内循环应该从质数开始
//        		set.remove(i * j);
//        	count++;
//        }
//        return count + set.size();
//    }
	//第二次写的版本，使用数组来存储数，注意数组下标和数的对应关系，速度排在中等
//	public int countPrimes(int n) {
//		if (n <= 2) return 0;
//		int[] prime = new int[n - 2];
//		for (int i = 0; i < prime.length; i++)
//			prime[i] = i + 2;
//		for (int i = 0; i < Math.sqrt(n) - 1; i++) {
//			if (prime[i] != 0) {
//				for (int j = 2; prime[i] * j < n; j++)
//					prime[prime[i] * j - 2] = 0;
//			}
//		}
//		int count = 0;
//		for (int i = 0; i < n - 2; i++) {
//			if (prime[i] != 0)
//				count++;
//		}
//		return count;
//	}
	//第三次打算把后面的两个循环并到一个，但发现速度反而更慢了，应该是因为循环中多了个判断的缘故
//	public int countPrimes(int n) {
//		if (n <= 2) return 0;
//		int[] prime = new int[n - 2];
//		for (int i = 0; i < prime.length; i++)
//			prime[i] = i + 2;
//		int count = 0;
//		for (int i = 0; i < n - 2; i++) {
//			if (i < Math.sqrt(n) - 1) {
//				if (prime[i] != 0) {
//					for (int j = 2; prime[i] * j < n; j++)
//						prime[prime[i] * j - 2] = 0;
//					count++;
//				}
//			} else if (prime[i] != 0)
//				count++;
//		}
//		return count;
//	}
	//第四次写的版本，速度和第二次差不多，有待提高
//	public int countPrimes(int n) {
//		if (n <= 2) return 0;
//		int[] prime = new int[n - 2];
//		for (int i = 0; i < prime.length; i++)
//			prime[i] = i + 2;
//		int count = 0;
//		for (int i = 0; i < (int)Math.sqrt(n) - 1; i++) {
//			if (prime[i] != 0) {
//				for (int j = 2; prime[i] * j < n; j++)
//					prime[prime[i] * j - 2] = 0;
//				count++;
//			}
//		}
//		for (int i = (int) Math.sqrt(n) - 1; i < n - 2; i++) {
//			if (prime[i] != 0)
//				count++;
//		}
//		return count;
//	}
	//第五次改写，内循环条件从j = 2改为j = prime[i]，减少了一些重复的删除，不过提高幅度不高，依然存在大量重复删减
//	public int countPrimes(int n) {
//		if (n <= 2) return 0;
//		int[] prime = new int[n - 2];
//		for (int i = 0; i < prime.length; i++)
//			prime[i] = i + 2;
//		for (int i = 0; i < Math.sqrt(n) - 1; i++) {
//			if (prime[i] != 0) {
//				for (int j = prime[i]; prime[i] * j < n; j++)
//					prime[prime[i] * j - 2] = 0;
//			}
//		}
//		int count = 0;
//		for (int i = 0; i < n - 2; i++) {
//			if (prime[i] != 0)
//				count++;
//		}
//		return count;
//	}
	//尝试使用链表，但是性能较数组更低，测试超时
	public int countPrimes(int n) {
		if (n <= 2) return 0;
		List<Integer> prime = new ArrayList<>();
		for (int i = 2; i < n; i++)
			prime.add(i);
		int count = 0;
		while (prime.get(0) < Math.sqrt(n)) {
			int deleteNum = prime.get(0);
			Iterator it = prime.iterator();
			while (it.hasNext()) {
				if (((int)it.next() % deleteNum) == 0)
					it.remove();
			}
			count ++;
		}
		return count + prime.size();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question204 q = new Question204();
		System.out.println(q.countPrimes(499979));
	}

}
