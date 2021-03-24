package exercises;

import java.util.Arrays;

import static java.lang.StrictMath.round;
import static java.lang.System.*;

/*
 * Methods with array/matrix params and/or return value. Implement methods.
 *
 * See:
 * - Matrices
 */
public class Ex3MatrixMethods {

    public static void main(String[] args) {
        new Ex3MatrixMethods().program();
    }

    void program() {
        int[][] m = {           // Hard coded test data
                {-1, 0, -5, 3},
                {6, 7, -2, 0},
                {9, -2, -6, 8},
                {0, 0, 5, -6}
        };

        // TODO uncomment one at a time and implement

        // Return array with all negatives in m
       int[] negs = getNegatives(m);
       out.println(negs.length == 6);
       out.println(Arrays.toString(negs).equals("[-1, -5, -2, -2, -6, -6]")); // Possibly other ordering!

        // Mark all negatives with a 1, others as 0
        // (create matrix on the fly)
        int[][] marked = markNegatives(new int[][]{
                {1, -2, 3,},
                {-4, 5, -6,},
                {7, -8, 9,},
        });
        /* marked should be (don't uncomment)
        { {0, 1, 0},
          {1, 0, 1},
          {0, 1, 0} }
        */
        out.println(Arrays.toString(marked[0]).equals("[0, 1, 0]"));
        out.println(Arrays.toString(marked[1]).equals("[1, 0, 1]"));
        out.println(Arrays.toString(marked[2]).equals("[0, 1, 0]"));

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // Create matrix from array
        int[][] matrix = toMatrix(arr);
        /* matrix should be (don't uncomment)
        { {1, 2, 3},
          {4, 5, 6},
          {7, 8, 9} }
        */
        //plot(matrix);  // If manual inspection
        out.println(Arrays.toString(matrix[0]).equals("[1, 2, 3]"));
        out.println(Arrays.toString(matrix[1]).equals("[4, 5, 6]"));
        out.println(Arrays.toString(matrix[2]).equals("[7, 8, 9]"));

        // Sum of all directly surrounding elements to some element in matrix
        // (not counting the element itself)
        // NOTE: Should be possible to expand method to include more distant neighbours
        out.println(sumNeighbours(matrix, 0, 0) == 11);
        out.println(sumNeighbours(matrix, 1, 1) == 40);
        out.println(sumNeighbours(matrix, 1, 0) == 23);
    }

    // -------- Write methods below this -----------------------

    int[] getNegatives(int[][] m){
        int count =0;
        int nNegatives = getNNegative(m);
        int [] neg = new int[nNegatives];
        for (int r =0; r < m.length; r++){
            for(int c= 0; c < m.length; c++) {
                if (m[r][c] < 0) {
                    neg[count]= m[r][c];
                    count++;
                }
            }
        }
        return neg;
    }
    int getNNegative(int[][] m) {
        int sum = 0;
        for (int r = 0; r < m.length; r++) {
            for (int c= 0; c < m.length; c++){
                if (m[r][c] < 0){
                    sum++;
                }
            }
        } return sum;
    }
    int[][] markNegatives(int[][] negatives) {

        int [][] marked = new int [negatives.length][negatives.length];
        for (int r = 0; r < negatives.length; r++) {
            for (int c= 0; c < negatives.length; c++){
                if (negatives[r][c] < 0){
                    marked[r][c] = 1;
                } else{
                    if(negatives[r][c]> 0){
                        marked[r][c]= 0;
                    }
                }
            }
        } return marked;
    }
    int [][] toMatrix(int[] arr){
        int square = (int) Math.sqrt(arr.length);
        int[][] matrix = new int[square][square];
        for(int i= 0; i< arr.length; i++){
            int row= i/square;
            int col = i%square;
            matrix[row][col] =arr[i];
        }
        return matrix;
    }
    int sumNeighbours(int[][] matrix, int row, int col) {
        int sum= 0;
        for(int r= (row- 1); r <=(row+1); r++){
            for (int c= (col-1); c <= (col+1); c++){
                if(r>=0 && c>= 0 && r<matrix.length && c<matrix.length){
                    if(!(r==row && c==col)){
                        sum = sum+matrix[r][c];
                    }
                }
            }
        }
        return sum;
    }

    // TODO Methods here

    // Use if you like (during development)
    void plot(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            out.println(Arrays.toString(matrix[row]));
        }
    }


}
