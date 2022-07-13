// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 3
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.ArrayList;

public class MyStackHunterDorminey<E> {

    public ArrayList<E> stack = new ArrayList<>();

    int top = -1;
    int size = 0;

    public void push(E element){
        top = top + 1;
        stack.add(element);
        size++;
    }

    public E pop() throws RuntimeException{
        if(size == 0){
            throw new RuntimeException("in pop(): no elements in the stack");
        }
        else {
            E popped = stack.get(top);
            stack.remove(top);
            top = top - 1;
            size--;
            return popped;
        }
    }

    public E peek() throws RuntimeException{
        if(size == 0){
            throw new RuntimeException("in pop(): no elements in the stack");
        }
        else {
            return stack.get(top);
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public String toString(){
        return stack.toString();
    }

}

