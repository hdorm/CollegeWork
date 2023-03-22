// Name: Hunter Dorminey
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 3

// Code Section
// Working code goes here

public class Mergesort {

    // Variables necessary for constructor and other classes
    int[] currentArray;
    static int mergesortCount;

    // Constructor needed to call class from another class
    public Mergesort(int[] array) {
        mergesortCount = 0;
        // Clones the array, so it can be used by other sort algorithms
        currentArray = array.clone();
        // Calls actual sorting method
        sorting(currentArray, 0, currentArray.length - 1);
    }

    // Method that splits the arrays until they can not be split further and then calls the merge method to put them back together into a sorted array
    void sorting(int[] unsortedArray, int left, int right) {
        if (left < right) {
            mergesortCount++;
            int middle = left + (right - left) / 2;
            sorting(unsortedArray, left, middle);
            sorting(unsortedArray, middle + 1, right);
            merging(unsortedArray, left, middle, right);
        }
    }

    // Merge method which merges the sub-arrays in a sorted manner
    void merging(int[] unsortedArray, int left, int middle, int right) {
        // Variables necessary for merging the arrays
        int firstArraySize = middle - left + 1;
        int secondArraySize = right - middle;
        int[] firstArray = new int[firstArraySize];
        int[] secondArray = new int[secondArraySize];
        int firstArrayIndex = 0;
        int secondArrayIndex = 0;
        int mergedArrayIndex = left;
        // Uses arraycopy to create sub-arrays using midpoint
        System.arraycopy(unsortedArray, left, firstArray, 0, firstArraySize);
        System.arraycopy(unsortedArray, middle + 1, secondArray, 0, secondArraySize);
        // While loop which runs until either the first or second sub-array indices are equal to their size
        while (firstArrayIndex < firstArraySize && secondArrayIndex < secondArraySize) {
            mergesortCount++;
            // Increments the first sub-array index and sets the merged array index to the first sub-array's index if its index is less than or equal to the second sub-array's index
            if (firstArray[firstArrayIndex] <= secondArray[secondArrayIndex]) {
                unsortedArray[mergedArrayIndex] = firstArray[firstArrayIndex];
                firstArrayIndex++;
                // Does the opposite of the above if statement if it is not met
            } else {
                unsortedArray[mergedArrayIndex] = secondArray[secondArrayIndex];
                secondArrayIndex++;
            }
            // Increments the merged array index
            mergedArrayIndex++;
        }
        // These catch any remaining elements in the two sub-arrays and put them in the proper place in the sorted array
        while (firstArrayIndex < firstArraySize) {
            mergesortCount++;
            unsortedArray[mergedArrayIndex] = firstArray[firstArrayIndex];
            mergedArrayIndex++;
            firstArrayIndex++;
        }
        while (secondArrayIndex < secondArraySize) {
            mergesortCount++;
            unsortedArray[mergedArrayIndex] = secondArray[secondArrayIndex];
            mergedArrayIndex++;
            secondArrayIndex++;
        }
    }
}
