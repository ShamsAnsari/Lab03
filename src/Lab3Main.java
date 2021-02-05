import java.util.Scanner;

public class Lab3Main {
    public static void main(String[] args) {

        final int SORT_SIZE_MAX = 16;
        int numElements = getUserInput(1, SORT_SIZE_MAX, "Enter number of elements: ");

        Scanner scan = new Scanner(System.in);


        int choice = getUserInput(1, 2, "Pick Object\n" +
                "\t1 - Compare USD\n" +
                "\t2 - Compare C2D\n" +
                "\tEnter a choice: ");

        USD[] arr = choice == 1 ? new USD[numElements] : new C2D[numElements];

        System.out.println("Enter money in form: X.XX");
        for (int i = 0; i < numElements; i++) {
            double input = getUserInput(0.009, Double.MAX_VALUE, "\tArray[" + i + "] = ");
            arr[i] = choice == 1 ? new USD() : new C2D();
            arr[i].setDollars((int) input);
            arr[i].setCents((int) (input * 100 % 100));
        }

        mergeSort(arr, numElements);


    }


    public static void mergeSort(USD arr[], int size) {

    }

    public static void mergeSort(USD arr[], int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);

        }
    }

    public static void merge(USD arr[], int left, int mid, int right) {
        int size1 = mid - left + 1;//left side
        int size2 = right - mid; //right side

        USD L[] = new USD[size1];
        USD R[] = new USD[size2];

        for (int i = 0; i < size1; i++)
            L[i] = arr[left + i];
        for (int i = 0; i < size2; i++)
            R[i] = arr[mid + i + 1];

        int i = 0, j = 0;

        int k = left;
        while( i < size1 && j < size2){

        }
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
            System.out.print(msg);
            input = scan.nextInt();
            if (input <= upperBound && input >= lowerBound) {
                break;
            }
            System.out.println("\tBounds of input: [" + lowerBound + ", " + upperBound + "]");
        }
        return input;

    }

    /**
     * Gets user input (double) and validates in loop
     *
     * @param lowerBound Lower bound of input (not inclusive)
     * @param upperBound upper bound of input (inclusive
     * @param msg        message before  user enter input
     * @return user input
     */
    public static double getUserInput(double lowerBound, double upperBound, String msg) {

        Scanner scan = new Scanner(System.in);

        double input;
        while (true) {
            System.out.print(msg);
            input = scan.nextDouble();
            if (input <= upperBound && input > lowerBound) {
                break;
            }
            System.out.println("\tBounds of input: [" + lowerBound + ", " + upperBound + "]");
        }
        return input;

    }
}

