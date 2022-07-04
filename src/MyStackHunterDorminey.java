// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 3
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.ArrayList;

public class MyStackHunterDorminey<E> {

    public ArrayList<E> stack = new ArrayList<>();

    int top = -1;

    public void push(E element){
        top = top + 1;
        stack.add(element);
    }

    public E pop() throws RuntimeException{
        if(stack.size() == 0){
            throw new RuntimeException("in pop(): no elements in the stack");
        }
        else {
            int pop = top;
            top = top - 1;
            return stack.get(pop);
        }
    }

    public E peek() throws RuntimeException{
        if(stack.size() == 0){
            throw new RuntimeException("in pop(): no elements in the stack");
        }
        else {
            return stack.get(top);
        }
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return stack.size() == 0;
    }

    public String toString(){
        return stack.toString();
    }

}

