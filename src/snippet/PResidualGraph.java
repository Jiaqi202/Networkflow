package snippet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PResidualGraph extends PSimpleGraph {

    private Map<PEdge, PEdge> edgeMap;

    public PResidualGraph() {
        super();
//        vertexList = new LinkedList();
//        edgeList = new LinkedList();
        edgeMap = new HashMap<>();
    }

    @Override
    public PEdge insertEdge(PVertex v, PVertex w, Object data, Object name) {
        PEdge forward = new PEdge(v, w, data, v+"to"+w);
        PEdge backward = new PEdge(w, v, 0.0, w+"to"+v);
        edgeList.addLast(forward);
        edgeList.addLast(backward);
        v.incidentEdgeList.addLast(forward);
        w.incidentEdgeList.addLast(backward);
        edgeMap.put(forward, backward);
        edgeMap.put(backward, forward);
        return forward;
    }

    public PEdge getReverseEdge(PEdge edge) {
        return edgeMap.get(edge);
    }
}
