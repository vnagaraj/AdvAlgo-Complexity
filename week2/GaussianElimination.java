package week2;

/**
 * class to compute linear equations via Gaussian Elimination
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 3.0 September 9th, 2016
 *
 */

public class GaussianElimination {
    private double[][] A; //refers to co-efficient matrix
    double[] b; //refers to output matrix
    boolean hasSolution = true;


    GaussianElimination(double[][] A, double[] b){
        if (A == null || b == null){
            hasSolution = false;
            return;
        }
        if (A.length == 0 || b.length == 0){
            hasSolution = false;
            return;
        }
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
                return;
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
