/**
 * Lab 3 - Sorting Arrays
 * @author Shams Ansari
 *
 * Prof: Manish Goel
 * Class: CIS 22C
 *
 * Sorting an array using mergesort and printing out the steps
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lab3Main {
    // path to output file
    final static String FILE_PATH = "output.txt";

    // Max size of array
    final static int SORT_SIZE_MAX = 16;

    /**
     * Create output file
     * Get size of array from user
     * Get choice of currency from user
     * Create array
     * Let user enter data
     * sort array
     *
     * @param args Not used
     */
    public static void main(String[] args) {

        //Create Output.txt file
        File output = new File(FILE_PATH);
        try {
            output.createNewFile();
            new FileWriter(FILE_PATH, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Get size of array from user
        int numElements = getUserInput(1, SORT_SIZE_MAX, "Enter number of elements: ");

        //Get choice of currency from user
        int choice = getUserInput(1, 2, "Pick Object\n" +
                "\t1 - Compare USD\n" +
                "\t2 - Compare C2D\n" +
                "\tEnter a choice: ");

        //Create array
        USD[] arr = choice == 1 ? new USD[numElements] : new C2D[numElements];

        //Get data for array from user
        write("Enter money in form: X.XX\n");
        for (int i = 0; i < numElements; i++) {
            //Money has to be at least 1 cent
            double input = getUserInput(0.009, Double.MAX_VALUE, "\tArray[" + i + "] = ");
            arr[i] = choice == 1 ? new USD() : new C2D();
            arr[i].setDollars((int) input);
            arr[i].setCents((int) (input * 100 % 100));
        }


        //Sort array using merge sort
        mergeSort(arr, numElements);

        //"Pause" at the end
        write("Press ENTER to Exit");
        new Scanner(System.in).nextLine();


    }

    /**
     * Sorts the array of USD's with mergesort
     *
     * @param arr  arr of USD's
     * @param size size of array
     */
    public static void mergeSort(USD arr[], int size) {
        recurMergeSort(arr, 0, size - 1);
        printArray(arr);
    }

    /**
     * Recursive mergeSort function wrapper
     *
     * @param arr   array to sort
     * @param left  left bound
     * @param right right bound
     */
    public static void recurMergeSort(USD arr[], int left, int right) {
        printArray(arr); //print the array
        if (left < right) {
            int mid = left + (right - left) / 2;

            recurMergeSort(arr, left, mid); //Sort from left to middle
            recurMergeSort(arr, mid + 1, right); // Sort from middle to right

            merge(arr, left, mid, right); // Merge the left and right side of the bounds
        }
    }

    /**
     * MergeSort helper. "Merges"  two arrays by sorting them
     *
     * @param arr original array
     * @param left left bound of array
     * @param mid middle  of a array
     * @param right right bound of array
     */
    public static void merge(USD arr[], int left, int mid, int right) {
        int size1 = mid - left + 1;//left side size
        int size2 = right - mid; //right side size

        USD L[] = new USD[size1]; //New array for left side
        USD R[] = new USD[size2]; //New array for right side

        //Copies part of array into new array of left and right side
        for (int i = 0; i < size1; i++)
            L[i] = arr[left + i];
        for (int i = 0; i < size2; i++)
            R[i] = arr[mid + i + 1];

        //i is the index for left side j int the index for right size
        int i = 0, j = 0;
        int k = left;

        //Merges/Sorts the left and right into the original array. "Merges"
        while (i < size1 && j < size2) {
            //If number in Left array is less than number in right array
            //Then add the number in left array to original array
            //Increment left array
            if (L[i].compareTo(R[j]) < 0) {
                arr[k] = L[i];
                i++;
            //If number in right array is greater then number i left array
            //Then add the number in right array to original array
            //increment right array
            } else {
                arr[k] = R[j];
                j++;
            }
            //Increment original array index
            k++;
        }

        //One of the arrays will have some remaining items
        //Put all remaining into the original array

        while (i < size1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < size2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /**
     * Write obj to both console and to and output file
     * Output file path is "FILE_PATH" variable
     * Appends to file
     *
     * @param obj obj to print
     */
    public static void write(Object obj) {

        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_PATH, true); //Appends to file
            writer.write(obj.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);


    }

    /**
     * Prints array of objects to both console and output file
     *
     * @param arr array to printout
     */
    public static void printArray(Object[] arr) {

        String out = "[";
        for (Object obj : arr) {
            out += obj + " ";
        }
        out = out.substring(0, out.length() - 1) + "]";
        write(out + "\n");

    }

    /**
     * Gets user input (int) and validates in loop
     *
     * @param lowerBound Lower bound of input (inclusive)
     * @param upperBound upper bound of input (inclusive)
     * @param msg        message before  user enter input
     * @return user input
     */
    public static int getUserInput(int lowerBound, int upperBound, String msg) {

        Scanner scan = new Scanner(System.in);

        int input;
        while (true) {
            write(msg);
            input = scan.nextInt();
            if (input <= upperBound && input >= lowerBound) {
                break;
            }
            write("\tBounds of input: [" + lowerBound + ", " + upperBound + "]\n");
        }
        return input;

    }

    /**
     * Gets user input (double) and validates in loop
     *
     * @param lowerBound Lower bound of input (Exclusive)
     * @param upperBound upper bound of input (inclusive
     * @param msg        message before  user enter input
     * @return user input
     */
    public static double getUserInput(double lowerBound, double upperBound, String msg) {

        Scanner scan = new Scanner(System.in);

        double input;
        while (true) {
            write(msg);
            input = scan.nextDouble();
            if (input <= upperBound && input > lowerBound) {
                break;
            }
            write("\tBounds of input: [" + lowerBound + ", " + upperBound + "]" + "\n");

        }
        return input;

    }
}

