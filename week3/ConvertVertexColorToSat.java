package week3;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * ConvertHamilPathToSat- class to convert VertexColor to SAT to be solved by SAT solver
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 16th, 2016
 *
 */
class ConvertVertexColorToSat {

    int colorCount = 3;
    int vertexCount ;
    ArrayList<ArrayList<Integer>> clauses;
    int variableCount;
    Graph g;
    Integer[][] variables; // 2d array in which i->vertex, j->color

    ConvertVertexColorToSat(Graph g) {
        this.g = g;
        vertexCount = g.vertexCount;
        variableCount = g.vertexCount * colorCount;
        clauses = new ArrayList<>();
        //vertex coloring with each vertex having 3 colors
        variables = new Integer[g.vertexCount][colorCount];
        initVariables();
        computeSat();
    }

    /**
     * Storing the value in (vertex, color) to output
     */
    private void initVariables(){
        int counter = 1;
        for (int vCount = 0; vCount < vertexCount; vCount++){
            for (int cCount = 0; cCount < colorCount; cCount++){
                variables[vCount][cCount] = counter++;
            }
        }
    }

    /**
     * Routine to add clauses with the following constraint
     * (only one value from the values array can be true)
     *
     * @param values
     */
    private void addExactlyOne(Integer[] values){
        ArrayList<Integer> list = new ArrayList<>();
        clauses.add(new ArrayList<Integer>(Arrays.asList(values)));
        //negation of each combination of the variables (nchoose2)
        for (int i=0; i < values.length ; i++){
            for (int j=i+1; j < values.length; j++){
                ArrayList<Integer> clause = new ArrayList<Integer>();
                int firstNo = values[i];
                int secondNo = values[j];
                clause.add(firstNo*-1);
                clause.add(secondNo*-1);
                clauses.add(clause);
            }
        }
    }

    /**
     * Add clause for the following constraint
     * vertex should be of some color
     */
    private void addVertexColorConstraint(){
        for (int vertex=0; vertex < vertexCount; vertex++){
            addExactlyOne(variables[vertex]);
        }
    }

    /**
     * add clause for following constraint
     * no two edges belong to same color
     */
    private void addVertexEdgeConstraint(){
        for (int i=0; i < g.edgeCount; i++){
            Edge edge = g.edges[i];
            int vertex1 = edge.from;
            int vertex2 = edge.to;
            for (int color = 0; color < colorCount; color++){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(variables[vertex1][color] * -1);
                list.add(variables[vertex2][color] * -1);
                clauses.add(list);
            }
        }
    }



    private void computeSat(){
        //add clause for every vertex in graph belonging to some color
        addVertexColorConstraint();
        //add clause for every edge in graph to satisfy constraint no two edges belong to same color
        addVertexEdgeConstraint();
    }
}


