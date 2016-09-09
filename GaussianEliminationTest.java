import java.io.IOException;
import java.util.Scanner;

/**
 * GaussianEliminationTest - client program to test the solution for linear equations
 *
 * Problem Description
 Task. You’re looking into a restaurant menu which shows for each dish the list of ingredients with amounts and the estimated total energy value in calories. You would like to  nd out the energy values of individual ingredients (then you will be able to estimate the total energy values of your favorite dishes).

 Input Format. The  rst line of the input contains an integer 𝑛 — the number of dishes in the menu, and it happens so that the number of di erent ingredients is the same. Each of the next 𝑛 lines contains description 𝑎1, 𝑎2, . . . , 𝑎𝑛, 𝐸 of a single menu item. 𝑎𝑖 is the amount of 𝑖-th ingredient in the dish, and 𝐸 is the estimated total energy value of the dish. If the ingredient is not used in the dish, the amount will be speci ed as 𝑎𝑖 = 0; beware that although the amount of any ingredient in any real menu would be positive, we will test that your algorithm works even for negative amounts 𝑎𝑖 < 0.
 Constraints. 0 ≤ 𝑛 ≤ 20; −1000 ≤ 𝑎𝑖 ≤ 1000.

 Output Format. Output 𝑛 real numbers — for each ingredient, what is its energy value. These numbers
 can be non-integer, so output them with at least 3 digits after the decimal point.

 Sample 1.
 2
 5 -5 -1
 -1 -2 -1
 0.200 0.400

 */


public class GaussianEliminationTest {
    static GaussianElimination ReadEquation() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        double a[][] = new double[size][size];
        double b[] = new double[size];
        for (int raw = 0; raw < size; ++raw) {
            for (int column = 0; column < size+1; ++column)
                if (column == size){
                    b[raw] = scanner.nextInt();
                } else {
                    a[raw][column] = scanner.nextInt();
                }
        }
        return new GaussianElimination(a, b);
    }


    public static void main(String[] args) throws IOException {
        ReadEquation().printResult();
    }
}

/**
 * class to compute linear equations via Gaussian Elimination
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 2.0 September 9th, 2016
 *
 */

class GaussianElimination {
    private double[][] A; //refers to co-efficient matrix
    private double[] b; //refers to output matrix
    boolean hasSolution = true;


    GaussianElimination(double[][] A, double[] b){
        copyMatrix(A);
        copyMatrix(b);
        rowReduce();
    }

    private void rowReduce(){
        int rowLength = A.length;
        for (int row =0; row < rowLength; row++){
            int rowPivot = getRowPivot(A, row);
            if (rowPivot == -1){
                hasSolution = false;
            }
            if (rowPivot != row){
                //swap rows
                swapRowsInA(row, rowPivot);
                swapIndexInb(row, rowPivot);
            }
            //pivot element is located in col <row> for current row < row>
            //rescale to make pivot as 1
            if (A[row][row] != 1){
                //rescale entire row
                rescalePivot(row);
            }
            //make col zero
            for (int r = 0; r < rowLength; r++){
                if (row == r){
                    continue;
                }
                makeColZero(row, r);

            }
        }
    }

    /**
     * Print result of b
     */
    public void printResult(){
        if (hasSolution) {
            for (int row = 0; row < b.length; row++) {
                System.out.print(b[row] + " ");
            }
        }
    }

    /**
     * Make col entries as zero for the pivot row of A and update b
     * @param current_row pi
     * @param row
     */
    private void makeColZero(int current_row, int row){
        double scale_factor = A[row][current_row];
        for (int col = 0; col < A[0].length; col++){
            A[row][col] = A[row][col] - scale_factor * A[current_row][col];
        }
        b[row] = b[row] - scale_factor * b[current_row];
    }

    private void rescalePivot(int row){
        double scale_factor = A[row][row];
        for (int col =0 ; col < A[0].length; col++){
            A[row][col] = A[row][col]/scale_factor;
        }
        b[row] = b[row] / scale_factor;
    }

    /**
     * Swap rows i and j of A
     */
    private void swapRowsInA(int i, int j){
        double[] temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * Swap values in ith and jth index of b
     */
    private void swapIndexInb(int i, int j){
        double temp = b[i];
        b[i] = b[j];
        b[j] = temp;
    }

    private int getRowPivot(double[][] matrix, int row){
        //select first non zero entry in left most column
        for (int r = row; r < matrix.length; r++){
            if (matrix[r][row] != 0){
                return r;
            }
        }
        return -1;
    }

    /**
     * Copy input A to have a local copy < avoid modifying client</>
     * @param matrix
     */
    private void copyMatrix(double[][] matrix){
        A = new double[matrix.length][matrix[0].length];
        for (int i=0; i < matrix.length; i++){
            for (int j=0; j< matrix[0].length; j++){
                this.A[i][j] = matrix[i][j];
            }
        }
    }

    /**
     * Copy input B to have a local copy < avoid modifying client</>
     * @param matrix
     */
    private void copyMatrix(double[] matrix){
        b = new double[matrix.length];
        for (int i=0; i< matrix.length; i++){
                b[i] = matrix[i];
        }

    }

}
