package snippet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Driver {

	public static void main(String[] args) {
		SimpleGraph G = new SimpleGraph();
		//GraphInput.LoadSimpleGraph(G, "/Users/moranwang/Desktop/random.txt");
		GraphInput.LoadSimpleGraph(G, "./snippet/Sample/Bipartite/g1.txt");
		//GraphInput.LoadSimpleGraph(G, "/Users/moranwang/Desktop/randomTest.txt");

		FordFulkerson ff = new FordFulkerson();
		System.out.println(ff.maxFlow(G));
		
	}
}
