package week4;
/**
 * TSTTest - client program to test the TSP class
 *
 * This is an instance of a classical NP-complete problem called Traveling Salesman Problem. Given
 a graph with weighted edges, you need to find the shortest cycle visiting each vertex exactly once.
 Vertices correspond to homes, the school and the depot. Edges weights correspond to the time to get
 from one vertex to another one. Some vertices may not be connected by an edge in the general case.
 Input Format. The first line contains two integers n and m ￿ the number of vertices and the number of
 edges in the graph. The vertices are numbered from 1 through n. Each of the next m lines contains
 three integers u, v and t representing an edge of the graph. This edge connects vertices u and v, and
 it takes time t to get from u to v. The edges are bidirectional: you can go both from u to v and from
 v to u in time t using this edge. No edge connects a vertex to itself. No two vertices are connected by
 more than one edge.
 Constraints. 2 <=n <=17; 1<=m<= n*(n- 1)/2
 2 ; 1 <=u, v <=n; u 6= v; 1<= t<=1 000 000.
 Output Format. If it is possible to start in some vertex, visit each other vertex exactly once in some order
 going by edges of the graph and return to the starting vertex, output two lines. On the first line,
 output the minimum possible time to go through such circular route visiting all vertices exactly once
 (apart from the first vertex which is visited twice ￿ in the beginning and in the end). On the second
 line, output the order in which you should visit the vertices to get the minimum possible time on
 the route. That is, output the numbers from 1 through n in the order corresponding to visiting the
 vertices. Don’t output the starting vertex second time. However, account for the time to get from the
 last vertex back to the starting vertex. If there are several solutions, output any one of them. If there
 is no such circular route, output just −1 on a single line. Note that for n = 2 it is considered a correct
 circular route to go from one vertex to another by an edge and then return back by the same edge.

 Sample 1
 Input
 4 6
 1 2 20
 1 3 42
 1 4 35
 2 3 30
 2 4 34
 3 4 12
 Output:
 97
 1 4 3 2

 Sample 2
 Input:
 4 4
 1 2 1
 2 3 4
 3 4 5
 4 2 1
 Output:
 -1

 */

import java.io.*;
import java.util.*;

public class TSPTest {
    private static FastScanner in;
    public static int INF = 1000 * 1000 * 1000;

    public static void main(String[] args) {
        in = new FastScanner();
        try {
            TSP tsp = new TSP(readData());
            tsp.printPath();
        } catch (IOException exception) {
            System.err.print("Error during reading: " + exception.toString());
        }
    }

    private static int[][] readData() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                graph[i][j] = INF;

        for (int i = 0; i < m; ++i) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int weight = in.nextInt();
            graph[u][v] = graph[v][u] = weight;
        }
        return graph;
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

}





