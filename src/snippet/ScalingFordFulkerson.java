package snippet;
import java.util.*;

/**
 * A class to represent scaling Ford-Fulkerson algorithm
 * @author WJQ
 * @version 1.0
 */
public class ScalingFordFulkerson {
	public ResidualGraph graph; //the residual graph
	public double maxflow;
	public ResidualVertex source;
	public SimpleGraph sGraph;
	
	public ScalingFordFulkerson(SimpleGraph sgraph){
		this.sGraph = sgraph;
	}
	public double getMaxFlow(){
		this.graph = new ResidualGraph();
		
		this.maxflow = 0;
		//initial maxflow value to 0;
		
		addVertices(sGraph);
		//add all vertex of input graph into vertex list 
		
		this.source = graph.getSource();
		
		double maxSourceCap = getMaxSourceCap(sGraph);
		//get the max capacity for each edge out of s
	
		int delta = getDelta(maxSourceCap);
		//calculate the delta with largest power of 2 but no larger than max-cap out of s
	
		while(delta >= 1){
			addEdges(sGraph,delta);
		
			List<ResidualVertex> path;
		
			while((path = findstpath1()).size() != 0){
				double minValue = getMinCap(path);
				updateResidualGraph(path, minValue);
				maxflow += minValue;
			}
			delta = delta /2;
		}
			return maxflow;
	}
	
	private void addVertices(SimpleGraph sGraph){
		List<Vertex> vertices = sGraph.vertexList;
		for(int i = 0; i < vertices.size(); i++){
			ResidualVertex v = new ResidualVertex((vertices.get(i)).getName());
			graph.insertVertex(v);
		}
	}
	
	private void addEdges(SimpleGraph sGraph, int delta){
		List<Edge> edges = sGraph.edgeList;
		for(int i = 0; i < edges.size(); i++){
			Edge e = edges.get(i);
			if((double)e.getData() >= delta && (double)e.getData() < delta * 2){
				Vertex v1 = e.getFirstEndpoint();
				Vertex u1 = e.getSecondEndpoint();
				ResidualVertex v2 = null;
				ResidualVertex u2 = null;
				Iterator vertices = graph.vertices();
				while(vertices.hasNext()){
					ResidualVertex curV = (ResidualVertex) vertices.next();
					if(curV.getName().equalsIgnoreCase((String) v1.getName())){
						v2 = curV;
					}
					if(curV.getName().equalsIgnoreCase((String) u1.getName())){
						u2 = curV;
						
					}
					
				}
				ResidualEdge edge = new ResidualEdge(v2, u2, (double) e.getData());
				graph.insertEdge(edge, v2);
			}
			
		}
	}
	
	private double getMaxSourceCap(SimpleGraph sg){
		double max = 0;
		Iterator vertices = sg.vertices();
		while(vertices.hasNext()){
			Vertex v = (Vertex)vertices.next();
			if(v.getName().toString().equalsIgnoreCase("s")){
				List<Edge> edges = v.incidentEdgeList;
				for (int i = 0; i < edges.size(); i++){
					max = Math.max(max, (double)edges.get(i).getData());
				}	
			}	
		}
		return max;
	}
	
	private int getDelta(double max){
		int delta = 1;
		while(delta * 2 <= max){
			delta *=2;
		}
		return delta;
	}
	
	private LinkedList<ResidualVertex> findstpath1(){
		LinkedList<ResidualVertex> path = new LinkedList<>();
		path = graph.findPath(source, path);
		updateVisitedStatus();
		source.setUnvisited();
		graph.moveToEnd(source);
		return path;
	}	
	
	private void updateVisitedStatus(){
		Iterator vertices = graph.vertices();
		while (vertices.hasNext()){
			ResidualVertex v = (ResidualVertex) vertices.next();
			v.setUnvisited();
		}
	}
	private double getMinCap(List<ResidualVertex> path){
		double min = Double.MAX_VALUE;
		for(int i = 0; i < path.size() - 1; i++){
			ResidualVertex v = path.get(i);
			ResidualVertex u = path.get(i+1);
			ResidualEdge e = graph.getEdge(v,u);
			min = Math.min(min, e.getCapacity());
		}
		return min;
	}
	
	private void updateResidualGraph(List<ResidualVertex> path, double minValue){
		for(int i = 0; i < path.size() -1; i++){
			ResidualVertex v = path.get(i);
			ResidualVertex u = path.get(i+1);
			ResidualEdge forward = graph.getEdge(v,u);
			if(forward.getCapacity() == minValue){
				graph.removeEdge(forward);
			}else{
				forward.setCapacity(forward.getCapacity() - minValue);
				graph.moveToEnd(forward);
			}
			ResidualEdge backward = graph.getEdge(u,v);
			if(backward == null){
				backward = new ResidualEdge(u, v, minValue);
				graph.insertEdge(backward, u);
			}else{
				backward.setCapacity(backward.getCapacity() + minValue);
				graph.moveToEnd(backward);
			}
		}
	}	
	

	private LinkedList<ResidualVertex> findSTpath() {
		//source.setVisited();
		LinkedList<ResidualVertex> path = new LinkedList<>();
		path = graph.findPath(source, path);
		updateVisitedStatus();
		source.setUnvisited();
		graph.moveToEnd(source);
		return path;
	}
	
	
}
