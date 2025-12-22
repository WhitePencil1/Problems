package problems;

import java.util.*;


public class Main {
	
	public static List<Pair> mergeClumbs(Pair[] clumbs) {
		if(clumbs.length == 0) {
			return List.of();
		}
		Arrays.sort(clumbs, (clumb1, clumb2) -> clumb1.from() - clumb2.from());
		
		List<Pair> result = new ArrayList<>();
		
		int startClumb = clumbs[0].from();
		int endClumb = clumbs[0].to();
		
		for(int i = 1; i < clumbs.length; i++) {
			Pair next = clumbs[i];
			
			if(next.from() <= endClumb) {
				//Пересечение
				endClumb = Math.max(endClumb, next.to());
			}
			else {
				result.add(new Pair(startClumb, endClumb));
				startClumb = next.from();
				endClumb = next.to();
			}
		}
		
		result.add(new Pair(startClumb, endClumb));
		return result;
	}
	
	public static void main(String[] args) {	
		Pair p1 = new Pair(7, 8);
		Pair p2 = new Pair(7, 8);
		Pair p3 = new Pair(2, 3);
		Pair p4 = new Pair(6, 10);
		
		List<Pair> result = mergeClumbs(new Pair[] {p1, p2, p3, p4});
		
		System.out.println(result.get(0).from() + " " + result.get(0).to());
		System.out.println(result.get(1).from() + " " + result.get(1).to());
		
		System.out.println(Math.max(6, (Integer) null));
	}
}
