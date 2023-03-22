// Name: Hunter Dorminey
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 2

// Algorithm Design Block
/* Problem Statement:

   We need to create a class which can search for a specified key in an array through the use
   of an approximated location of that value. It needs to be initiated through a constructor which
   has the parameters of the key value and array value.

*/

/* Algorithm Logical Steps:

   Given a sorted array that contains random values which may or may not contain the key value:
   1. Start at index zero and the rightmost index.
   2. Find the values of the current leftmost and rightmost index.
   3. Check that the leftmost value is less than or equal to the rightmost, the key value is not less than the left
   most value, and the key value is not greater than the right most value.
   4. If any of the checks in three fail, the key value is not in the array.
   5. Use the indices, their values, and the key value in the straight line formula to find an approximated location
   for the key.
   6. Check whether the approximated index value is greater than, less than, or equal to the key value
   7. If less than, set the leftmost index as the approximated index plus one. If greater than, set the rightmost index
   as the approximated index minus one. If equal to, return the location of the index.
   8. Repeat from step two until the location of the index is found or the checks in step three fail.

*/

/* Algorithm Pseudocode Syntax:

   class InterpolationSearch{

   public InterpolationSearch constructor (array, key) <- receives input from another class
   {
     array = array
     key = key
     RecursiveMethod(0, size of array minus 1)
   }

   RecursiveMethod(leftMostIndex, rightMostIndex)
   {
     boolean found = false
     find leftmost value from index
     find rightmost value from index

     if(leftmost value is less than or equal to the rightmost && the key value is not less than the leftmost value &&
            the key value is not greater than the right most value)
        {
            approximatedIndex = leftMostIndex + (((key - leftMostIndex) * (rightMostIndex - leftMostIndex))
             / (rightMostIndex - leftMostIndex));
            if(key == approximatedIndex)
            {
                set index location as approximatedIndex
                return found
            }
            else if (key < approximatedIndex)
            {
                RecursiveMethod(leftMostIndex, approximatedIndex - 1)
            }
            else if (key > approximatedIndex)
            {
                RecursiveMethod(approximatedIndex + 1, rightMostIndex)
            }
        }

      else
      {
        return not found
      }

   }

   }

*/

// Implementation Section

import java.util.ArrayList;

// Class to hold the methods and constructor for the interpolation search
public class InterpolationSearch {
    // Variable declarations for use in the search
    int[] currentArray;
    int x, currentKey, leftMost, rightMost;
    boolean Found = false;
    int Index = -1;
    int Divisions = 0;
    static ArrayList<Integer> indices = new ArrayList<>();
    static ArrayList<Integer> keys = new ArrayList<>();
    static ArrayList<Integer> divisionsList = new ArrayList<>();
    static ArrayList<Boolean> founds = new ArrayList<>();

    // Constructor which takes the array and key that were randomly generated for use in the search method
    public InterpolationSearch(int[] array, int key) {
        currentArray = array;
        currentKey = key;
        // Calls the recursive search method from the constructor with arguments pointing to the leftmost and rightmost indices
        RecursiveMethod(0, 1023);
    }

    // Method that can be called from the test class to reset the storage lists for a new set of keys
    public static void reset() {
        keys.clear();
        founds.clear();
        indices.clear();
        divisionsList.clear();
    }

    // Recursive method that actually does the searching for the key value
    public void RecursiveMethod(int leftIndex, int rightIndex) {
        // Finds the values of the left and right indices for use in the straight line formula
        leftMost = currentArray[leftIndex];
        rightMost = currentArray[rightIndex];
        // Checks to make sure conditions are met before continuing as the value is not in the array if they are not met
        if (currentKey >= currentArray[leftIndex] && currentKey <= currentArray[rightIndex] && leftMost <= rightMost) {
            // Straight line formula which is meant to find the approximate location of the key value
            x = leftIndex + (((currentKey - leftMost) * (rightIndex - leftIndex)) / (rightMost - leftMost));
            // Determines that the key has been found and adds the values to different lists for storage
            if (currentArray[x] == currentKey) {
                Found = true;
                keys.add(currentKey);
                founds.add(Found);
                indices.add(x);
                divisionsList.add(Divisions);
                // Determines that the approximated value is greater than the key value, so the number of divisions
                // is incremented and the method calls itself with the approximated value and everything to the right
                // of it eliminated
            } else if (currentArray[x] > currentKey) {
                Divisions++;
                RecursiveMethod(leftIndex, x - 1);
                // Does the same thing as the above else if statement except it eliminates the approximated value
                // and everything to the left of it when calling the method again
            } else if (currentArray[x] < currentKey) {
                Divisions++;
                RecursiveMethod(x + 1, rightIndex);
            }
            // The key value is not in the array if the above conditions are not met, so we add the values to the
            // storage lists with Found set to false and an index of -1
        } else {
            Found = false;
            keys.add(currentKey);
            founds.add(Found);
            indices.add(Index);
            divisionsList.add(Divisions);
        }
    }
}