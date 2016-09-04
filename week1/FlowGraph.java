package week1;
import java.util.List;
import java.util.ArrayList;

/**
 * Class to compute the flow graph
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 1st, 2016
 */
class FlowGraph{
    //adj list representation in which in which the vertex index maps to a an index in the flowEdges array
    ArrayList<Integer>[] vertices = null;
    FlowEdge[] flowEdges;
    int counter = 0;

    FlowGraph(int vertexCount, int edgeCount){
        vertices = new ArrayList[vertexCount];
        flowEdges = new FlowEdge[edgeCount*2];
        for (int v = 0; v < vertexCount; v++) {
            vertices[v] = new ArrayList<Integer>();
        }
    }

    public int getSize(){
        return vertices.length;
    }

    public void addEdge(int from, int to, int capacity) {
        //forward edge with initial assignment of flow = capacity
        FlowEdge forwardEdge = new FlowEdge(from, to, capacity);
        vertices[from].add(counter);
        flowEdges[counter++] = forwardEdge;
        //backward edge with initial assignment of flow = 0
        FlowEdge backwardEdge = new FlowEdge(to, from, 0);
        vertices[to].add(counter);
        flowEdges[counter++] = backwardEdge;
    }

    public List<Integer> getIds(int from) {
        List<Integer> ids = new ArrayList<Integer>();
        for (Integer flowEdgesIndex: vertices[from]) {
            ids.add(flowEdgesIndex);
        }
        return ids;
    }

    public void addFlow(int flow, int flowEdgeIndex) {
        FlowEdge flowEdge = flowEdges[flowEdgeIndex];
        if (flowEdge.isFull()){
            throw new RuntimeException("Cannot remove flow on full edge");
        }
        int currentFlow = flowEdge.flow;
        if (flow > currentFlow){
            throw new RuntimeException("Cannot remove more flow than designated on edge");
        }
        currentFlow -= flow;
        flowEdge.flow = currentFlow;
        //backward edge
        FlowEdge backwardEdge = flowEdges[flowEdgeIndex+1];
        backwardEdge.flow += flow;
    }

}