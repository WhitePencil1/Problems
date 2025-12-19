package problems;

import java.util.*;


public class Main {
	
	public static int[] mergeSort(int[] arr) {
		if(arr.length == 1) {
			return arr;
		}
		
		int[] sortedL = mergeSort(Arrays.copyOfRange(arr, 0, arr.length / 2));
		int[] sortedR = mergeSort(Arrays.copyOfRange(arr, arr.length / 2, arr.length));
		int[] result = new int[sortedL.length + sortedR.length];
		
		int l = 0;
		int r = 0;
		int i = 0;
		while(l < sortedL.length && r < sortedR.length) {
			if(sortedL[l] <= sortedR[r]) {
				result[i] = sortedL[l];
				l++;
			}
			else {
				result[i] = sortedR[r];
				r++;
			}
			i++;
		}
		while(l < sortedL.length) {
			result[i] = sortedL[l];
			i++;
			l++;
		}
		while(r < sortedR.length) {
			result[i] = sortedR[r];
			i++;
			r++;
		}
		
		return result;
	}
	
	public static void main(String[] args) {	
		int[] arr = {11, 2, 9, 7, 1};
		
		System.out.println(Arrays.toString(mergeSort(arr)));
	}
}
