package week1;

/**
 * class to compute the min no of overlays required for stocks to be displayed.
 * Reduction into max matching bipartite
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 3rd, 2016
 *
 */
class StockCharts{
    private int edgeCount;
    public int minCut;
    int source;
    int sink;


    StockCharts(int[][] stockData){
        //n * n matrix
        boolean[][] truthTable =  buildTruthTable(stockData);
        FlowGraph flowGraph = buildFlowGraph(truthTable);
        FordFulkerson.maxFlow(flowGraph, source, sink);
        minCut = computeNonFullEdges(flowGraph);
    }

    private int computeNonFullEdges(FlowGraph flowGraph){
        int counter = 0;
        for (int index :flowGraph.vertices[source]){
            FlowEdge flowEdge = flowGraph.flowEdges[index];
            if (!flowEdge.isFull()){
               counter +=1;
            }
        }
        return counter;
    }

    private boolean[][] buildTruthTable(int[][] stockData){
        boolean[][] truthTable = new boolean[stockData.length][stockData.length];
        for (int frow =0; frow < stockData.length; frow ++){
            for (int srow = frow+1; srow < stockData.length; srow ++){
                compareRows(frow, srow, stockData, truthTable);
            }
        }
        return truthTable;
    }

    private FlowGraph buildFlowGraph(boolean[][] truthTable){
        //2 nodes per stock plus source and sink
        int vertexCount = truthTable.length * 2 + 2;
        int fStockNode = truthTable.length;
        source = vertexCount-2;
        sink = vertexCount -1;
        //account for edges from source to firststocknode1 and edges from secondstocknode2 to sink
        edgeCount +=  2* fStockNode;
        FlowGraph flowGraph = new FlowGraph(vertexCount, edgeCount);
        //adding edges from source to stocknode 1
        for (int s = 0; s < fStockNode; s++){
            flowGraph.addEdge(source, s, 1);
        }
        //adding edges from stocknode2 to sink
        for (int s = fStockNode; s < source; s++){
            flowGraph.addEdge(s, sink, 1);
        }
        //adding edges from stocknode1 to stocknode2
        for (int i=0; i < truthTable.length; i++){
            for (int j =0; j < truthTable[0].length; j++){
                if (truthTable[i][j]){
                    flowGraph.addEdge(i, j+ fStockNode, 1);
                }
            }
        }
        return flowGraph;
    }

    private void compareRows(int frow, int srow, int[][] stockData, boolean[][] truthTable){
        int stockColCount = stockData[0].length;
        int greater_counter =0;
        int less_counter = 0;
        for (int col = 0; col < stockData[0].length; col++){
            if (stockData[frow][col] < stockData[srow][col]){
                less_counter += 1;
            } else if((stockData[frow][col] > stockData[srow][col])) {
                greater_counter +=1;
            }
        }
        if (less_counter == stockColCount){
            truthTable[frow][srow] = true;
            edgeCount +=1;
        } else if (greater_counter == stockColCount) {
            truthTable[srow][frow] = true;
            edgeCount +=1;
        }
    }
}