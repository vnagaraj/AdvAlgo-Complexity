package week1;
import java.util.ArrayList;
/**
 * class to compute max flow for the given flow graph, BFS is used to find augmented path
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 2.0 September 1st, 2016
 *
 */
class FordFulkerson{

    public  static int maxFlow(FlowGraph graph, int source, int sink) {
        int flow = 0;
        BFS bfs = new BFS(source, graph);
        bfs.run();
        while (bfs.containsPath(sink)){
            ArrayList<Integer> path = bfs.pathTo(sink);
            int bottleNeckCap = getMinCap(graph, path);
            //System.out.println("BottleNeck " + bottleNeckCap);
            flow += bottleNeckCap;
            updateFlowEdges(graph, bottleNeckCap, path);
            bfs = new BFS(source, graph);
            bfs.run();
        }
        return flow;
    }

    private static void updateFlowEdges(FlowGraph flowGraph, int flow, ArrayList<Integer> flowEdges){
        for (Integer flowEdgeIndex: flowEdges){
            flowGraph.addFlow(flow, flowEdgeIndex);
        }
    }

    private static int getMinCap(FlowGraph graph, ArrayList<Integer> flowEdges){
        int min = Integer.MAX_VALUE;
        for (Integer flowEdgeIndex: flowEdges){
            FlowEdge flowEdge = graph.flowEdges[flowEdgeIndex];
            if (min > flowEdge.flow){
                min = flowEdge.flow;
            }
        }
        return min;
    }
}