// Name: Hunter Dorminey
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 3

// Algorithm Design Block
// Analysis and discussion details of outputs go in this block

//              Best case       Worst case      Average case
//-----------------------------------------------------------
// Mergesort    O(n * log(n))   O(n * log(n))   O(n * log(n))
// Quicksort    O(n * log(n))   O(n^2)          O(n * log(n))
// Heapsort     O(n * log(n))   O(n * log(n))   O(n * log(n))

// Mergesort and heapsort both have fairly similar results when going
// from random to increasing to decreasing arrays. This tracks well
// with them having the same performance regardless of the data they
// are working with. On the other hand, quicksort goes from having
// a similar number of operations as the other two when working with
// a random set of numbers to having a much, much larger number of
// operations when working with its worst cases. Trying to sort an
// already sorted array with quicksort results in an exponentially
// larger number of operations compared to its counterparts. My IDE
// is not actually able to run quicksort when working with anything
// larger than 10^4 as it results in an overflow error. My quicksort
// results are showing around half as many operations as it should
// when working with worst case arrays. I am getting around 500,000
// and 50,000,000 when I should be getting 1,000,000 and 100,000,000
// according to O(n^2), which could be a result of having misplaced
// my count variables.

// Code Section
// Working code goes here

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class testAdvancedSorting {

    // Creates all the arrays that will be needed
    public static int[] thirdIncreasing = new int[1000];
    public static int[] fourthIncreasing = new int[10000];
    public static int[] fifthIncreasing = new int[100000];
    public static int[] sixthIncreasing = new int[1000000];
    public static int[] thirdDecreasing = new int[1000];
    public static int[] fourthDecreasing = new int[10000];
    public static int[] fifthDecreasing = new int[100000];
    public static int[] sixthDecreasing = new int[1000000];
    public static int[] thirdRandom;
    public static int[] fourthRandom;
    public static int[] fifthRandom;
    public static int[] sixthRandom;
    public static int[][] arrayBundle;

    // Method to fill up the arrays we will be sorting
    public static void populateArrays() {

        // Variable used to stop arrays from overfilling
        int index = 0;

        // Creates four arrays of varying sizes with distinct and random numbers
        thirdRandom = ThreadLocalRandom.current().ints(1, 10000).distinct().limit(100).toArray();
        fourthRandom = ThreadLocalRandom.current().ints(1, 100000).distinct().limit(10000).toArray();
        fifthRandom = ThreadLocalRandom.current().ints(1, 1000000).distinct().limit(100000).toArray();
        sixthRandom = ThreadLocalRandom.current().ints(1, 10000000).distinct().limit(1000000).toArray();

        // For loop which fills four arrays with increasing numbers
        for (int i = 0; i < 1000000; i++) {
            // These if statements stop the for loop from trying to put too many numbers
            if (i < 1000) {
                thirdIncreasing[i] = i + 1;
            }
            if (i < 10000) {
                fourthIncreasing[i] = i + 1;
            }
            if (i < 100000) {
                fifthIncreasing[i] = i + 1;
            }
            sixthIncreasing[i] = i + 1;
        }
        // For loop which fills four arrays with decreasing numbers
        for (int i = 1000000; i > 0; i--) {
            // Uses the index variable to keep track of size since the i variable is set to a million
            if (index < 1000) {
                thirdDecreasing[index] = i - 999000;
            }
            if (index < 10000) {
                fourthDecreasing[index] = i - 990000;
            }
            if (index < 100000) {
                fifthDecreasing[index] = i - 900000;
            }
            sixthDecreasing[index] = i;
            index++;
        }
        // Creates an array of arrays to hold the various arrays and allow for looping through them
        arrayBundle = new int[][]{thirdRandom, fourthRandom, fifthRandom, sixthRandom, thirdIncreasing, fourthIncreasing, fifthIncreasing, sixthIncreasing, thirdDecreasing, fourthDecreasing, fifthDecreasing, sixthDecreasing};
    }

    // Method which shows the user a menu and follows their input whether the program should populate the arrays, run the sort algorithms, display the results, or exit the program
    public static void runTest() {
        // Sets up variables needed for the test method
        int numbersInput;
        int[] mergeComparisons = new int[12];
        int[] quickComparisons = new int[12];
        int[] heapComparisons = new int[12];
        boolean cont = true;
        // Initializes scanner to receive user input
        Scanner scan = new Scanner(System.in);
        // While loop which keeps the main menu running until the user chooses to exit the program
        while (cont) {
            System.out.println("---------------MAIN MENU---------------");
            System.out.println("1. Populate Arrays");
            System.out.println("2. Run Algorithms");
            System.out.println("3. Display outputs");
            System.out.println("4. Exit program");
            System.out.println();
            System.out.print("Enter option number: ");
            numbersInput = scan.nextInt();
            // Populates the arrays if user selects option one
            if (numbersInput == 1) {
                populateArrays();
                // Uses a for loop to send all the arrays through the different sort algorithms if the user selects option two
                // Stores the counts from each of the sorts in an array specific to that sort
                // Skips array indices 6, 7, 10, and 11 for quicksort to avoid an overflow error
            } else if (numbersInput == 2) {
                for (int i = 0; i < 12; i++) {
                    new Mergesort(arrayBundle[i]);
                    mergeComparisons[i] = Mergesort.mergesortCount;
                    if (i == 6 || i == 7 || i == 10 || i == 11) {
                        quickComparisons[i] = 0;
                    } else {
                        new Quicksort(arrayBundle[i]);
                        quickComparisons[i] = Quicksort.quicksortCount;
                    }
                    new Heapsort(arrayBundle[i]);
                    heapComparisons[i] = Heapsort.heapsortCount;
                }
                // Displays the count results from the sort algorithms using printf for formatting if the user selects option three
            } else if (numbersInput == 3) {
                System.out.println();
                System.out.println("Array Type: Random");
                System.out.println("Algorithm     n=1000    n=10000     n=100000    n=1000000");
                System.out.println("----------------------------------------------------------");
                System.out.printf("%-13s %-9d %-11d %-11d %-7d\n", "Mergesort", mergeComparisons[0], mergeComparisons[1], mergeComparisons[2], mergeComparisons[3]);
                System.out.printf("%-13s %-9d %-11d %-11d %-7d\n", "Quicksort", quickComparisons[0], quickComparisons[1], quickComparisons[2], quickComparisons[3]);
                System.out.printf("%-13s %-9d %-11d %-11d %-7d\n", "Heapsort", heapComparisons[0], heapComparisons[1], heapComparisons[2], heapComparisons[3]);
                System.out.println();
                System.out.println("Array Type: Increasing");
                System.out.println("Algorithm     n=1000    n=10000     n=100000    n=1000000");
                System.out.println("----------------------------------------------------------");
                System.out.printf("%-13s %-9d %-11d %-11d %-7d\n", "Mergesort", mergeComparisons[4], mergeComparisons[5], mergeComparisons[6], mergeComparisons[7]);
                System.out.printf("%-13s %-9d %-11d %-11s %-7s\n", "Quicksort", quickComparisons[4], quickComparisons[5], "Overflow", "Overflow");
                System.out.printf("%-13s %-9d %-11d %-11d %-7d\n", "Heapsort", heapComparisons[4], heapComparisons[5], heapComparisons[6], heapComparisons[7]);
                System.out.println();
                System.out.println("Array Type: Decreasing");
                System.out.println("Algorithm     n=1000    n=10000     n=100000    n=1000000");
                System.out.println("----------------------------------------------------------");
                System.out.printf("%-13s %-9d %-11d %-11d %-7d\n", "Mergesort", mergeComparisons[8], mergeComparisons[9], mergeComparisons[10], mergeComparisons[11]);
                System.out.printf("%-13s %-9d %-11d %-11s %-7s\n", "Quicksort", quickComparisons[8], quickComparisons[9], "Overflow", "Overflow");
                System.out.printf("%-13s %-9d %-11d %-11d %-7d\n", "Heapsort", heapComparisons[8], heapComparisons[9], heapComparisons[10], heapComparisons[11]);
                System.out.println();
                // Exits the program if the user selects option four
            } else if (numbersInput == 4) {
                cont = false;
            }
        }
    }

    // Main method which only calls the runTest method
    public static void main(String[] args) {
        runTest();
    }
}