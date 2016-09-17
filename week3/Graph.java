package week3;
import java.util.ArrayList;

/**
 * Graph class
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 16th, 2016
 *
 */
class Graph {
    int vertexCount;
    int edgeCount;
    Edge[] edges;
    private int edgeCounter = 0;
    boolean[][] isEdge;
    ArrayList<Edge> nonEdges;


    Graph(int vertexCount, int edgeCount){
        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        edges = new Edge[edgeCount];
        //computing nonEdges - used as a input for constraint in hamilPath SAT
        isEdge = new boolean[vertexCount][vertexCount];
        nonEdges = new ArrayList<>();
    }

    void addEdge(int from, int to){
        Edge edge = new Edge(from, to);
        edges[edgeCounter++] = edge;
        isEdge[from][to] = true;
        isEdge[to][from] = true;
    }

    /**
     * computing nonEdges - used as a input for constraint in hamilPath SAT
     */
    void updateNonEdges(){
        for (int i = 0; i < isEdge.length; i++){
            for (int j = i+1; j < isEdge[0].length; j++){
                if (!isEdge[i][j]){
                    nonEdges.add(new Edge(i,j));
                }
            }
        }
    }

}