// Name: Hunter Dorminey
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 2

// Algorithm Design Block
/* Problem Statement:

   We need to create a class which contains two methods. The first method creates an array of
   size 1024 which contains random and distinct numbers between 1 and 9999 and then sorts it
   in ascending order. The second method presents the user with a menu with options to create
   the array, set the size of a table, call an interpolation search class, and exit the program.
   It is able to take input from the user and allow them to complete all the available options.

*/

/* Algorithm Logical Steps:

   1. Start while loop which continually displays menu.
   2. Do one of four options depending on the user input.
   3. End program when user selects that option.

*/

/* Algorithm Pseudocode Syntax:

   RandomDistinct() <- Creates array with unique and random numbers using ThreadLocalRandom and returns it
   {
     fill Array[] with random and unique numbers of desired amount
     sort Array in ascending order
     return Array
   }
   RunIS() <- Display user with menu and allow them to select the desired one
   {
     while(true)
     {
       Display menu with options
       if "1"
       {
       call RandomDistinct() and set it as an array variable
       }
       if "2"
       {
       ask for the size of table and store the variable
       }
       if "3"
       {
       call the interpolation search algorithm and display its output in a table
       }
       if "4"
       {
       exit the loop and program
       }
     }
*/

// Implementation Section

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

// Class for running tests on the interpolation search class
public class TestIS {

    // Method for creating and returning a sorted 1024 size array with random and distinct numbers
    public static int[] RandomDistinct(){
        int[] sortedArray = ThreadLocalRandom.current().ints(1, 9999).distinct().limit(1024).toArray();
        Arrays.sort(sortedArray);
        return sortedArray;
    }
    // Method that creates the user menu and does the calling of other methods based on their input
    public static void RunIS(){
        // Variable declarations
        int numbersInput;
        int tableSize = 0;
        boolean cont = true;
        int[] randomArray = new int[1024];
        // Initializes scanner to receive user input
        Scanner scan = new Scanner(System.in);
        // While loop which keeps the main menu running until the user chooses to exit the program
        while(cont) {
            System.out.println("---------------MAIN MENU---------------");
            System.out.println("1. Create and display array Values[]");
            System.out.println("2. Read output table size");
            System.out.println("3. Run algorithm and display outputs");
            System.out.println("4. Exit program");
            System.out.println();
            System.out.print("Enter option number: ");
            numbersInput = scan.nextInt();
            // Calls the random array method and displays the random array using for loops
            if (numbersInput == 1) {
                randomArray = RandomDistinct();
                int currentIndex = 0;
                for (int i = 0; i < 35; i++) {
                    for (int j = 0; j < 30; j++) {
                        if(currentIndex < 1024) {
                            System.out.print(randomArray[currentIndex] + "   \t");
                        }
                        currentIndex++;
                    }
                    System.out.println();
                }
                System.out.println();
                // Sets the table size depending on user input
            } else if (numbersInput == 2) {
                System.out.print("Please enter the desired table size: ");
                tableSize = scan.nextInt();
                // Sends the array and key to the interpolation search class as many times as the user specified with
                // option 2 using a for loop. Also keeps track of the sum of divisions, the average amount of divisions,
                // and the difference from the average efficiency of interpolation search
            } else if (numbersInput == 3) {
                double divisionSum = 0;
                System.out.println("  Key      Found     Index      Divisions");
                System.out.println("-------------------------------------------");
                for(int i = 0; i < tableSize; i++){
                    int randomKey = ThreadLocalRandom.current().nextInt(1, 9999);
                    new InterpolationSearch(randomArray, randomKey);
                }
                // Gets the lists from the interpolation search class which are storing the table data, so they can
                // be displayed
                ArrayList<Integer> indices = InterpolationSearch.indices;
                ArrayList<Integer> keys = InterpolationSearch.keys;
                ArrayList<Integer> divisionsList = InterpolationSearch.divisionsList;
                ArrayList<Boolean> founds = InterpolationSearch.founds;
                // for loop which prints the table data
                for(int i = 0; i < tableSize; i++){
                    System.out.print("  ");
                    System.out.printf("%-8d %-10s %-13d %-1d\n", keys.get(i), founds.get(i), indices.get(i), divisionsList.get(i));
                    divisionSum = divisionsList.get(i) + divisionSum;
                }
                // Calculates the division average and difference
                double divisionAverage = divisionSum / tableSize;
                double difference = 3.322 - divisionAverage;
                System.out.println("Divisions average:     " + divisionAverage);
                System.out.println("Difference:            " + difference);
                // Calls method in interpolation search class which resets the table data for subsequent runs
                InterpolationSearch.reset();
                // Ends the while loop and program
            } else if (numbersInput == 4) {
                cont = false;
            }
        }
    }
    // Main method which only calls the RunIS method
    public static void main(String[] args) {
        RunIS();
    }
}
