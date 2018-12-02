package snippet;
//package tcss543;
//import java.io.*;
//
///**
// * test file
// * @author WJQ
// *
// */
//
//public class tcss543 {
//	public static void main(String[] args) throws IOException{
//		tcss543 test = new tcss543();
//		if(args.length > 0){
//			SimpleGraph G = new SimpleGraph();
//			
//			GraphInput.LoadSimpleGraph(G,args[0]);
//			FordFulkerson ff = new FordFulkerson(G);
//			ScalingFordFulkerson sff = new ScalingFordFulkerson(G);
//			PreflowPush pp = new PreflowPush(G);
//			test.run(ff,sff,pp);
//		}
//		else{
//			test.testPerformance();
//			//throw the new IOException("input a text");
//		}
//	}
//	//test three algorithm
//	public static void run(FordFulkerson ff, ScalingFordFulkerson sff, PreflowPush pp){
//		System.out.println("Test three algorithms for 4 times given an input file");
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
//	
//		public static void runAlgorithm(String algorithm, FordFulkerson ff, ScalingFordFulkerson sff, PreflowPush pp){
//			int times = 4;
//			long[] runtime = new long[times];
//			long start = 0;
//			double maxflow = 0;
//			System.out.println("Test for " + algorithm);
//			for(int i = 0; i < times; i++){
//				start = System.currentTimeMillis();
//				if(algorithm.equals("Ford Fulkerson Algorithm")){
//					maxflow = ff.getMaxFlow();
//				}else if(algorithm.equals("Scaling Ford Fulkerson Algorithm")){
//					maxflow=sff.getMaxFlow();
//				}else{
//					maxflow = pp.getMaxFlow();
//				}
//				
//				runtime[i] = System.currentTimeMillis() - start;
//			}
//			long result = getave(runtime);
//			System.out.println(algorithm + ": maxflow" + maxflow);
//			System.out.println(algorithm+":runningtime"+ result+ " millisecond");
//		}
//		public static long getave(long[] runtime){
//			long sum = 0;
//			int length = runtime.length;
//			for(int i =0; i< length; i++){
//				sum = sum + runtime[i];
//			}
//			return sum/length;
//		}
//		
//		//test three algorithms
//		public static void testPerformance(){
//			System.out.println("Test three algorithms for 5 times");
//			System.out.println("=================================");
//			String[] graphTypes = {"./src/BipartiteGraph","./src/BuildGraph", "./src/MeshGenerator","./src/RandomGraph"};
//			for(int i = 0; i< graphTypes.length; i++){
//				File folder = new File(graphTypes[i]);
//				File[] testScenarios = folder.listFiles();
//				for(int j = 0; j<testScenarios.length; j++){
//					File[] testcases = testScenarios[j].listFiles();
//					for(int k = 0; k < testcases.length; k++){
//						System.out.println(testcases[k].getPath());
//						SimpleGraph G = new SimpleGraph();
//						GraphInput.LoadSimpleGraph(G,testcases[k], getPath());
//						FordFulkerson ff = new FordFulkerson(G);
//						ScalingFordFulkerson sff = new ScalingFordFulkerson(G);
//						PreflowPush pp = new PreflowPush(G);
//						System.out.println("==========test for" + testcase[k].getPath() + "===========");
//						
//						//F-F algorithm
//						runAlgorithm("Ford Fulkerson Algorithm", ff, sff,pp);
//						
//						//SF-F algorithm
//						
//						runAlgorithm("Scaling Ford Fulkerson Algorithm", ff, sff, pp);
//						
//						//P-P algorithm
//						runAlgorithm("Preflow Push Algorithm", ff, sff, pp);
//					}
//				}
//				
//			}
//		}
//		
//}
