package preflowpush;

import preflowpush.PResidualGraph;
import preflowpush.PVertexData;
import preflowpush.Edge;
import preflowpush.Vertex;

import java.util.*;

public class PreflowPush {

    PResidualGraph residualGraph;
    Vertex s;
    Vertex t;
    PriorityQueue<Vertex> pq;
    Set<Vertex> vistedSet;

    public PreflowPush() {
    }

    public PreflowPush(PResidualGraph residualGraph) {
        this();
        this.residualGraph = residualGraph;
        // Descending 5, 4, 3, 2, 1...
        pq = new PriorityQueue<>((o1, o2) -> {
            double excess1 = ((PVertexData) o1.getData()).getExcess();
            double excess2 = ((PVertexData) o2.getData()).getExcess();
            if (excess1 < excess2) {
                return 1;
            } else if (excess1 == excess2) {
                return 0;
            } else {
                return -1;
            }
        });
        vistedSet = new HashSet<>();
        initialize();
    }

    private void initialize() {
        Iterator itr = residualGraph.vertices();
        while (itr.hasNext()) {
            Vertex vertex = (Vertex) itr.next();
            PVertexData vd = (PVertexData) vertex.getData();
            Object name = vertex.getName();

            if (name.equals("s")) {
                int vertexCount = residualGraph.numVertices();
                vd.setHeight(vertexCount);
                s = vertex;

                Iterator edgeItr = residualGraph.incidentEdges(vertex);
                while (edgeItr.hasNext()) {
                    Edge edge = (Edge) edgeItr.next();
                    Double capacity = (Double) edge.getData();
                    Vertex theOtherVertex = residualGraph.opposite(vertex, edge);
                    PVertexData theOtherVertexData = (PVertexData) theOtherVertex.getData();
                    // Saturate the capacity
                    theOtherVertexData.setExcess(capacity);
                    // Update edge and residual edge
                    edge.setData(0.0);
                    // Update backward edge capacity
                    Edge backwardEdge = residualGraph.getReverseEdge(edge);
                    backwardEdge.setData(capacity);
                    pq.offer(theOtherVertex);
                    vistedSet.add(theOtherVertex);
                }
            }
            if (name.equals("t")) {
                t = vertex;
            }
        }
    }

    public double findMaxFlow() {
        while (!pq.isEmpty()) {
            // Highest level
            Vertex curVetex = pq.poll();
            vistedSet.remove(curVetex);

            while (((PVertexData) curVetex.getData()).getExcess() > 0) {
                double curExcess = ((PVertexData) curVetex.getData()).getExcess();
                int curHeight = ((PVertexData) curVetex.getData()).getHeight();
                Iterator edgeItr = residualGraph.incidentEdges(curVetex);

                while (edgeItr.hasNext()) {
                    Edge edge = (Edge) edgeItr.next();
                    double edgeData = (double) edge.getData();
                    Vertex theOtherVertex = residualGraph.opposite(curVetex, edge);
                    int theOtherHeight = ((PVertexData) theOtherVertex.getData()).getHeight();

                    if (curHeight > theOtherHeight && edgeData > 0) {
                        push(curVetex, theOtherVertex, edge, curExcess);
                        curExcess = ((PVertexData) curVetex.getData()).getExcess();
                    }
                }
                if (((PVertexData) curVetex.getData()).getExcess() > 0) {
                    relabel(curVetex);
                }
            }
        }
        PVertexData vd = (PVertexData) t.getData();
        return vd.getExcess();
    }

    private void push(Vertex fromVertex, Vertex toVertex, Edge edge, double wantToPush) {
        double capacity = (double) edge.getData();

        PVertexData fromVertexData = (PVertexData) fromVertex.getData();
        double fromVertexExcess = fromVertexData.getExcess();
        PVertexData toVertexData = (PVertexData) toVertex.getData();
        double toVertexExcess = toVertexData.getExcess();
        double flow = capacity >= wantToPush ? wantToPush : capacity;

        fromVertexData.setExcess(fromVertexExcess - flow);
        toVertexData.setExcess(toVertexExcess + flow);
        edge.setData(capacity - flow);

        Edge reverseEdge = residualGraph.getReverseEdge(edge);
        double reverseEdgeData = (double) reverseEdge.getData();
        reverseEdge.setData(reverseEdgeData + flow);

        // Push to PriorityQueue after adding flow to its excess, if it is not source and sink
        String toVertexName = toVertex.getName().toString();
        if (!vistedSet.contains(toVertex) && !(toVertexName.equals("s") || toVertexName.equals("t"))) {
            pq.offer(toVertex);
            vistedSet.add(toVertex);
        }
    }

    private void relabel(Vertex vertex) {
        PVertexData vd = (PVertexData) vertex.getData();
        int height = vd.getHeight();
        vd.setHeight(height + 1);
    }
}
