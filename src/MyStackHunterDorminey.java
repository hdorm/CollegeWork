// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 3
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.ArrayList;
import java.util.List;

public class MyStackHunterDorminey<E> {

    public List<E> stack = new ArrayList<>();

    int top = -1;

    public void push(E element) throws RuntimeException{

        top = top + 1;
        stack.add(element);

    }

    public E pop() throws RuntimeException{

        int pop = top;
        top = top - 1;
        return stack.get(pop);

    }

    public E peek() throws RuntimeException{
        return stack.get(top);
    }

    public int size(){
        return 5;
    }

    public boolean isEmpty(){
        return stack.size() == 0;
    }

}

