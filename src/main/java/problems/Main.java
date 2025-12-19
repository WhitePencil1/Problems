package problems;

import java.util.*;


public class Main {
	
	public static void printAllSeq(int n) {
		printSeq(n, 0, 0, "");
	}
	
	public static void printSeq(int n, int open, int close, String prefix) {
		if(open == n && open == close) {
			System.out.println(prefix);
		}
		
	    if(open < n) {
	    	printSeq(n, open + 1, close, prefix + "(");
	    }
	    if(close < open) {
	    	printSeq(n, open, close + 1, prefix + ")");
	    }
		
	}
	
	
	public static void main(String[] args) {	
		printAllSeq(3);
	}
}
