// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 1, Programming #2
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.*;

public class AverageGradeHunterDorminey {

    public static double findAverage(int[] array, int size) {

        //checks for base condition
        if(size <= 0){
            return 0;
        }

        //calls for method again and adds the last element in the array
        else{
            return findAverage(array, size-1) + array[size-1];
        }
    }

    public static void main(String[] args) {

        boolean Continue = true;

        //loops if user is not done
        while (Continue) {

            //establishes variables
            int size;
            String answer;
            Scanner scan = new Scanner(System.in);
            int[] classArray;

            //requests class size and grades and stores responses
            System.out.print("Class size:     ");
            size = scan.nextInt();
            classArray = new int[size];
            System.out.print("Entered grades: ");

            //for loop that requests the amount of grades in the class
            for (int i = 0; i < size; i++) {
                classArray[i] = scan.nextInt();
                scan.nextLine();
            }

            //prints the class average after dividing the sum of the array which was found with recursion
            System.out.println("Class average:  " + String.format("%.2f", findAverage(classArray, size)/ classArray.length));
            System.out.print("Try Again(Y/N):  ");
            answer = scan.nextLine();

            //stops the loop if user is done
            if (Objects.equals(answer, "N")) {
                Continue = false;
            }

        }
    }
}

