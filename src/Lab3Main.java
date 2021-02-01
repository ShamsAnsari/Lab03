import java.util.Scanner;

public class Lab3Main {
    public static void main(String[] args){

        final int SORT_SIZE_MAX = 16;
        int numElements = getUserInput(1,SORT_SIZE_MAX, "Enter number of elements: ");

        Scanner scan = new Scanner(System.in);


        int choice = getUserInput(1,2,"Pick Object\n" +
                "\t1 - Compare USD\n" +
                "\t2 - Compare C2D\n" +
                "\tEnter a choice: ");

        USD[] arr = choice == 1? new USD[numElements]: new C2D[numElements];


    }




    /** Gets user input (int) and validates in loop
     *
     * @param lowerBound Lower bound of input
     * @param upperBound upper bound of input
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
}

