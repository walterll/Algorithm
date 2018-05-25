package com.chapter1.fibonacci;

public class Fibonacci {
	
	public static long F(int n)
	{
		if(n == 0) return 0;
		if(n == 1) return 1;
		else return F(n-2) + F(n-1);
			
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++)
		{
			System.out.println(i + ": " + F(i));
		}
	}
		
	

}
