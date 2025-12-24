package problems;

import java.io.*;
import java.util.*;


public class Main {
	
	
	public static List<List<Integer>> getComponents(List<List<Integer>> list) {
		
		int[] colors = new int[list.size()];
		Arrays.fill(colors, 0);
		int colorsCounter = 0;
		
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 1; i < list.size(); i++) {
			if(colors[i] != 0) {
				continue;
			}
			colorsCounter++;
			stack.push(i);
			while(!stack.isEmpty()) {
				int v = stack.pop();
				if(colors[v] == 0) {
					colors[v] = colorsCounter;
					for(var child : list.get(v)) {
						if(colors[child] == 0) {
							stack.push(child);
						}
					}
				}
			}
		}

		List<List<Integer>> components = new ArrayList<>();
		for(int i = 0; i <= colorsCounter; i++) {
			components.add(new ArrayList<>());
		}
		for(int i = 1; i < colors.length; i++) {
			components.get(colors[i]).add(i);
		}
		return components;
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
	public static void main(String[] args) {	
		int vertexNum = 4;
		List<List<Integer>> adjList = getAdjacencyList(new Edge[] {new Edge(2, 3), new Edge(2, 1), new Edge(4, 3)}, vertexNum);
		List<List<Integer>> components = getComponents(adjList);
		System.out.println(components);
	}
}
