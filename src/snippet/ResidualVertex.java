package snippet;
import java.util.*;
/**
 * Residual vertex of graph
 * @author WJQ
 *
 */
public class ResidualVertex {
		public List<ResidualEdge> nextEdgeList;
		private String name;
		//name for the residual vertex
		
		private boolean visited;
		private double height;
		private double excess;
		
		//set the name for residual vertices
		//give the visited nodes value of false
		
		public ResidualVertex(Object name){
			this.name = String.valueOf(name);
			this.visited = false;
			this.nextEdgeList = new LinkedList<>();
		}
		
		public String getName(){
			return name;
		}
		
		public boolean isVisited(){
			return visited;
		}
		
		public void setVisited(){
			this.visited = true;
		}
		
		public void setUnvisited(){
			this.visited = false;
		}
		
		public boolean hasUnvisitedNeigh(){
			for(int i = 0; i < nextEdgeList.size(); i++){
				ResidualEdge e = nextEdgeList.get(i);
				ResidualVertex v = e.getSecondPoint();
				if(!v.isVisited()){
					return true;
				}
				
			}
			return false;
		}
		
		public ResidualVertex getNeigh(){
			for(int i = 0; i < nextEdgeList.size(); i++){
				ResidualEdge e = nextEdgeList.get(i);
				ResidualVertex v = e.getSecondPoint();
				if(!v.isVisited()){
					return v;
				}
				
			}
			return null;
		}
		
		public double getHeight(){
			return height;
		}
		public void setHeight(double height){
			this.height = height;
		}
		public double getExcess(){
			return excess;
		}
		public void setExcess(double excess){
			this.excess = excess;
		}
}
