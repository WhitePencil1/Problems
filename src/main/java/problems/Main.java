package problems;

import java.util.*;


public class Main {
	
	public static int getBSTCount(int nodesCount) {
		int[] dp = new int[nodesCount + 1];
		dp[0] = 1;
		
		for(int n = 1; n <= nodesCount; n++) {
			dp[n] = 0;
			for(int i = 1; i <= n; i++) {
				dp[n] += dp[n - i] * dp[i - 1];
			}
		}
		return dp[nodesCount];
	}
	
	public static void getRange(Node node, int L, int R, Deque<Integer> result) {
		if(node == null) return;
		
		int value = node.getValue();
		
		if(value >= L) {
			getRange(node.getLeft(), L, R, result);
		}
		
		if(value >= L && value <= R) {
			result.add(node.getValue());
		}
		
		if(value <= R) {
			getRange(node.getRight(), L, R, result);
		}
	}
	
	public static Node insertNode(Node node, int value) {
		if(node == null) {
			node = new Node(value);
			return node;
		}
		
		if(node.getValue() <= value) {
			if(node.getRight() != null) {
				insertNode(node.getRight(), value);
			}
			else {
				node.setRight(new Node(value));
			}
		}
		else {
			if(node.getLeft() != null) {
				insertNode(node.getLeft(), value);
			}
			else {
				node.setLeft(new Node(value));
			}
		}
		return node;
	}
	
	public static Node remove(Node node, int key) {
		if(node.getValue() < key) {
			node.setRight(remove(node.getRight(), key));
		}
		else if(node.getValue() > key) {
			node.setLeft(remove(node.getLeft(), key));
		}
		
		else {
			if(node.getLeft() == null && node.getRight() == null) {
				return null;
			}
			if(node.getLeft() == null) {
				return node.getRight();
			}
			if(node.getRight() == null) {
				return node.getLeft();
			}
			
			//Когда оба ребёнка
			Node maxLeft = findMaxLeftChild(node.getLeft());
			node.setValue(maxLeft.getValue());
			node.setLeft(remove(node.getLeft(), maxLeft.getValue()));
		}
		return node;
	}
	
	public static Node findMaxLeftChild(Node node) {
		if(node.getRight() == null) return node;
		return findMaxLeftChild(node.getRight());
	}
	
	public static boolean checkBalance(Node node) {
		int result = checkHeight(node);
		if(result == -1) return false;
		return true;
	}
	
	
	public static int checkHeight(Node node) {
		if(node == null) {
			return 0;
		}
		
		int leftLevels = checkHeight(node.getLeft());
		if(leftLevels == -1) return -1;
		
		int rightLevels = checkHeight(node.getRight());
		if(rightLevels == -1) return -1;
		
		if(Math.abs(leftLevels - rightLevels) > 1) return -1;
		
		return Math.max(leftLevels, rightLevels) + 1;
	}
	
	
	public static void main(String[] args) {	
		Node root = new Node(1);
		root.setLeft(new Node(2));
		
		Node r1 = root.setRight(new Node(0));
		r1.setLeft(new Node(3));
		r1.setRight(new Node(6));
		
		System.out.println(checkBalance(root));
	}
}
