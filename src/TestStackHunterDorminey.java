// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 3
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestStackHunterDorminey {

    public static void showExceptionMessage(String str) {
        System.out.println(str);
        System.out.println();
    }

    public static int mainMenu(){
        int choice;
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("-----MAIN MENU-----");
            System.out.println("0 - Exit Program");
            System.out.println("1 - Push");
            System.out.println("2 - Pop");
            System.out.println("3 - Peek (Top)");
            System.out.println("4 - Size");
            System.out.println("5 - Is Empty?");
            System.out.println("6 - Print Stack");
            System.out.print("Choose menu: ");
            choice = scan.nextInt();
        }
        catch(InputMismatchException e){
            System.out.println("Please enter one of the specified numbers.");
            choice = mainMenu();
        }
        return choice;
    }

    public static void main(String[] args) {
        String input;
        int choice = mainMenu();
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();
        while(choice != 0){
            if(choice == 1){
                Scanner scan = new Scanner(System.in);
                System.out.print("Enter next stack element: ");
                input = scan.nextLine();
                myStack.push(input);
                choice = mainMenu();
            }
            else if(choice == 2){
                try{
                    System.out.println();
                    System.out.println(myStack.pop() + " <-- Pop output");
                    System.out.println();
                    choice = mainMenu();
                }
                catch(Exception e){
                    showExceptionMessage("Stack is empty.");
                    choice = mainMenu();
                }
            }
            else if(choice == 3){
                try{
                    System.out.println();
                    System.out.println(myStack.peek() + " <-- Peek output");
                    System.out.println();
                    choice = mainMenu();
                }
                catch(Exception e){
                    showExceptionMessage("Stack is empty.");
                    choice = mainMenu();
                }
            }
            else if(choice == 4){
                System.out.println();
                System.out.println(myStack.size() + " <-- Size output");
                System.out.println();
                choice = mainMenu();
            }
            else if(choice == 5){
                System.out.println();
                System.out.println(myStack.isEmpty() + " <-- isEmpty output");
                System.out.println();
                choice = mainMenu();
            }
            else if(choice == 6){
                System.out.println();
                System.out.println(myStack + " <-- String output");
                System.out.println();
                choice = mainMenu();
            }
            else{
                System.out.println();
                System.out.println("Please enter one of the specified numbers.");
                System.out.println();
                choice = mainMenu();
            }
        }
    }
}