package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *
 * StockChartsTest - client program to test the StockCharts program
 * Problem Description
 Task. You’re in the middle of writing your newspaper’s end-of-year economics summary, and you’ve decided that you want to show a number of charts to demonstrate how di erent stocks have performed over the course of the last year. You’ve already decided that you want to show the price of 𝑛 di erent stocks, all at the same 𝑘 points of the year.
 A simple chart of one stock’s price would draw lines between the points (0, 𝑝𝑟𝑖𝑐𝑒0), (1, 𝑝𝑟𝑖𝑐𝑒1), . . . , (𝑘 − 1,𝑝𝑟𝑖𝑐𝑒𝑘−1), where 𝑝𝑟𝑖𝑐𝑒𝑖 is the price of the stock at the 𝑖-th point in time.
 In order to save space, you have invented the concept of an overlaid chart.
 An overlaid chart is the combination of one or more simple charts, and shows the prices of multiple stocks (simply drawing a line for each one). In order to avoid confusion between the stocks shown in a chart, the lines in an overlaid chart may not cross or touch.
 Given a list of 𝑛 stocks’ prices at each of 𝑘 time points, determine the minimum number of overlaid charts you need to show all of the stocks’ prices.

 Input Format. The  rst line of the input contains two integers 𝑛 and 𝑘 — the number of stocks and the number of points in the year which are common for all of them. Each of the next 𝑛 lines contains 𝑘 integers. The 𝑖-th of those 𝑛 lines contains the prices of the 𝑖-th stock at the corresponding 𝑘 points in the year.
 Constraints. 1 ≤ 𝑛 ≤ 100; 1 ≤ 𝑘 ≤ 25. All the stock prices are between 0 and 1 000 000.

 Output Format. Output a single integer — the minimum number of overlaid charts to visualize all the
 stock price data you have.

 Sample 1.
 Input:
 3 4
 1 2 3 4
 2 3 4 6
 6 5 4 3

 Output:
 2
 */

public class StockChartsTest {
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new StockChartsTest().solve();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        int[][] stockData = readData();
        System.out.println(new StockCharts(stockData).minCut);
    }

    int[][] readData() throws IOException {
        int numStocks = in.nextInt();
        int numPoints = in.nextInt();
        int[][] stockData = new int[numStocks][numPoints];
        for (int i = 0; i < numStocks; ++i)
            for (int j = 0; j < numPoints; ++j)
                stockData[i][j] = in.nextInt();
        return stockData;
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








