package problems;

import java.util.*;


public class Main {
	
	public static int[] getEnoughDays(int[] sum, int bikeCost) {
		int firstDay = getEnoughDay(sum, bikeCost);
		int secondDay = getEnoughDay(sum, bikeCost * 2);

		return new int[] {firstDay, secondDay};		
	}
	
//	public static int getEnoughDay(int[] sum, int left, int right, int minCost) {
//		int mid = left + ((right - left) / 2);
//		
//		
//		if(mid - 1 >= left) {
//			if(sum[mid - 1] >= minCost) {
//				return getEnoughDay(sum, left, mid, minCost);
//			}
//		}
//		
//		if(sum[mid] >= minCost) {
//			return mid + 1;
//		}
//		
//		if(mid + 1 <= right) {
//			return getEnoughDay(sum, mid + 1, right, minCost);
//		}
//		
//		return -1;
//	}
	
	public static int getEnoughDay(int[] sum, int minCost) {
		int left = 0;
		int right = sum.length - 1;
		int answer = -1;
		
		
		while(left <= right) {
			int mid = left + ((right - left) / 2);
			
			if(sum[mid] >= minCost) {
				answer = mid;
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
			
			
			
		}
		return answer == -1 ? -1 : answer + 1;
	}
	
	
	public static void main(String[] args) {	
		int[] sum = {1, 2, 4, 4, 4, 4};
		int bikeCost = 10;
		int[] days = getEnoughDays(sum, bikeCost);
		System.out.println(Arrays.toString(days));
	}
}
