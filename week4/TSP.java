package week4;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * TSP used to compute the path that visits every vertex exactly once using dyanmic programming
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 28th, 2016
 *
 */
class TSP{
    //2D array indexed by  subset id subsetCount from (1-(n-1)) and destination vertex j from (0-(n-1))
    Integer[][] A;
    //map with key->subsetSize, value-> list of subsets
    HashMap<Integer, ArrayList<Integer>> sSetSizeMap;
    //map with key->subset, value-> list of vertices
    HashMap<Integer, ArrayList<Integer>> sSetIdMap;
    int subsetCount; //no of subsets
    int maxSubsetSize;
    int minDist = -1;
    int[][] graph;
    boolean isPath = true;

    TSP(int[][] graph){
        this.graph = graph;
        if (!dfs(graph)){
            isPath = false;
            return;
        }
        if (!isPath(graph)){
            isPath = false;
            return;
        }
        maxSubsetSize = graph.length;
        subsetCount = (int) Math.pow(2, maxSubsetSize); //total subsets
        A = new Integer[subsetCount][maxSubsetSize];
        sSetSizeMap = new HashMap<Integer, ArrayList<Integer>>();
        sSetIdMap = new HashMap<Integer, ArrayList<Integer>>();
        setupMap();
        run(graph);
        int s = 0;
    }

    boolean isPath(int[][] graph){
        if (graph.length == 2){
            return true;
        }
        for (int v = 0; v < graph.length; v++){
            if (getDegree(v) <=1){
                return false;
            }
        }
        return true;
    }

    int getDegree(int v){
        int count = 0;
        int[] edges = graph[v];
        for (int edge: edges){
            if (edge != TSPTest.INF){
                count++;
            }
        }
        return count;
    }

    void run(int[][] graph){
        //base case
        //subset comprising of only  susbset 0 with destination vertex '0' is 0
        A[1][0] = 0;
        //every other subset to destination vertex '0' is infinity
        for (int k = 2; k < subsetCount; k ++){
            A[k][0] = Integer.MAX_VALUE;
        }
        for (int m= 2; m < maxSubsetSize+1; m++){
            //each of these sSets contains 1
            ArrayList<Integer> sSets = sSetSizeMap.get(m);
            for (Integer sSet: sSets){
                for (Integer vertex1: sSetIdMap.get(sSet)){
                    if (vertex1 == 0){
                        continue;
                    }
                    for (Integer vertex2: sSetIdMap.get(sSet)){
                        if (vertex1 .equals(vertex2)) {
                            continue;
                        }
                        if (graph[vertex2][vertex1] == TSPTest.INF){
                            continue;
                        }
                        if ( A[sSet ^ (1 << vertex1)][vertex2] == null){
                            continue;
                        }
                        if ( A[sSet ^ (1 << vertex1)][vertex2] == Integer.MAX_VALUE ){
                            continue;
                        }
                        if (A[sSet][vertex1] == null){
                                A[sSet][vertex1] =  A[sSet ^ (1 << vertex1)][vertex2] + graph[vertex2][vertex1];
                        }
                        A[sSet][vertex1] = Math.min(A[sSet][vertex1], A[sSet ^ (1 << vertex1)][vertex2] + graph[vertex2][vertex1]);
                    }

                }

            }
        }
        //out min value
        /**
        int min = Integer.MAX_VALUE;
        for (int i =1; i < maxSubsetSize; i++){
            if (min > A[subsetCount -1][i] + graph[i][0]){
                min = A[subsetCount -1][i] + graph[i][0];
                minFinalVertex = i;
            }
        }
        return min;
         **/

    }

    int prevVertex(int subsetId, int vertex){
        //out min value
        int prevVertex = -1;
        int min = Integer.MAX_VALUE;
        for (int v: sSetIdMap.get(subsetId)){
            if (v == 0){
                continue;
            }
            if (graph[v][vertex] == TSPTest.INF){
                continue;
            }
            if (A[subsetId][v] == null){
                continue;
            }
            if (min > A[subsetId][v] + graph[v][vertex]){
                min = A[subsetId ][v] + graph[v][vertex];
                prevVertex= v;
            }
        }
        if (minDist == -1){
            minDist = min;
        }
        return prevVertex;
    }

    void printPath(){
        if (!isPath){
            System.out.println(minDist);
            return;
        }
        int[] result = constructPath();
        System.out.println(minDist);
        if (isPath) {
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
        }
    }


    int[] constructPath(){
        int[] result = new int[maxSubsetSize];
        int subsetid = subsetCount-1;
        int finalVertex = 0;
        result[0] =1;
        for (int i = result.length-1; i >=1; i--){
            int val = prevVertex(subsetid, finalVertex);
            if (val == -1){
                isPath = false;
                minDist = -1;
                break;
            }
            result[i] = val+1;
            finalVertex = val;
            subsetid = subsetid ^ (1 << finalVertex);
        }
        return result;
    }

    void setupMap(){
        //start from index 1 since 0 corresponds to empty subset
        for (int i = 1; i < subsetCount; i++){
            String binaryString = Integer.toBinaryString(i);
            //ignore subsets not including vertex 0
            if (binaryString.charAt(binaryString.length()-1)!= '1'){
                continue;
            }
            //number of 1's in binary string
            int size = getSize(binaryString, i);
            ArrayList<Integer> sSets = null;
            if (!sSetSizeMap.containsKey(size)){
               sSets = new ArrayList<Integer>();
               sSets.add(i);
               sSetSizeMap.put(size, sSets);
            } else{
                sSets = sSetSizeMap.get(size);
                sSets.add(i);
            }
        }
    }

    int getSize(String binaryString, int index){
        ArrayList<Integer> vertices = new ArrayList<>();
        int count = 0;
        int vertex =0;
        for (int i=binaryString.length()-1; i >=0 ; i--){
            if (binaryString.charAt(i) == '1'){
                count +=1;
                vertices.add(vertex);
            }
            vertex++;
        }
        sSetIdMap.put(index, vertices);
        return count;
    }

    /**
     * DFS routine to check if graph is connected
     * @param graph
     * @return
     */
    boolean dfs(int[][] graph){
        boolean[] marked = new boolean[graph.length];
        explore(0, marked);
        for (int i =0; i < marked.length; i++){
            if (!marked[i]){
                return false;
            }
        }
        return true;
    }

    void explore(int vertex, boolean[] marked){
        marked[vertex] = true;
        for (int neighbor=0; neighbor < graph[vertex].length; neighbor++){
            if (graph[neighbor][vertex] != TSPTest.INF){
                if (!marked[neighbor]) {
                    explore(neighbor, marked);
                }
            }
        }
    }
}