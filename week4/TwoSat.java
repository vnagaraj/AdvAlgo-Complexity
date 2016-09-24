package week4;
/**
 * TwoSat Feasiblilty  class
 * to compute feasibility given a list of clauses and variables and make assignments if possible
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 23rd, 2016
 *
 */

class TwoSat {
    private int numVars;
    DirectedGraph graph;
    DirectedGraph reverseGraph;
    KosaRajuSCC scc;



    TwoSat(int n, Clause[] clauses) {
        numVars = n;
        buildImplicationGraph(clauses);
        scc = new KosaRajuSCC(graph, reverseGraph);
    }


    public void printAsssignments(){
        if (scc.isSat()) {
            System.out.println("SATISFIABLE");
            for (int i = 0; i < graph.V(); i += 2) {
                int val = scc.vertexAssignment(i);
                if (val == 1) {
                    System.out.print(i/2 + 1 + " ");
                }else{
                    System.out.print(-1 *(i/2 + 1) + " ");
                }
            }
        }
        else{
            System.out.println("UNSATISFIABLE");
        }
    }



    private void buildImplicationGraph(Clause[] clauses){
        graph = new DirectedGraph(numVars * 2);
        reverseGraph = new DirectedGraph(numVars * 2);
        for (Clause clause: clauses){
            createEdges(clause);
        }

    }

    private void createEdges(Clause clause){
        int firstVar = clause.firstVar;
        int secondVar = clause.secondVar;
        int fVertexNegfVar = getVertex(-1 * firstVar); // vertex created from negation of first var
        int sVertexsVar = getVertex(secondVar); // vertex created from second var
        int sVertexNegsVar = getVertex(-1 * secondVar); //vertex created from negation of second var
        int fVertexfVar = getVertex(firstVar); //vertex created from first var
        //create directed edge
        graph.addEdge(fVertexNegfVar, sVertexsVar);
        graph.addEdge(sVertexNegsVar, fVertexfVar);
        //directed edges in reverse direction
        reverseGraph.addEdge(sVertexsVar, fVertexNegfVar);
        reverseGraph.addEdge(fVertexfVar, sVertexNegsVar);
    }

    private int getVertex(int variable){
        int notNegation = 2;
        if (variable < 0){
            notNegation = 1;
        }
        int val = Math.abs(variable);
        return (val * 2) -notNegation;
    }


}