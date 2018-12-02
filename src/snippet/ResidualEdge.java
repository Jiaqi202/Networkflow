package snippet;
/**
 * Residual edge for the graph
 * @author WJQ
 *
 */
public class ResidualEdge {
		private double capacity;
		private ResidualVertex v;
		private ResidualVertex u;
		
		//set the vertex v,u flow and capacity value of the residual edge
		//v one end of the edge
		//u another end of edge
		//capacity of the edge
		
		public ResidualEdge(ResidualVertex v, ResidualVertex u, double capacity){
			this.v = v;
			this.u = u;
			this.capacity = capacity;
		}
		
		public double getCapacity(){
			return capacity;
		}
		
		public void setCapacity(double value){
			this.capacity = value;
		}
		
		public ResidualVertex getFirstPoint(){
			return v;
		}
		
		public ResidualVertex getSecondPoint(){
			return u;
		}
}
