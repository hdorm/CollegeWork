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

        // Checking addFirst() correctly works
        try {
            stackTesterOne(); // add first must work because it is given by me.
        }catch(Exception e) {
            showExceptionMessage("1");
        }
        // Checking addLast() correctly works
        try {stackTesterTwo();} catch(Exception e) {showExceptionMessage("2");}
        // Checking addLast() correctly works
        try {stackTesterThree();} catch(Exception e) {showExceptionMessage("2");}
        // Checking addLast() correctly works
        try {linkedListIteratorTest1();} catch(Exception e) {showExceptionMessage("Iterator");}
    }

    public static void stackTesterOne() {
        printTestName("[1] peek() and push() Tester");
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();

        myStack.push("CC");
        System.out.println(myStack.peek() + "  <-- Your Top");
        System.out.println("CC  <-- Correct Top");
        myStack.push("BB");
        System.out.println(myStack.peek() + "  <-- Your Top");
        System.out.println("BB  <-- Correct Top");
        myStack.push("AA");
        System.out.println(myStack.peek() + "  <-- Your Top");
        System.out.println("AA  <-- Correct Top");
    }

    public static void stackTesterTwo() {
        printTestName("[2] isEmpty() Tester");
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();

        System.out.println(myStack.isEmpty() + "  <-- Your Answer");
    }

    public static void stackTesterThree() {
        printTestName("[2] pop() Tester");
        MyStackHunterDorminey<String> myStack = new MyStackHunterDorminey<>();

        myStack.push("CC");
        myStack.push("BB");
        myStack.push("AA");
        System.out.println("AA  <-- Correct Pop");
        System.out.println(myStack.pop() + "  <-- Your Pop");
        System.out.println("AA  <-- Correct Pop");
        System.out.println(myStack.pop() + "  <-- Your Pop");
        System.out.println("BB  <-- Correct Pop");
        System.out.println(myStack.pop() + "  <-- Your Pop");
        System.out.println("CC  <-- Correct Pop");
    }

    public static void linkedListIteratorTest1() {
        printTestName("Iterator Tester");

        MyLinkedListHunterDorminey<String> myList = new MyLinkedListHunterDorminey<>();

        myList.addFirst("JKL");
        myList.addFirst("GHI");
        myList.addFirst("DEF");
        myList.addFirst("ABC");
        MyLinkedListHunterDorminey<String>.Iterator iter = myList.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next()+" ");
        }
        System.out.println(" <-- Your List");
        System.out.println("ABC DEF GHI JKL  <-- Correct List");
    }
}