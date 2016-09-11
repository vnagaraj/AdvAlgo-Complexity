package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * LinearEquationsTest - client program to test the linear inequalities with max objective
 *
 * Problem Description
 Task. You want to optimize your diet: that is, make sure that your diet satisfies all the recommendations of nutrition experts, but you also get maximum pleasure from your food and drinks. For each dish and drink you know all the nutrition facts, cost of one item, and an estimation of how much you like it. Your budget is limited, of course. The recommendations are of the form “total amount of calories consumed each day should be at least 1000” or “the amount of water you drink in liters should be at least twice the amount of food you eat in kilograms”, and so on. You optimize the total pleasure which is the sum of pleasure you get from consuming each particular dish or drink, and that is proportional to the amount amounti of that dish or drink consumed.
 The budget restriction and the nutrition recommendations can be converted into a system of linear
 inequalities like Pm costi · amounti  Budget, amounti   1000 and amounti   2 · amountj   0, where i=1
 amounti is the amount of i-th dish or drink consumed, costi is the cost of one item of i-th dish or
 drink, and Budget is your total budget for the diet. Of course, you can only eat a non-negative amount
 amounti oPf i-th item, so amounti   0. The goal to maximize total pleasure is reduced to the linear m
 objective amounti · pleasurei ! max where pleasurei is the pleasure you get after consuming one i=1
 unit of i-th dish or drink (some dishes like fish oil you don’t like at all, so pleasurei can be negative). Combined, all this is a linear programming problem which you need to solve now.

 Input Format. The first line of the input contains integers n and m   the number of restrictions on your diet and the number of all available dishes and drinks respectively. The next n + 1 lines contain the coefficients of the linear inequalities in the standard form Ax  b, where x = amount is the vector of length m with amounts of each ingredient, A is the n ⇥ m matrix with coefficients of inequalities and b is the vector with the right-hand side of each inequality. Specifically, i-th of the next n lines contains m integers Ai1, Ai2, . . . , Aim, and the next line after those n contains n integers b1, b2, . . . , bn. These lines describe n inequalities of the form Ai1 · amount1 + Ai2 · amount2 + · · · + Aim · amountm  bi. The last line of the input contains m integers   the pleasure for consuming one item of each dish and drink pleasure1, pleasure2, . . . , pleasurem.
 Constraints. 1n,m8; 100Aij 100; 1000000bi 1000000; 100costi 100.

 Output Format. If there is no diet that satisfies all the restrictions, output “No solution” (without quotes). If you can get as much pleasure as you want despite all the restrictions, output “Infinity” (without quotes). If the maximum possible total pleasure is bounded, output two lines. On the first line, output “Bounded solution” (without quotes). On the second line, output m real numbers   the optimal amounts for each dish and drink. Output all the numbers with at least 15 digits after the decimal point.

 Sample 1.
 Input:
 3 2
 -1 -1
 1 0
 0 1
 -1 2 2
 -1 2
 Output:
 Bounded Solution
 0.000000000000000 2.0000000000000000
 Explanation:
 Here we have only two items, and we know that ( 1) · amount1 + ( 1) · amount2   1 ) amount1 + amount2
 1 from the first inequality, and also that amount1  2 and amount2  2 from the second and the third inequalities.
 We also know that all amounts are non-negative. We want to maximize ( 1) · amount1 + 2 · amount2 under those restrictions
 that is, we don’t like dish or drink number 1, and we twice as much like dish or drink number 2.
 It is optimal then to consume as few as possible of the first item and as much as possible of the second item.
 It turns out that we can avoid consuming the first item at all and take the maximum possible amount 2 of the second item,
 and all the restrictions will be satisfied! Clearly, this is a diet with the maximum possible total pleasure!
 Note that integers 0 and 2 in the output are printed with 15 digits after the decimal point.
 Don’t forget to print at least 15 digits after the decimal point, as the answers to some tests will be non- integer, and
 you don’t want to get your answer rejected only because of some rounding problems.

 32 -1 -1 10 01 -122 -12

 */

public class LinearEquationsTest {

    BufferedReader br;
    StringTokenizer st;
    boolean eof;

    /**
     * Get co-efficient matrix from user input
     * @param n - no of equations
     * @param m - no of variables
     * @return
     * @throws IOException
     */
    double[][] getAMatrix(int n, int m) throws IOException {
        double[][] A = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = nextInt();
            }
        }
        return A;
    }

    /**
     * Get output matrix from user input
     * @param n - no of equations
     * @return
     * @throws IOException
     */
    double[] geBCMatrix(int n) throws IOException {
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = nextInt();
        }
        return b;
    }

    /**
     * Get max objective function matrix from user input
     * @param m - no of variables
     * @return
     * @throws IOException
     */
    double[] getCMatrix(int m) throws IOException {
        double[] c = new double[m];
        for (int i = 0; i < m; i++) {
            c[i] = nextInt();
        }
        return c;
    }

    LinearEquationsTest() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        //no of inequalities
        int n = nextInt();
        //no of variables
        int  m = nextInt();
        //get co-efficient matrix
        double[][] A = getAMatrix(n, m);
        //get output matrix
        double[] b = geBCMatrix(n);
        //get objective function matrix
        double[] c = getCMatrix(m);
        new LinearEquationsSolver(A, b, c).print();
    }

    public static void main(String[] args) throws IOException {
        new LinearEquationsTest();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }
}



