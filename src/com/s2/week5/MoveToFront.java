package com.s2.week5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
	// apply move-to-front encoding, reading from standard input and writing to standard output
	//在eclipse测试的版本，因为eclipse无法正常使用StdIn测试
    public static void encode(String str) {
    	int R = 256;
    	char[] letter = new char[R];
    	for (int i = 0; i < R; i++)
    		letter[i] = (char)i;
    	for (int i = 0; i < str.length(); i++) {
    		int index = 0;
//    		while (letter[index] != str.charAt(i)) 	//原来这样写的，有bug，因为循环条件letter[index]
//    			letter[++index]--;					//被上一个循环中的letter[++index]--修改，无法找到正确位置
    		
    		//第二次改写，依然存在bug
//    		if (letter[index] != str.charAt(i)) {
//    			while (letter[index + 1] != str.charAt(i))
//    				letter[++index]--;				//关键在于对于调整过的字符，这样操作会在下一次调整中将其抹去，产生bug
//    			letter[0] = letter[index + 1];		//因为使用ascii码来移位无法获得调整过的字符
//    			letter[++index]--;
//    		}
    		
    		//第三次改写，只能先找到字符所在位置，再反向调整之前的字符
    		while (letter[index] != str.charAt(i))
    			index++;
    		System.out.println(index);
    		while (index > 0)
    			letter[index] = letter[--index];
    		letter[0] = str.charAt(i);

    	}
    }
    
 // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
    	int R = 256;
    	char[] letter = new char[R];
    	for (int i = 0; i < R; i++)
    		letter[i] = (char)i;
    	while (!BinaryStdIn.isEmpty()) {
    		int index = 0;
    		char c = BinaryStdIn.readChar(8);
    		while (letter[index] != c)
    			index++;
    		BinaryStdOut.write(index, 8);
    		while (index > 0)
    			letter[index] = letter[--index];
    		letter[0] = c;
     	}
    	BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
    	int R = 256;
    	char[] letter = new char[R];
    	for (int i = 0; i < R; i++)
    		letter[i] = (char)i;
    	while (!BinaryStdIn.isEmpty()) {
    		int index = BinaryStdIn.readChar(8);
    		char codeLetter = letter[index];
    		BinaryStdOut.write(codeLetter, 8);
    		while (index > 0)
    			letter[index] = letter[--index];
    		letter[0] = codeLetter;
    	}
    	BinaryStdOut.flush();		//第一次提交没写这句，造成很多错误没能通过
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
//    	encode("ABRACADABRA!");
//    	int[] code = new int[]{65,66,82,2,68,1,69,1,4,4,2,38};
//    	decode(code);
    	if ("-".equals(args[0]))
    		encode();
    	else if ("+".equals(args[0]))
    		decode();
    	else throw new IllegalArgumentException("Illegal command line argument");
    }
}
