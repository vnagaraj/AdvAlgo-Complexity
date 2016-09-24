package week4;

import java.util.Iterator;

/**
 * KosaRajuSCC used to compute SCC using 2 pass DFS
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 23rd, 2016
 *
 */
class KosaRajuSCC {

    private DirectedGraph graph;
    private DirectedGraph reverseGraph;
    private boolean[] visited;
    private int[] fstimes;     // required for 1st run of DFS on reverse graph to compute finishing time
    private int fsTime =0;     //global finishing time required for first run
    private  int scc = 0;     // required for 2nd run of DFS on orig graph
    private int[] vertexcc;      // index - vertex, value - component id
    private int[] vertexAssignments; //assignments of vertex
    private boolean sat = true;


    KosaRajuSCC(DirectedGraph graph, DirectedGraph reverseGraph){
        this.graph = graph;
        this.reverseGraph = reverseGraph;
        run();
    }

    public boolean isSat(){
        return sat;
    }

    private void run(){
        visited = new boolean[graph.V()];
        fstimes = new int[graph.V()];
        //run DFS on reverseGraph
        Stack<Integer> stack = new Stack<Integer>();
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[reverseGraph.V()];
        for (int v = 0; v < reverseGraph.V(); v++)
            adj[v] = reverseGraph.adj(v).iterator();
        for (int vertex=reverseGraph.V()-1; vertex >=0 ; vertex--){
            if (!visited[vertex]) {
                    iterative_dfs_first(vertex, adj, stack);

            }
        }
        // run DFS on original graph in order of decreasing finishing time
        visited = new boolean[graph.V()];
        vertexAssignments = new int[graph.V()];
        for (int i = 0; i < vertexAssignments.length; i++){
            vertexAssignments[i] = -1;
        }
        vertexcc = new int[graph.V()];
        for (int i = 0; i < vertexcc.length; i++){
            vertexcc[i] = -1;
        }
        for (int fstime = fstimes.length-1; fstime >=0 ; fstime--){
            int vertex = fstimes[fstime];
            if (!visited[vertex]) {
                    iterative_dfs_second(vertex, graph, stack);
                    if (!sat){
                        break;
                    }
                    scc++;
            }
        }

    }



    private void iterative_dfs_first(int vertex, Iterator<Integer>[] adj, Stack<Integer> stack){
        visited[vertex] = true;
        stack.push(vertex);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!visited[w]) {
                    visited[w] = true;
                    stack.push(w);
                }
            }
            else {
                fstimes[fsTime++] = stack.pop();;
            }
        }
    }

    private void iterative_dfs_second(int vertex, DirectedGraph graph, Stack<Integer> stack){
        stack.push(vertex);
        visited[vertex] = true;
        while (!stack.isEmpty()){
            vertex = stack.pop();
            vertexcc[vertex] = scc;
            //System.out.println(vertex);
            if (!checkAndMakeAssignment(vertex)) {
                sat = false;
                break;
            }
            Iterator<Integer> adjList = graph.adj(vertex).iterator();
            while (adjList.hasNext()){
                int neighbor = adjList.next();
                if (!visited[neighbor]){
                    visited[neighbor] = true;
                    stack.push(neighbor);
                    //System.out.println(vertex);
                }
            }
        }
    }

    public boolean strongComponent(int v, int w){
        return vertexcc[v] == vertexcc[w];
    }

    public int vertexAssignment(int v){
        return vertexAssignments[v];
    }

    private boolean checkAndMakeAssignment(int literal){
        int negation = -1;
        if (literal%2 == 0){
            negation = literal +1;
        }else{
            negation = literal -1;
        }
        if (strongComponent(literal, negation)){
                //vertex and negation belong to same component
                return false;
         }
        else if (vertexAssignment(negation) == -1){
                vertexAssignments[literal] = 1;
                vertexAssignments[negation] = 0; //assign negative value
                return true;
        }
        return true;
    }

}
