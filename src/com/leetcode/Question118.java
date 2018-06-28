package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**********************
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 示例:
 * 输入: 5
 * 输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 * @author Walterll
 *
 */
//使用迭代将上一行的数计算本行的数，成绩前1%，大家都一样
public class Question118 {
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows < 1) return list;
        List<Integer> row = new ArrayList<>();
        row.add(1);
        list.add(row);
        for (int i = 2; i <= numRows; i++) {
        	row = generateRow(row);
        	list.add(row);
        }
        return list;
    }
	public List<Integer> generateRow(List<Integer> row) {
		List<Integer> thisRow = new ArrayList<>();
		int last = 0;
		for (Integer i : row) {
			thisRow.add(i + last);
			last = i;
		}
		thisRow.add(1);
		return thisRow;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Question118 q = new Question118();
		System.out.println(q.generate(7));
	}

}
