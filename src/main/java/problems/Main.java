package problems;

import java.io.*;
import java.util.*;


public class Main {
	
	
	public static String getOrderSeq(List<List<Integer>> edges, int start) {
		Colors[] colors = new Colors[edges.size()];
		Arrays.fill(colors, Colors.WHITE);
		
		StringBuilder builder = new StringBuilder();
		
		Queue<Integer> planned = new ArrayDeque<>();
		planned.add(start);
		colors[start] = Colors.GRAY;
		
		while(!planned.isEmpty()) {
			int v = planned.poll();
			builder.append(v).append(" ");
			for(var child : edges.get(v)) {
				if(colors[child] == Colors.WHITE) {
					colors[child] = Colors.GRAY;
					planned.add(child);
				}
			}
			colors[v] = Colors.BLACK;
		}
		return builder.toString();
	}
	
	
	public static Deque<Integer> getMinDist(List<List<Integer>> edges, int start, int end) {
		Colors[] colors = new Colors[edges.size()];
		Arrays.fill(colors, Colors.WHITE);
		
		Integer[] previous = new Integer[edges.size()];
		Integer[] distance = new Integer[edges.size()];
		
		Queue<Integer> planned = new ArrayDeque<>();
		planned.add(start);
		colors[start] = Colors.GRAY;
		distance[start] = 0;
		
		while(!planned.isEmpty()) {
			int v = planned.poll();
			for(var child : edges.get(v)) {
				if(colors[child] == Colors.WHITE) {
					
					distance[child] = distance[v] + 1;
					previous[child] = v;
					
					colors[child] = Colors.GRAY;
					planned.add(child);
				}
			}
			colors[v] = Colors.BLACK;
		}
		
		if(distance[end] == null) {
			return new ArrayDeque<>();
		}
		
		Deque<Integer> path = new ArrayDeque<>();
		Integer cur = end;
		while(cur != null) {
			path.addFirst(cur);
			cur = previous[cur];
		}
		return path;
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
			Collections.sort(resultList.get(i), (a, b) -> a - b);
		}
		return resultList;
	}
	
	
	public record Edge(int start, int end) {};
	public enum Colors {WHITE, BLACK, GRAY};
	public static void main(String[] args) {	
		int vertexNum = 5;
		int startV = 1;
		int endV = 5;
		List<List<Integer>> adjList = getAdjacencyList(new Edge[] {new Edge(1, 2), new Edge(1, 3), new Edge(2, 5), new Edge(2, 4), new Edge(3, 4), new Edge(4, 5)}, vertexNum);
		System.out.println(getMinDist(adjList, startV, endV));

	}
}
