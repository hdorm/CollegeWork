// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 1, Programming #1
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.*;

public class PrintReverseHunterDorminey {

    public static String printCharsReverse(String string){

        //checks for base condition
        if(string.isEmpty()){
            return string;
        }

        //calls for method again and adds next last letter
        else{
            return(printCharsReverse(string.substring(1)) + string.charAt(0));
        }

    }

    public static void main(String[] args){

        //boolean for checking if program should continue after user input
        boolean Continue = true;

        //while loop to keep program going until user specifies it stops
        while(Continue){

            //variable declarations
            Scanner scan = new Scanner(System.in);
            String string;

            //prints prompt for user input and collects user input
            System.out.print("Entered String:   ");
            string = scan.nextLine();

            //prints the reversed string
            System.out.println("Reversed String:  " + printCharsReverse(string));
            System.out.print("Try Again(Y/N):   ");
            String answer = scan.nextLine();

            //stops program if user answers N to prompt
            if(Objects.equals(answer, "N")){
                Continue = false;
            }

        }
    }
}
