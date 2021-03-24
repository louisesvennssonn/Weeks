package exercises;

import java.util.Arrays;
import java.util.Random;

import static java.lang.StrictMath.round;
import static java.lang.System.out;

/*
 *  Methods with array params and/or return value. Implement methods.
 *
 *  See:
 *  - MathMethods
 *  - ArrayMethods
 */
public class Ex2ArrayMethods {

    public static void main(String[] args) {
        new Ex2ArrayMethods().program();
    }

    final static Random rand = new Random();


    void program() {
        int[] arr = {1, 2, 2, 5, 3, 2, 4, 2, 7};  // Hard coded test data

        // TODO uncomment one at a time and implement

        // Count occurrences of some element in arr
        out.println(count(arr, 2) == 4);      // There are four 2's
        out.println(count(arr, 7) == 1);

        // Generate array with 100 elements with 25% distribution of -1's and 1's (remaining will be 0)
        arr = generateDistribution(100, 0.25, 0.25);
        out.println(count(arr, 1) == 25);
        out.println(count(arr, -1) == 25);
        out.println(count(arr, 0) == 50);

        // Generate array with 14 elements with 40% 1's and 30% -1's
        arr = generateDistribution(14, 0.4, 0.3);
        out.println(count(arr, 1) == 6);
        out.println(count(arr, -1) == 4);

        for (int i = 0; i < 10; i++) {
            // Random reordering of arr, have to check by inspecting output
            shuffle(arr);
            out.println(Arrays.toString((arr)));  // Does it look random?
        }
    }



    // ---- Write methods below this ------------
    int count(int[] arr, int num) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                count++;
            }
        }return count;

    }
    int[] generateDistribution(int n, double p, double p1) {
        int[] arr;
        arr = new int[n];
        for (int i= 0; i < round((n*p)); i++){
            arr[i] = 1;
        }
        for(double i = round((n*p)); i < round((n*p+ n*p1)); i++){
            arr[(int) i] = -1;
        }
       return arr;
    }

    void shuffle(int [] arr){
        for (int i = 0; i < arr.length; i++) {
            int randomPosition = rand.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[randomPosition];
            arr[randomPosition] = temp;

        }
    }

}

