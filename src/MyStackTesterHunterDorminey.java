// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 2 Part Two
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.Scanner;

public class MyStackTesterHunterDorminey {

    public static void printTestName(String str) {
        System.out.println();
        System.out.println("=================================");
        System.out.println("\t" + str);
        System.out.println("=================================");
    }

    public static void showExceptionMessage(String str) {
        System.out.println("#################################");
        System.out.println("Exception Occurs in Tester [" + str + "]");
        System.out.println("#################################");
        System.out.println();
    }

    public static int mainMenu(){
        int choice;
        Scanner scan = new Scanner(System.in);
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
        return choice;
    }

    public static void main(String[] args) {
        String input;
        int choice = mainMenu();
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();
        while(choice != 0){
            if(choice == 1){
                Scanner scan = new Scanner(System.in);
                input = scan.nextLine();
                myStack.push(input);
                choice = mainMenu();
            }
            else if(choice == 2){
                try{
                    System.out.println(myStack.pop());
                    choice = mainMenu();
                }
                catch(Exception e){
                    showExceptionMessage("2");
                    choice = mainMenu();
                }
            }
            else if(choice == 3){
                System.out.println(myStack.peek());
                choice = mainMenu();
            }
            else if(choice == 4){
                System.out.println(myStack.size());
                choice = mainMenu();
            }
            else if(choice == 5){
                System.out.println(myStack.isEmpty());
                choice = mainMenu();
            }
            else if(choice == 6){
                System.out.println(myStack);
                choice = mainMenu();
            }
        }
    }

    public static void stackTesterOne() {
        printTestName("[1] peek() and push() Tester");
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();

        myStack.push("CC");
        System.out.println(myStack.peek() + "  <-- Your top");
        System.out.println("CC  <-- Correct top");
        myStack.push("BB");
        System.out.println(myStack.peek() + "  <-- Your top");
        System.out.println("BB  <-- Correct top");
        myStack.push("AA");
        System.out.println(myStack.peek() + "  <-- Your top");
        System.out.println("AA  <-- Correct top");
    }

    public static void stackTesterTwo() {
        printTestName("[2] isEmpty() Tester");
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();

        System.out.println(myStack.isEmpty() + "  <-- Your Answer");
    }

    public static void stackTesterThree() {
        printTestName("[3] pop() Tester");
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();

        myStack.push("CC");
        myStack.push("BB");
        myStack.push("AA");
        System.out.println("AA  <-- Correct pop");
        System.out.println(myStack.pop() + "  <-- Your pop");
        System.out.println("AA  <-- Correct pop");
        System.out.println(myStack.pop() + "  <-- Your pop");
        System.out.println("BB  <-- Correct pop");
        System.out.println(myStack.pop() + "  <-- Your pop");
        System.out.println("CC  <-- Correct pop");
    }

    public static void stackTesterFour() {
        printTestName("[4] size() Tester");
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();

        myStack.push("CC");
        myStack.push("BB");
        myStack.push("AA");
        System.out.println(myStack.size() + "  <-- Your size");
    }

    public static void stackTesterFive() {
        printTestName("[5] toString() Tester");
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();

        myStack.push("CC");
        myStack.push("BB");
        myStack.push("AA");
        System.out.println(myStack + "  <-- Your string");
    }
}