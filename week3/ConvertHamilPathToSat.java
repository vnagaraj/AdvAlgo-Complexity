package week3;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ConvertHamilPathToSat- class to convert HamiltonianPath to SAT to be solved by SAT solver
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 16th, 2016
 *
 */

class ConvertHamilPathToSat {

    ArrayList<ArrayList<Integer>> clauses;
    private int vertexCount;
    private int positionCount;
    int variableCount;
    private Graph g;
    Integer[][] variables; // 2d array in which i->vertex, j->position in hamilPath
    ConvertHamilPathToSat(Graph g) {
        this.g = g;
        g.updateNonEdges();
        //hamilPath with v vertices has v paths
        variables = new Integer[g.vertexCount][g.vertexCount];
        vertexCount = variables.length;
        positionCount = variables[0].length;
        variableCount = vertexCount * positionCount;
        initVariables();
        clauses = new ArrayList<>();
        computeSat();
    }

    /**
     * Storing the value in (vertex, pos) to output
     */
    private void initVariables(){
        int counter = 1;
        for (int vCount = 0; vCount < vertexCount; vCount++){
            for (int pCount = 0; pCount < positionCount; pCount++){
                variables[vCount][pCount] = counter++;
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
     * one vertex in each position in hamilPath
     */
    private void addVertexVisitConstraint(){

        for (int vertex=0; vertex < vertexCount; vertex++){
            Integer[] pos = new Integer[variables.length];
            for (int position =0; position < positionCount; position++){
                pos[position] = variables[position][vertex];
            }
            addExactlyOne(pos);
        }
    }

    /**
     * Add clause for the following constraint
     * vertex should be visited only once in hamilPath
     */
    private void addVertexPositionConstraint(){
        for (int vertex=0; vertex < vertexCount; vertex++){
            addExactlyOne(variables[vertex]);
        }
    }

    /**
     * Add clause for the following constraint
     * vertices not sharing an edge should not be part of successive positions in hamilPath
     */
    private void addVertexNonEdgeConstraint(){
        for (int i=0; i < g.nonEdges.size(); i++){
            Edge edge = g.nonEdges.get(i);
            int vertex1 = edge.from;
            int vertex2 = edge.to;
            for (int pCount =0; pCount < positionCount-1; pCount++){
                ArrayList<Integer> values = new ArrayList<>();
                values.add((variables[vertex1][pCount] * -1));
                values.add((variables[vertex2][pCount+1] * -1));
                clauses.add(values);
                values = new ArrayList<>();
                values.add((variables[vertex1][pCount+1] * -1));
                values.add((variables[vertex2][pCount] * -1));
                clauses.add(values);
            }

        }
    }


    private void computeSat(){
        //add clause for one vertex in each position in path
        addVertexVisitConstraint();
        //add clause for each vertex must be visited only once/ all vertices must be on path
        addVertexPositionConstraint();
        //add clause for every edge in graph to satisfy constraint of vertices not belonging to edge not part of successive positions
        addVertexNonEdgeConstraint();
    }
}
