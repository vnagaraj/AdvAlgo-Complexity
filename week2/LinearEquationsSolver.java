package week2;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

/**
 * class to compute linear inequalities with max objective via Brute force approach
 *
 * @author Vivekanand Ganapathy Nagarajan
 * @version 1.0 September 9th, 2016
 *
 */

public class LinearEquationsSolver {
    double[][] A; //co-efficient matrix
    double[] b; //output matrix for co-efficicnet
    double[] c; //objective function matrix

    int n;//no of input equations
    int m; // no of variables
    double maxValue = Double.NEGATIVE_INFINITY;
    double[] result = null;
    static double INFINITY = Math.pow(10,9);
    private boolean bounded = true;

    LinearEquationsSolver(double[][] A, double[] b, double[] c){
        n = A.length;
        m = c.length;
        this.A = A;
        this.b = b;
        this.c = c;
        //total no of inequalities = n +m + 1(for infinity)
        int total = n + m +1;
        compute(total);
    }

    public void print(){
        if (maxValue == Double.NEGATIVE_INFINITY){
            System.out.println("No solution");
            return;
        }
        if (!bounded){
            System.out.println("Infinity");
            return;
        }
        System.out.println("Bounded solution");
        for (int i=0; i< result.length; i++){
            System.out.print(String.format("%.15f", result[i]) + " ");
        }
    }

    private void compute(int total){
        int[] arr = new int[total];
        for (int i=0; i < arr.length; i++){
            arr[i] = i;
        }
        genProcessCombinations(arr,total, m);
    }

    /**
     * Process subset of size
     * @param subset
     */
    private void processSubset(Set<Integer> subset){
        double[][] A = new double[m][m];
        double[] b = new double[m];
        updateMatrices(subset, A, b);
        GaussianElimination gElim = new GaussianElimination(A, b);
        if (!gElim.hasSolution){
            return;
        }
        double[] temp_result = gElim.b;
        if (!satisfiesAllInEq(temp_result)){
            return;
        }
        double val = computeVal(temp_result);
        if (val > maxValue){
            maxValue = val;
            result = temp_result;
            if (subset.contains(n+m)){
                bounded = false;
            } else{
                bounded = true;
            }
        }
    }

    /**
     * Verify result satisfies all the equations
     * @param result
     * @return
     */
    private boolean satisfiesAllInEq(double[] result){
        boolean satisfied = true;
        //check to see if eq satisfies regular equations
        for (int i=0; i < n; i++){
            double inEqSum = 0;
            for(int j= 0; j < m; j++){
                    inEqSum += result[j] * this.A[i][j];
            }
            if (inEqSum > b[i] + Math.pow(10, -3)){
                //not satisfied
                satisfied = false;
                break;
            }
        }
        //check to see if it satisfies constraints
        for (int i=0 ; i < m; i++){
            if (result[i] * -1 > Math.pow(10, -3)){
                //not satisfied
                satisfied = false;
                break;
            }

        }
        return  satisfied;

    }

    private double computeVal(double[] result){
        double val = 0;
        for (int i=0; i < result.length; i++){
            val += result[i] * c[i];
        }
        return val;
    }

    /**
     * Update matrices
     */
    private void updateMatrices(Set<Integer> set, double[][] A, double[] b){
        int index = 0;
        for (Integer val: set) {
            if (val < n) {
                A[index] = this.A[val];
                b[index] = this.b[val];
            }
            else if (val < n+m){
                int diff = val - n;
                A[index] = new double[m];
                A[index][diff] = -1;
                b[index] = 0;
            }else{
                A[index] = new double[m];
                Arrays.fill(A[index], 1);
                b[index] = INFINITY;
            }
            index += 1;
        }
    }


    /**
      arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed
     **/
    void combinationUtil(int arr[], int n, int r, int index,
                                int data[], int i)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            Set<Integer> set = new HashSet<>();
            for (int j=0; j<r; j++)
                set.add(data[j]);
            processSubset(set);
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n)
            return;

        // current is included, put next at next location
        data[index] = arr[i];
        combinationUtil(arr, n, r, index+1, data, i+1);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationUtil(arr, n, r, index, data, i+1);
    }

    /**
     *     The main function that prints all combinations of size r
     *      in arr[] of size n. This function mainly uses combinationUtil()
     */
     void genProcessCombinations(int arr[], int n, int r)
    {
        // A temporary array to store all combination one by one
        int data[]=new int[r];
        //Set<Set<Integer>> result = new HashSet<Set<Integer>>();
        // Print all combination using temprary array 'data[]'
        combinationUtil(arr, n, r, 0, data, 0);
    }
}