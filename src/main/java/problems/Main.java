package problems;

import java.io.*;
import java.util.*;


public class Main {
	
	
	public static List<Integer> dijkstra(List<List<Edge>> graph, int start, int end) {
		
		int n = graph.size();
		boolean[] visited = new boolean[n];
		int[] dist = new int[n];
		int[] prev = new int[n];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(prev, -1);
		
		dist[start] = 0;
		
		for(int i = 1; i < n; i++) {
			
			int v = -1;
			for(int j = 1; j < n; j++) {
				if(!visited[j] && (v == -1 || dist[j] < dist[v])) {
					v = j;
				}
			}
			
			if(dist[v] == Integer.MAX_VALUE) break;
			
			
			visited[v] = true;
			for(var e : graph.get(v)) {
				if(dist[e.to] > dist[v] + e.w) {
					dist[e.to] = dist[v] + e.w;
					prev[e.to] = v;
				}
			}
		}
		
		
		List<Integer> path = new ArrayList<>();
		
		int curV = end;
		
		while(curV != -1) {
			path.add(curV);
			curV = prev[curV];
		}
		Collections.reverse(path);
		
		return path;
	}
	
	
//	public static List<List<Integer>> getAdjacencyList(Edge[] edges, int vertexNum) {
//		List<List<Integer>> resultList = new ArrayList<>();
//
//		for (int i = 0; i <= vertexNum; i++) {
//			resultList.add(new ArrayList<>());
//		}
//
//		for(int i = 0; i < edges.length; i++) {
//			int curVertex = edges[i].start;
//			int reverseVertex = edges[i].end;
//
//			resultList.get(curVertex).add(reverseVertex);
//			resultList.get(reverseVertex).add(curVertex);
//		}
//
//		for(int i = 0; i < resultList.size(); i++) {
//			Collections.sort(resultList.get(i), (a, b) -> b - a);
//		}
//		return resultList;
//	}
	
	public record Edge(int to, int w) {};
	public static void main(String[] args) {	
		List<List<Edge>> graph = new ArrayList<>();
		graph.add(List.of());
		graph.add(List.of(new Edge(2, 2), new Edge(3, 1)));
		graph.add(List.of(new Edge(5, 4), new Edge(4, 3)));
		graph.add(List.of());
		graph.add(List.of(new Edge(5, 4)));
		graph.add(List.of());
		System.out.println(dijkstra(graph, 1, 5));
	}
}
