// Name: Hunter Dorminey
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 3

// Code Section
// Working code goes here

public class Heapsort {

    // Variables necessary for constructor and other classes
    int[] currentArray;
    static int heapsortCount;

    // Constructor needed to call class from another class
    public Heapsort(int[] array) {
        heapsortCount = 0;
        // Clones the array, so it can be used by other sort algorithms
        currentArray = array.clone();
        // Calls actual sorting method
        sorting(currentArray);
    }

    // Method that does the actual sorting
    public void sorting(int[] unsortedArray) {
        // Sets the array length as a variable, so it can be manipulated
        int arrayLength = unsortedArray.length;
        // Turns the original array into a heap using the create heap method
        for (int i = (arrayLength / 2) - 1; i >= 0; i--) {
            heapsortCount++;
            createHeap(unsortedArray, arrayLength, i);
        }
        // Removes the root node from the heaped array (as it is the largest) and places it at the end of the array and then sends it back to be re-heaped and repeats the process until every element has been sorted
        for (int i = arrayLength - 1; i > 0; i--) {
            heapsortCount++;
            int temp = unsortedArray[0];
            unsortedArray[0] = unsortedArray[i];
            unsortedArray[i] = temp;
            // Calls a smaller array so the end elements are not disrupted
            createHeap(unsortedArray, i, 0);
        }
    }

    void createHeap(int[] unsortedArray, int arrayLength, int rootNode) {
        // Variables necessary for creating heap
        int largestValue = rootNode;
        int right = 2 * rootNode + 2;
        int left = 2 * rootNode + 1;
        // These two if statements set a new largest value if the conditions hold
        if (right < arrayLength && unsortedArray[right] > unsortedArray[largestValue]) {
            largestValue = right;
        }
        if (left < arrayLength && unsortedArray[left] > unsortedArray[largestValue]) {
            largestValue = left;
        }
        // Sets the root node as the largest value that was found if they are not the same and calls the heap creator again to deal with the subtree that is no longer a heap
        if (largestValue != rootNode) {
            heapsortCount++;
            int temp = unsortedArray[rootNode];
            unsortedArray[rootNode] = unsortedArray[largestValue];
            unsortedArray[largestValue] = temp;
            createHeap(unsortedArray, arrayLength, largestValue);
        }
    }
}
