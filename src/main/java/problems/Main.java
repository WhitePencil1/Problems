package problems;

import java.util.*;


public class Main {
	
	public static int shiftUp(int[] heap, int idx) {
		if(idx == 1) {
			return idx;
		}
		int parent = idx / 2;
		if(heap[parent] < heap[idx]) {
			int temp = heap[idx];
			heap[idx] = heap[parent];
			heap[parent] = temp;
			return shiftUp(heap, parent);
		}
		else {
			return idx;
		}
	}
	
	
	public static int shiftDown(int[] heap, int idx) {
		int left = idx * 2;
		int right = idx * 2 + 1;
		
		if(heap.length - 1 < left) return idx;
		int largestIndex = left;
		if(right <= heap.length - 1 && heap[left] < heap[right]) {
			largestIndex = right;
		}

		if(heap[idx] >= heap[largestIndex]) {
			return idx;
		}
		
		int temp = heap[idx];
		heap[idx] = heap[largestIndex];
		heap[largestIndex] = temp;
		
		return shiftDown(heap, largestIndex);
	}
	
	
	public static void main(String[] args) {	
		int[] heapUp = {0, 12, 6, 8, 3, 15, 7};
		System.out.println(shiftUp(heapUp, 5));
		
		int[] heapDown = {0, 12, 1, 8, 3, 4, 7};
		System.out.println(shiftDown(heapDown, 2));
	}
}
