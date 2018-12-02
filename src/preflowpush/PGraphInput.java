package preflowpush; /**
 * Written by Ed Hong UWT Feb. 19, 2003.
 * Modified by Donald Chinn May 14, 2003.
 * Modified by Donald Chinn December 11, 2003.
 */

import preflowpush.PreflowPush;

import java.io.*;
import java.util.*;

/**
 * A class that can read a graph (in a specific format) from a file.
 *
 * @author edhong
 * @version 0.0
 */
public class PGraphInput {

    static PResidualGraph rg;

    /**
     * Load graph data from a text file via user interaction.
     * This method asks the user for a directory and path name.
     * It returns a hashtable of (String, Vertex) pairs.
     * newgraph needs to already be initialized.
     *
     * @param newgraph a simple graph
     * @returns a hash table of (String, Vertex) pairs
     */
    public static Hashtable LoadSimpleGraph(SimpleGraph newgraph) {
        System.out.print("Please enter the full path and file name for the input data: ");
        String userinput;
        userinput = KeyboardReader.readString();
        return LoadSimpleGraph(newgraph, userinput);
    }

    /**
     * Load graph data from a text file.
     * The format of the file is:
     * Each line of the file contains 3 tokens, where the first two are strings
     * representing vertex labels and the third is an edge weight (a double).
     * Each line represents one edge.
     * <p>
     * This method returns a hashtable of (String, Vertex) pairs.
     *
     * @param newgraph        a graph to add edges to. newgraph should already be initialized
     * @param pathandfilename the name of the file, including full path.
     * @returns a hash table of (String, Vertex) pairs
     */
    public static Hashtable LoadSimpleGraph(SimpleGraph newgraph, String pathandfilename) {
        BufferedReader inbuf = InputLib.fopen(pathandfilename);
        System.out.println("Opened " + pathandfilename + " for input.");
        String line = InputLib.getLine(inbuf); // get first line
        StringTokenizer sTok;
        int n, linenum = 0;
        Hashtable table = new Hashtable();
        SimpleGraph sg = newgraph;
        rg = new PResidualGraph();

        while (line != null) {
            linenum++;
            sTok = new StringTokenizer(line);
            n = sTok.countTokens();
            if (n == 3) {
                Double edgedata;
                Vertex v1, v2, v1Copy, v2Copy;
                String v1name, v2name, v1CopyName, v2CopyName;

                v1name = sTok.nextToken();
                v1CopyName = v1name + "Copy";
                v2name = sTok.nextToken();
                v2CopyName = v2name + "Copy";
                edgedata = new Double(Double.parseDouble(sTok.nextToken()));
                v1 = (Vertex) table.get(v1name);
                v1Copy = (Vertex) table.get(v1CopyName);
                if (v1 == null) {
//                    System.out.println("New vertex " + v1name);
                    v1 = sg.insertVertex(null, v1name);
                    table.put(v1name, v1);
                    // Zac updated
                    v1Copy = rg.insertVertex(v1.getData(), v1.getName());
                    PVertexData vd = new PVertexData();
                    v1Copy.setData(vd);
                    table.put(v1CopyName, v1Copy);
                }
                v2 = (Vertex) table.get(v2name);
                v2Copy = (Vertex) table.get(v2CopyName);
                if (v2 == null) {
//                      System.out.println("New vertex " + v2name);
                    v2 = sg.insertVertex(null, v2name);
                    table.put(v2name, v2);
                    // Zac updated
                    v2Copy = rg.insertVertex(v2.getData(), v2.getName());
                    PVertexData vd = new PVertexData();
                    v2Copy.setData(vd);
                    table.put(v2CopyName, v2Copy);
                }
//              System.out.println("Inserting edge (" + v1name + "," + v2name + ")" + edgedata);
                sg.insertEdge(v1, v2, edgedata, null);
                // Zac updated
                rg.insertEdge(v1Copy, v2Copy, edgedata, null);
            } else {
                System.err.println("Error:invalid number of tokens found on line " + linenum + "!");
                return null;
            }
            line = InputLib.getLine(inbuf);
        }

        InputLib.fclose(inbuf);
        System.out.println("Successfully loaded " + linenum + " lines. ");
        return table;
    }

    /**
     * Code to test the methods of this class.
     */
    public static void main(String args[]) {
        SimpleGraph G;
        G = new SimpleGraph();
//        Hashtable table = LoadSimpleGraph(G, args[0]);
        Hashtable table = LoadSimpleGraph(G, "./snippet/Sample");

        // Call Preflow-Push
        long startTime = System.currentTimeMillis();
        PreflowPush preflowPush = new PreflowPush(rg);
        long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " ms.");
    }

    
    public static PreflowPush getPreflowPush(String pathandfilename) {
    	SimpleGraph G = new SimpleGraph();
        Hashtable table = LoadSimpleGraph(G, pathandfilename);
        return new PreflowPush(rg);
    }
}



	

	
	
	