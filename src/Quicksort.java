// Name: Hunter Dorminey
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 3

// Code Section
// Working code goes here

public class Quicksort {

    // Variables necessary for constructor and other classes
    int[] currentArray;
    static int quicksortCount;

    public Quicksort(int[] array) {
        quicksortCount = 0;
        // Clones the array, so it can be used by other sort algorithms
        currentArray = array.clone();
        // Calls actual sorting method
        sorting(currentArray, 0, currentArray.length - 1);
    }

    // Method that does sort using quicksort
    public void sorting(int[] unsortedArray, int left, int right) {
        // Base condition that stops the sort
        if (left < right) {
            // Variables necessary for sort
            int partitionLocation;
            int pivot = unsortedArray[left];
            int low = left + 1;
            int high = right;
            // Loops until the left incrementation meets the right incrementation
            while (low < high) {
                // Loops until the low index is greater than the pivot or low meets high
                while (low <= high && unsortedArray[low] <= pivot) {
                    low++;
                    quicksortCount++;
                }
                // Loops until the high index is less than the pivot or low meets high
                while (low <= high && unsortedArray[high] >= pivot) {
                    high--;
                    quicksortCount++;
                }
                // Swaps high and low in case the above loops now have them mixed up
                if (low < high) {
                    int temp = unsortedArray[high];
                    unsortedArray[high] = unsortedArray[low];
                    unsortedArray[low] = temp;
                }
            }
            // Sets left equal to high if pivot is greater than high as left/pivot should be less than high
            // Also sets high equal to pivot as it should be the higher number compared to left/pivot
            // New partition location is high
            if (pivot > unsortedArray[high]) {
                unsortedArray[left] = unsortedArray[high];
                unsortedArray[high] = pivot;
                partitionLocation = high;
                // Sets partition location as left if pivot is less than or equal to high
            } else {
                partitionLocation = left;
            }
            // Recursively calls itself using new right and left locations
            sorting(unsortedArray, left, partitionLocation - 1);
            sorting(unsortedArray, partitionLocation + 1, right);
        }
    }
}
