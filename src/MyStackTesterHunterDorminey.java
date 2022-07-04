// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 2 Part Two
// IDE: 		    IntelliJ, JDK 18.0.1

public class MyStackTesterHunterDorminey {

    public static void printTestName(String str) {
        System.out.println();
        System.out.println("=================================");
        System.out.println("\t" + str);
        System.out.println("=================================");
    }

    public static void showExceptionMessage(String str) {
        System.out.println("#################################");
        System.out.println("Exception Occurs in Tester [" + "]");
        System.out.println("#################################");
        System.out.println();
    }

    public static void main(String[] args) {

        // Checking peek() and push() correctly works
        try {
            stackTesterOne(); // add first must work because it is given by me.
        }catch(Exception e) {
            showExceptionMessage("1");
        }
        // Checking isEmpty() correctly works
        try {stackTesterTwo();} catch(Exception e) {showExceptionMessage("2");}
        // Checking pop() correctly works
        try {stackTesterThree();} catch(Exception e) {showExceptionMessage("2");}
        // Checking size() correctly works
        try {stackTesterFour();} catch(Exception e) {showExceptionMessage("2");}
        // Checking toString() correctly works
        try {stackTesterFive();} catch(Exception e) {showExceptionMessage("2");}
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