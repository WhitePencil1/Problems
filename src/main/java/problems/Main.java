package problems;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
	
	public static String mainDfs(List<List<Integer>> adjList, int startEdge) {
		Colors[] colors = new Colors[adjList.size()];
		Arrays.fill(colors, Colors.WHITE);
		return getDfsSequence(adjList, startEdge, colors);
	}

	public static String getDfsSequence(List<List<Integer>> adjList, int startEdge, Colors[] colors) {
		Deque<Integer> stack = new ArrayDeque<>();
		StringBuilder resultSequence = new StringBuilder();
		
		stack.push(startEdge);
		while(!stack.isEmpty()) {
			
			Integer vertex = stack.pop();
			
			
			if(colors[vertex] == Colors.WHITE) {
				colors[vertex] = Colors.GRAY;
				resultSequence.append(vertex + " ");
				for(var adjVertex : adjList.get(vertex)) {
					if(colors[adjVertex] == Colors.WHITE) {
						stack.push(adjVertex);
					}
				}	
			}
			else if(colors[vertex] == Colors.GRAY) {
				colors[vertex] = Colors.BLACK;
			}
			
		}
		return resultSequence.toString();
	}
	
	public static List<List<Integer>> getAdjacencyList(Edge[] edges, int vertexNum) {
		List<List<Integer>> resultList = new ArrayList<>();
		
		for (int i = 0; i <= vertexNum; i++) {
			resultList.add(new ArrayList<>());
		}
		
		for(int i = 0; i < edges.length; i++) {
			int curVertex = edges[i].start;
			int reverseVertex = edges[i].end;
			
			resultList.get(curVertex).add(reverseVertex);
			resultList.get(reverseVertex).add(curVertex);
		}
		
		for(int i = 0; i < resultList.size(); i++) {
			Collections.sort(resultList.get(i), (a, b) -> b - a);
		}
		return resultList;
	}
	
	
	
	public record Edge(int start, int end) {};
	
	public enum Colors {WHITE, BLACK, GRAY};
	
	public static void main(String[] args) {	
		int vertexNum = 2;
		int startEdge = 1;
		Edge[] edges = {new Edge(1, 2)};
		
		
		List<List<Integer>> adjacList = getAdjacencyList(edges, vertexNum);
		System.out.println(mainDfs(adjacList, startEdge));
	}
}
