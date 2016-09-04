package week1;
/**
 * class to compute Bipartite matching
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 2nd, 2016
 *
 */

class BipartiteMatching{
    FlowGraph graph;
    private int firstVertexType = 0;
    private int source = -1;
    private int sink = -1;

    BipartiteMatching(boolean[][] adjMatrix){
        firstVertexType = adjMatrix.length;
        graph = buildFlowGraph(adjMatrix);
        source = graph.getSize()-2;
        sink = graph.getSize()-1;
        FordFulkerson.maxFlow(graph, source, sink);
        int[] flights = matchAssignments(graph);
        writeResponse(flights);

    }

    /**
     * Print output of ideal matched assignments if possible
     * @param flights
     */
    private void writeResponse(int[] flights){
        for (int i=0; i < flights.length; i++){
            int assignment = flights[i];
            if (assignment == 0){
                assignment = -1;
            }
            System.out.print(assignment + " ");
        }
    }

    /**
     * Match ideal assignments of features with crews
     * @param graph
     * @return array of features with assignments
     */
    private int[] matchAssignments(FlowGraph graph) {
        int[] flights = new int[firstVertexType];
        for (int i =firstVertexType; i < source; i++){
            for (Integer flowEdgeIds : this.graph.getIds(i)){
                FlowEdge edge = graph.flowEdges[flowEdgeIds];
                if (!edge.isFull() && edge.endVertex != sink){
                    //crew i has been assigned edge.endVertex
                    //i stands for
                    flights[edge.endVertex] = i - firstVertexType +1;
                    //System.out.print(edge.endVertex+1);
                    break;
                }
            }
        }
        return flights;
    }

    /**
     * Build FlowGraph to model into a bipartite problem
     * @param adjMatrix user defined matches of features with crews
     * @return updated flowGraph
     */
    private FlowGraph buildFlowGraph(boolean[][] adjMatrix){
        int firstVertexCount = adjMatrix.length;
        int secondVertexCount = adjMatrix[0].length;
        //accounting for source and sink
        int vertexCount = 2 + firstVertexCount + secondVertexCount;
        //accounting for edges from source to firstVertexType and edges from secondVertexType to sink
        int edgeCount = firstVertexCount + secondVertexCount;
        for (int i=0; i < adjMatrix.length; i++){
            for (int j=0; j < adjMatrix[0].length; j++){
                if (adjMatrix[i][j]){
                    //accounting for edges from firstVertexType to secondVertexType
                    edgeCount +=1;
                }
            }
        }
        FlowGraph graph = new FlowGraph(vertexCount, edgeCount);
        int source = vertexCount-2;
        int sink = vertexCount -1;
        //add edge from source to firstVertexType of capacity 1
        for (int i = 0; i < firstVertexCount; i++){
            graph.addEdge(source, i, 1);
        }
        //add edge from secondVertexType to sink of capacity 1
        for (int i =firstVertexCount; i< source; i++){
            graph.addEdge(i, sink, 1);
        }
        //add edge of max capacity from firstVertexType to secondVertexType
        for (int i=0; i < adjMatrix.length; i++){
            for (int j=0; j < adjMatrix[0].length; j++){
                if (adjMatrix[i][j]){
                    //accounting for edges from firstVertexType to secondVertexType
                    int secondVertex = j + firstVertexCount;
                    graph.addEdge(i, secondVertex, 1);
                }
            }
        }
        return graph;
    }
}