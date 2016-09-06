package week2;

import java.io.IOException;
import java.util.Scanner;

/**
 * EnergyValuesTest - client program to test the solution for linear equations
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


public class EnergyValuesTest {
    static RowReduce ReadEquation() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        double a[][] = new double[size][size+1];
        for (int raw = 0; raw < size; ++raw) {
            for (int column = 0; column < size+1; ++column)
                a[raw][column] = scanner.nextInt();
        }
        return new RowReduce(a);
    }


    public static void main(String[] args) throws IOException {
        ReadEquation();
    }
}
