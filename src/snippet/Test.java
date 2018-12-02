package snippet;

import java.io.*;

public class Test {

	/**
	 * test file
	 * 
	 * @author WJQ
	 *
	 */

	public static void main(String[] args) {
		Test test = new Test();
		if (args.length > 0) {
			SimpleGraph G = new SimpleGraph();

			GraphInput.LoadSimpleGraph(G, args[0]);

			ScalingFordFulkerson sff = new ScalingFordFulkerson(G);

			test.run(sff);
		} else {
			test.testPerformance();
			// throw the new IOException("input a text");
		}
	}

	// test three algorithm
	public static void run(ScalingFordFulkerson sff) {
		System.out.println("Test algorithm for 3 times given an input file");
		System.out.println("=====================================================");

		// run scaling ford-fulkerson
		runAlgorithm("Scaling Ford Fulkerson Algorithm", sff);

	}

	public static void runAlgorithm(String algorithm, ScalingFordFulkerson sff) {
		int times = 3;
		long[] runtime = new long[times];
		long start = 0;
		double maxflow = 0;
		System.out.println("Test for " + algorithm);
		for (int i = 0; i < times; i++) {
			start = System.currentTimeMillis();
			if (algorithm.equals("Scaling Ford Fulkerson Algorithm")) {
				maxflow = sff.getMaxFlow();
			}

			runtime[i] = System.currentTimeMillis() - start;
		}
		long result = getave(runtime);
		System.out.println(algorithm + ": maxflow" + maxflow);
		System.out.println(algorithm + ":runningtime" + result + " millisecond");
	}

	public static long getave(long[] runtime) {
		long sum = 0;
		int length = runtime.length;
		for (int i = 0; i < length; i++) {
			sum = sum + runtime[i];
		}
		return sum / length;
	}

	// test three algorithms
	public static void testPerformance() {
		System.out.println("Test algorithm for 3 times");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		String[] graphTypes = { "./snippet/Random"};
		for (int i = 0; i < graphTypes.length; i++) {
			File folder = new File(graphTypes[i]);
			File[] testScenarios = folder.listFiles();
			for (int j = 0; j < testScenarios.length; j++) {
				File[] testcases = testScenarios[j].listFiles();
				for (int k = 0; k < testcases.length; k++) {
					System.out.println(testcases[k].getPath());
					SimpleGraph G = new SimpleGraph();
					GraphInput.LoadSimpleGraph(G, testcases[k].getPath());
					
					ScalingFordFulkerson sff = new ScalingFordFulkerson(G);

					System.out.println("==========>>>>>>>>test for" + testcases[k].getPath() + "<<<<<<<<<<<<===========");

					// SF-F algorithm

					runAlgorithm("Scaling Ford Fulkerson Algorithm", sff);

				}
			}

		}
	}

}
