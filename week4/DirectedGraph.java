package week4;

/**
 * DirectedGraph
 *
 * @version 1.0 September 23rd, 2016
 *
 */

class DirectedGraph {
    private Bag<Integer>[] adj;


    DirectedGraph(int vertexCount){
        adj = (Bag<Integer>[]) new Bag[vertexCount];
        for (int v = 0; v < vertexCount; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    void addEdge(int start, int end){
        adj[start].add(end);
    }

    int V(){
        return adj.length;
    }

    /**
     * Returns the adj adjacent from vertex {@code v} in this digraph.
     *
     * @param  v the vertex
     * @return the adj adjacent from vertex {@code v} in this digraph, as an iterable
     * @throws IndexOutOfBoundsException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}