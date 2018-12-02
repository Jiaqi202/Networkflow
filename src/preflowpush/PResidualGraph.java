package preflowpush;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PResidualGraph extends SimpleGraph {

    private Map<Edge, Edge> edgeMap;

    public PResidualGraph() {
        super();
//        vertexList = new LinkedList();
//        edgeList = new LinkedList();
        edgeMap = new HashMap<>();
    }

    @Override
    public Edge insertEdge(Vertex v, Vertex w, Object data, Object name) {
        Edge forward = new Edge(v, w, data, v+"to"+w);
        Edge backward = new Edge(w, v, 0.0, w+"to"+v);
        edgeList.addLast(forward);
        edgeList.addLast(backward);
        v.incidentEdgeList.addLast(forward);
        w.incidentEdgeList.addLast(backward);
        edgeMap.put(forward, backward);
        edgeMap.put(backward, forward);
        return forward;
    }

    public Edge getReverseEdge(Edge edge) {
        return edgeMap.get(edge);
    }
}
