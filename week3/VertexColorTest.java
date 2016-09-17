package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * VertexColorTest -  client program to test reduction of  VertexColor to SAT.
 */

public class VertexColorTest {
    private final InputReader reader;
    private final OutputWriter writer;

    public VertexColorTest(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new VertexColorTest(reader, writer).run();
        writer.writer.flush();
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Graph g = new Graph(n, m);

        for (int i = 0; i < m; ++i) {
            g.addEdge(reader.nextInt()-1, reader.nextInt()-1);
        }

        ConvertVertexColorToSat sat = new ConvertVertexColorToSat(g);
        writer.printf("%d %d\n",sat.clauses.size(), sat.variableCount);
        for (int i=0; i < sat.clauses.size(); i++) {
            ArrayList<Integer> clause = sat.clauses.get(i);
            for (int j =0; j < clause.size(); j++) {
                writer.printf("%d ", clause.get(j));
            }
            writer.printf("%d\n",0);

        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}



