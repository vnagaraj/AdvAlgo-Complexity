package week1;

/**
 * FlowEdge class - indicates the amount of flow that can be assigned ( > 0)
 *
 *  @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 1st, 2016
 */
class FlowEdge {
    int startVertex;
    int endVertex;
    int flow;

    FlowEdge(int startVertex, int endVertex, int flow){
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.flow = flow;
    }

    boolean isFull(){
        return flow == 0;
    }
}