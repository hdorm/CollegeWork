// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 3
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.ArrayList;

public class MyStackHunterDorminey<E> {

    ArrayList<E> Stack = new ArrayList<>();

    int top = -1;

    public void push(E element) throws RuntimeException{

        top = top + 1;
        Stack.add(top, element);

    }

    public void pop() throws RuntimeException{
    }

    public ArrayList<E> peek() throws RuntimeException{
        return peek();
    }

    public int size(){
        return 5;
    }

    public boolean isEmpty(){
        return true;
    }

}
