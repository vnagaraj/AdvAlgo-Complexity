package week2;

/**
 * class to compute linear equations via Gaussian Elimination
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 5th, 2016
 *
 */

class RowReduce {
    double[][] matrix ;

    RowReduce(double[][] matrix){
        this.matrix = matrix;
        int rowLength = matrix.length;
        for (int row =0; row < rowLength; row++){
            int rowPivot = getRowPivot(matrix, row);
            if (rowPivot != row){
                //swap rows
                swapRows(matrix, row, rowPivot);
            }
            //pivot element is located in col <row> for current row < row>
            //rescale to make pivot as 1
            if (matrix[row][row] != 1){
                //rescale entire row
                rescalePivotRow(matrix, row);
            }
            //make col zero
            for (int r = 0; r < rowLength; r++){
                if (row == r){
                    continue;
                }
                makeColZero(matrix, row, r);
            }
        }
        printResult(matrix);
    }

    /**
     * Print result  of the matrix from lastCol
     * @param matrix
     */
    private void printResult(double[][] matrix){
        int lastCol = matrix[0].length -1;
        for (int row =0; row < matrix.length; row++){
            System.out.print(matrix[row][lastCol] + " ");
        }
    }

    /**
     * Make col entries as zero for the pivot row
     * @param matrix
     * @param current_row pi
     * @param row
     */
    private void makeColZero(double[][] matrix, int current_row, int row){
        double scale_factor = matrix[row][current_row];
        for (int col = 0; col < matrix[0].length; col++){
            matrix[row][col] = matrix[row][col] - scale_factor * matrix[current_row][col];
        }
    }

    private void rescalePivotRow(double[][] matrix, int row){
        double scale_factor = matrix[row][row];
        for (int col =0 ; col < matrix[0].length; col++){
           matrix[row][col] = matrix[row][col]/scale_factor;
        }
    }

    /**
     * Swap rows i and j of matrix
     * @param matrix
     * @param i
     * @param j
     */
    private void swapRows(double[][] matrix, int i, int j){
        double[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
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
}