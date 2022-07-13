// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 3
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.ArrayList;
import java.util.Scanner;

public class ExprHunterDorminey {
    
    public static String infixToPostfix(String infix){
        MyStackHunterDorminey<Character> operator = new MyStackHunterDorminey<>();
        StringBuilder newPostfix = new StringBuilder();
        for(char left : infix.toCharArray()) {

            //appends operands if one is detected
            if (left == '0' || left == '1' || left == '2' || left == '3' || left == '4' || left == '5' || left == '6' || left == '7' || left == '8' || left == '9') {
                newPostfix.append(left);
            }

            //handles operators
            else if (left == '+' || left == '-' || left == '*' || left == '/' || left == '^') {

                //appends operator if stack is currently empty
                if(operator.isEmpty()){
                    operator.push(left);
                }

                //decides what to do with operator if stack is not empty
                else if(!operator.isEmpty()){

                        //decides what to do if operator is addition or subtraction
                        if (left == '+' || left == '-') {
                            while (!operator.isEmpty()) {
                                if(operator.peek() != '(') {
                                    newPostfix.append(operator.pop());
                                }
                                else if(operator.peek() == '(' || operator.peek() == '*' || operator.peek() == '/' || operator.peek() == '^'){
                                    operator.push(left);
                                    break;
                                }
                            }
                        }

                        //decides what to do if operator is multiplication or division
                        else if (left == '*' || left == '/') {
                            while(!operator.isEmpty()) {
                                if(operator.peek() == '*' || operator.peek() == '/' || operator.peek() == '^'){
                                    newPostfix.append(operator.pop());
                                }
                                else if(operator.peek() == '(' || operator.peek() == '+' || operator.peek() == '-'){
                                    operator.push(left);
                                    break;
                                }
                            }
                        }
                        else {
                            while (!operator.isEmpty()){
                                if (operator.peek() == '^') {
                                    newPostfix.append(operator.pop());
                                }
                                else if(operator.peek() == '(' || operator.peek() == '*' || operator.peek() == '/' || operator.peek() == '+' || operator.peek() == '-'){
                                    operator.push(left);
                                    break;
                                }
                            }
                        }
                    }
                }

            //decides what to do if token is "("
            else if (left == '(') {
                operator.push(left);
            }

            //decides what to do if token is ")"
            else if (left == ')') {
                while(!operator.isEmpty()){
                    if(operator.peek() != '('){
                        newPostfix.append(operator.pop());
                    }
                    else if(operator.peek() == '('){
                        operator.pop();
                        break;
                    }
                }
            }
            else{
                while(!operator.isEmpty()){
                    newPostfix.append(operator.pop());
                }
            }
        }
        return newPostfix.toString();
    }

    public static String postfixEval(String postfix){
        return "Unfinished.";
    }

    public static void main(String[] args) {
        String answer = "Y";
        while(answer.equals("Y")) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter an infix:     ");
            String newInfix = scan.nextLine();
            String newPostfix = infixToPostfix(newInfix);
            System.out.println("Postfix Expression: " + newPostfix);
            String newEval = postfixEval(newPostfix);
            System.out.println("Result Value:       " + newEval);
            System.out.print("Do you want to continue? (Y/N) ");
            answer = scan.nextLine();
        }
    }
}