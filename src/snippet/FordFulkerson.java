package snippet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class FordFulkerson {
	
	
	public double maxFlow(SimpleGraph G) {
		double maxflow = 0;
		double capacity = 0;
		double capacityUp = 0;
		
		ResidualGraph rg = residualGraph(G);

		
		// source 
		ResidualVertex source = rg.getSource();
		
		// capacity related to source
		Iterator<ResidualEdge> edgeList = rg.nextEdgeList(source);
		while (edgeList.hasNext()) {
			ResidualEdge n = edgeList.next();
			capacity = capacity + n.getCapacity();	
		}
		
		// find the path and store it 
		LinkedList<ResidualVertex> path = new LinkedList<ResidualVertex>();
		//boolean pathExist = STPath(rg, source, path);
		path = rg.findPath(source, path);
		//printPath(path);
		// if there is S-T path, update the Residual graph
		while (path.size()!=0) {
			UpdateResidaulGraph(rg,path);
			path = new LinkedList<ResidualVertex>();	
			path = rg.findPath(source, path);
//			printPath(path);
		}
		
		// after residual graph update, find the source node and sum the edges capacity leave the source node
		Iterator<ResidualEdge> edgeListUpdate = rg.nextEdgeList(source);	
		while (edgeListUpdate.hasNext()) {
			ResidualEdge nUpdate = edgeListUpdate.next();
			capacityUp = capacityUp + nUpdate.getCapacity();	
		}
		maxflow = capacity - capacityUp;
		return maxflow;
	}
	
	public void printPath (LinkedList<ResidualVertex> p) {
		for (int i = 0; i < p.size(); i++) {
			System.out.print(p.get(i).getName());
		}
	}
	
	public ResidualGraph UpdateResidaulGraph (ResidualGraph rg, LinkedList<ResidualVertex> path) {
		double currFlow = bottleneck(rg,path);
		
		// set all vertex is unvisited; 
		rg.getSource().setUnvisited();
		Iterator<ResidualVertex> vertexList = rg.vertices();
		while (vertexList.hasNext()) {
			ResidualVertex visitVertex = vertexList.next();
			visitVertex.setUnvisited();
		}
		
		for (int i = 1; i < path.size();i++) {
			// the start vertex i 
			ResidualVertex start = path.get(i-1);
			// the end vertex i+1
			ResidualVertex end = path.get(i);
			// forward edge between i and i + 1
			ResidualEdge e = rg.getEdge(start,end);
			if (e != null) {
				// find the original flow of the path between node i and i + 1
				double oFlow = e.getCapacity();
				// set the flow of the forward edge
				e.setCapacity(oFlow - currFlow);
				//create an new backward edge and insert into the rg
				ResidualEdge backWardEdge = new ResidualEdge(end,start,currFlow);	
				// insert the backwardedge into the rg
				rg.insertEdge(backWardEdge, end);
				// if backward edge's flow is its capacity, remove the forward
				if (oFlow == backWardEdge.getCapacity()) {
					rg.removeEdge(e);
				}
			}
		}
		return rg;
	}
	
	
	public double bottleneck (ResidualGraph rg,LinkedList<ResidualVertex> path) {
		ResidualVertex source = rg.getSource();
		double min = Double.MAX_VALUE;
		//System.out.println(path.size());
		for (int i = 1; i < path.size();i++) {
			ResidualEdge e = rg.getEdge(path.get(i-1),path.get(i));
			if (e != null) {
				double weight = e.getCapacity();
				if (weight < min) {
					min = weight;
				}
			}
		}
		return min;
	}
	
	public ResidualGraph residualGraph (SimpleGraph original) {
		ResidualGraph rg = new ResidualGraph();
		//create a hashMap to store the vertex edge and its start name and last name
		HashMap<Object,ResidualVertex> map = new HashMap<Object,ResidualVertex>();	
		
		//vertex and edges from original graph
		Iterator<Vertex> vertexList = original.vertices();
		//add vertex to residual graph
		while (vertexList.hasNext()) {
			Vertex currVertex = vertexList.next();
			Object name = currVertex.getName();
			ResidualVertex vertex = new ResidualVertex(name);
			rg.insertVertex(vertex);
			map.put(name, vertex);
		}	
		Iterator<ResidualVertex> rvl = rg.vertices();
		//read all edges from the original graph, and store them in map 
		Iterator<Edge> edgeList = original.edges();
		while(edgeList.hasNext()) {
			Edge e = edgeList.next();
			double capacity = (double)e.getData();
			Object startpointname = e.getFirstEndpoint().getName();
			Object endpointname = e.getSecondEndpoint().getName();
			ResidualEdge redge = new ResidualEdge(map.get(startpointname), map.get(endpointname),capacity);
			rg.insertEdge(redge,map.get(startpointname));
		}		
		return rg;
	}
	
	public boolean STPath(ResidualGraph rg, ResidualVertex v, LinkedList<ResidualVertex> path) {
		//if has s-t path , return true
		if (rg.findPath(v,path).size() == 0) {
			return false;
		}		
		return true;
	}
}
