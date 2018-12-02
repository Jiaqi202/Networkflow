package snippet;
import java.io.File;
import java.io.IOException;

import preflowpush.PreflowPush;
import preflowpush.PResidualGraph;
import preflowpush.PGraphInput;

/**
 * test file
 * @author WJQ
 *
 */

public class tcss543 {
	public static void main(String[] args) throws IOException{
		tcss543 test = new tcss543();
//		if(args.length > 0){
//			SimpleGraph G = new SimpleGraph();
//			
//			GraphInput.LoadSimpleGraph(G,args[0]);
//			FordFulkerson ff = new FordFulkerson();
//			ScalingFordFulkerson sff = new ScalingFordFulkerson(G);
//			PreflowPush pp = new PreflowPush(G);
//			test.run(ff,sff,pp);
//		}
//		else{
			test.testPerformance();
			//throw the new IOException("input a text");
//		}
	}
	
	//test three algorithm
//	public static void run(FordFulkerson ff, ScalingFordFulkerson sff, PreflowPush pp){
//		System.out.println("Test three algorithms for 3 times given an input file");
//		System.out.println("=====================================================");
//		
//		// run ford-fulkerson
//		runAlgorithm("Ford Fulkerson Algorithm", ff, sff, pp);
//		
//		//run scaling ford-fulkerson
//		runAlgorithm("Scaling Ford Fulkerson Algorithm", ff, sff, pp);
//		
//		//run preflow push
//		runAlgorithm("Preflow Push Algorithm", ff, sff, pp);
//	
//	}
	
	public static double findMaxFlow(String path) {
		SimpleGraph G = new SimpleGraph();
		GraphInput.LoadSimpleGraph(G,path);
		ScalingFordFulkerson sff = new ScalingFordFulkerson(G);
		return sff.getMaxFlow();
	}
	
		public static void runAlgorithm(String algorithm, SimpleGraph G, FordFulkerson ff, ScalingFordFulkerson sff, PreflowPush pp){
			int times = 3;
			long[] runtime = new long[times];
			long start = 0;
			double maxflow = 0;
			System.out.println("Test for " + algorithm);
			for(int i = 0; i < times; i++){
				start = System.currentTimeMillis();
				if(algorithm.equals("Ford Fulkerson Algorithm")){
					maxflow = ff.maxFlow(G);
				}else if(algorithm.equals("Scaling Ford Fulkerson Algorithm")){
					maxflow = sff.getMaxFlow();

				}else{
					maxflow = pp.findMaxFlow();
				}
				
				runtime[i] = System.currentTimeMillis() - start;
			}
			long result = getave(runtime);
			System.out.println("\n"+algorithm + ": maxflow " + maxflow);
			System.out.println(algorithm+":runningtime "+ result+ " millisecond");
		}
		public static long getave(long[] runtime){
			long sum = 0;
			int length = runtime.length;
			for(int i =0; i< length; i++){
				sum = sum + runtime[i];
			}
			return sum/length;
		}
		
		//test three algorithms
		public static void testPerformance(){
			System.out.println("Test three algorithms for 3 times");
			System.out.println("=================================");
			String[] graphTypes = {"./src/snippet/Sample"};
//			"./snippet/Bipartite","./snippet/FixedDegree", "./snippet/Mesh","./snippet/Random ,"./snippet/FixedDegree", "./snippet/Mesh","./snippet/Random""
			
			for(int i = 0; i< graphTypes.length; i++){
				File folder = new File(graphTypes[i]);
				File[] testScenarios = folder.listFiles();
				for(int j = 0; j<testScenarios.length; j++){
					File[] testcases = testScenarios[j].listFiles();
					for(File testcase : testcases){
						
//						System.out.println("Ford Fulkerson Algorithm for file: " + testcase.getPath());
//						FFMain.findMaxFlow(testcase.getPath());
//						System.out.checkError()；
//						
//						System.out.println("Scaling Ford Fulkerson for file: " + testcase.getPath());
//						findMaxFlow(testcase.getPath());
//						
//						System.out.println("Preflow Push Algorithm for file: " + testcase.getPath());
//						PPMain.findMaxFlow(testcase.getPath());
						
						System.out.println(testcase.getPath());
						SimpleGraph G = new SimpleGraph();
						GraphInput.LoadSimpleGraph(G,testcase.getPath());
						
						FordFulkerson ff = new FordFulkerson();
//						FordFulkerson ff = null;
						ScalingFordFulkerson sff = new ScalingFordFulkerson(G);
//						ScalingFordFulkerson sff = null;
						PreflowPush pp = PGraphInput.getPreflowPush(testcase.getPath());
//						PreflowPush pp = null;
						System.out.println("==========>test for" + testcase.getPath() + "<===========");
						
//						F-F algorithm
						runAlgorithm("Ford Fulkerson Algorithm", G, ff, sff,pp);
						
						//SF-F algorithm
						
						runAlgorithm("Scaling Ford Fulkerson Algorithm", G, ff, sff, pp);
						
						//P-P algorithm
						runAlgorithm("Preflow Push Algorithm", G, ff, sff, pp);
					}
				}
				
			}
		}
		
}
