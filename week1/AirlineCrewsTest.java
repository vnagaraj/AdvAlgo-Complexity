package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * AirlineCrewsTest - Client program to test the bipartite matching problem
 *
 *
 Problem Description
 Task. The airline o ers a bunch of  ights and has a set of crews that can work on those  flights.
 However, the  flights are starting in di erent cities and at different times, so only some of the crews are able to
 work on a particular  ight. You are given the pairs of  ights and associated crews that can work on those  flights.
 You need to assign crews to as many  ights as possible and output all the assignments.

 Input Format. The  rst line of the input contains integers 𝑛 and 𝑚 — the number of  flights and the number of crews respectively.
 Each of the next 𝑛 lines contains 𝑚 binary integers (0 or 1). If the 𝑗-th integer in the 𝑖-th line is 1,
 then the crew number 𝑗 can work on the  ight number 𝑖, and if it is 0, then it cannot.
 Constraints. 1 ≤ 𝑛, 𝑚 ≤ 100.

 Output Format. Output 𝑛 integers — for each  ight, output the 1-based index of the crew assigned to this  flight.
 If no crew is assigned to a  ight, output −1 as the index of the crew corresponding to it.
 All the positive indices in the output must be between 1 and 𝑚, and they must be pairwise different,
 but you can output any number of −1’s. If there are several assignments with the maximum possible number of
 flights having a crew assigned, output any of them.

 Sample 1.
 Input:
 3 4
 1 1 0 1
 0 1 0 0
 0 0 0 0

 Output:
 1 2 -1

 */

public class AirlineCrewsTest {
    private FastScanner in;

    public static void main(String[] args) throws IOException {
        new AirlineCrewsTest().solve();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        boolean[][] bipartiteGraph = readData();
        new BipartiteMatching(bipartiteGraph);
    }

    boolean[][] readData() throws IOException {
        int numLeft = in.nextInt();
        int numRight = in.nextInt();
        boolean[][] adjMatrix = new boolean[numLeft][numRight];
        for (int i = 0; i < numLeft; ++i)
            for (int j = 0; j < numRight; ++j)
                adjMatrix[i][j] = (in.nextInt() == 1);
        return adjMatrix;
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









