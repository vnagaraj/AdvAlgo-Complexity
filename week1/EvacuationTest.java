package week1;

import java.io.*;
import java.util.StringTokenizer;

/**
 * EvacuationTest - client program to test the evacuation problem solved using ford fulkerson network flow
 *
 * Problem Description
 Task. A tornado is approaching the city, and we need to evacuate the people quickly. There are several roads outgoing from the city to the nearest cities and other roads going further. The goal is to evacuate everybody from the city to the capital, as it is the only other city which is able to accomodate that many newcomers. We need to evacuate everybody as fast as possible, and your task is to  nd out what is the maximum number of people that can be evacuated each hour given the capacities of all the roads.

 Input Format. The  rst line of the input contains integers 𝑛 and 𝑚 — the number of cities and the number of roads respectively. Each of the next 𝑚 lines contains three integers 𝑢, 𝑣 and 𝑐 describing a particular road — start of the road, end of the road and the number of people that can be transported through this road in one hour. 𝑢 and 𝑣 are the 1-based indices of the corresponding cities.
 The city from which people are evacuating is the city number 1, and the capital city is the city number 𝑛.
 Note that all the roads are given as one-directional, that is, you cannot transport people from 𝑣 to 𝑢 using a road that connects 𝑢 to 𝑣. Also note that there can be several roads connecting the same city 𝑢 to the same city 𝑣, there can be both roads from 𝑢 to 𝑣 and from 𝑣 to 𝑢, or there can be only roads in one direction, or there can be no roads between a pair of cities. Also note that there can be roads going from a city 𝑢 to itself in the input.
 When evacuating people, they cannot stop in the middle of the road or in any city other than the capital. The number of people per hour entering any city other than the evacuating city 1 and the capital city 𝑛 must be equal to the number of people per hour exiting from this city. People who left a city 𝑢 through some road (𝑢,𝑣,𝑐) are assumed to come immediately after that to the city 𝑣. We are interested in the maximum possible number of people per hour leaving the city 1 under the above restrictions.
 Constraints.1≤𝑛≤100;0≤𝑚≤10000;1≤𝑢,𝑣≤𝑛;1≤𝑐≤10000. Itisguaranteedthat 𝑚 · EvacuatePerHour ≤ 2 · 108, where EvacuatePerHour is the maximum number of people that can be evacuated from the city each hour — the number which you need to output.

 Output Format. Output a single integer — the maximum number of people that can be evacuated from the city number 1 each hour.

 Sample 1.
 Input:
 5 7
 1 2 2
 2 5 5
 1 3 6
 3 4 2
 4 5 1
 3 2 3
 2 4 1
 Output:
 6
 */

public class EvacuationTest {
    private static FastScanner in;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();

        FlowGraph graph = readGraph();
        System.out.println(FordFulkerson.maxFlow(graph, 0, graph.getSize()-1));
    }


    static FlowGraph readGraph() throws IOException {
        int vertex_count = in.nextInt();
        int edge_count = in.nextInt();
        FlowGraph graph = new FlowGraph(vertex_count, edge_count);

        for (int i = 0; i < edge_count; ++i) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1, capacity = in.nextInt();
            graph.addEdge(from, to, capacity);
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












