// Name: Hunter Dorminey
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 4
// IDE: IntelliJ

// Algorithm Design Block
// Analysis and discussion details go in this block

// Test Results 1:
// Input: shun chary disconcert lien auxiliary engrossing noisome truce odor pluck vex ineffable gossamer forward malign riddle hospitable bellicose quotidian whimsical unscathed justify kerosene xylophone yacht zoo
// Inputs size: 26 words
// Number of comparisons: 26

// This list contains words that all start with different letters. It makes sense that it would only
// take 26 comparisons, as the program should only have to check whether the specific list associated
// with that letter is empty.

//----------------------------------------------------------------------------------------------------------------------

// Test Results 2:
// Input: Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello Hello
// Inputs size: 26 words
// Number of comparisons: 26

// This list contains 26 elements of the same word. It is similar to the above list as the program
// should only have to check the first element in the list after passing the word through the hash
// function to find that it matches.

//----------------------------------------------------------------------------------------------------------------------

// Test Results 3:
// Input: ability able about above accept according account across act action activity actually add address administration admit adult affect after again against age agency agent ago apple
// Inputs size: 26 words
// Number of comparisons: 326

// This list contains many unique words beginning with "a" and took substantially more comparisons
// than the other two. I'm not sure if the efficiency lines up with theoretical numbers.
// The number of comparisons are greater than nlog(n), but less than n^2. The number of
// comparisons lines up when looking at the program, as it has to check through several items
// in the list given by the hashing function before determining whether there is a duplicate, and
// it has to do that for every item in the list after the first, with each iteration taking more
// comparisons.

//----------------------------------------------------------------------------------------------------------------------

// Test Results 4:
// Input: grave animosity expedient skiff derision frugal relapse ascetic recitals plod cadge tepid soar precursory dawdler ambidextrous precarious ineluctable forage impervious perpetrate salubrious ineptitude preclude fecund active
// Inputs size: 26 words
// Number of comparisons: 38

// This list contains some words that contain a unique first letter and some that are the same.
// The actual efficiency is pretty similar to the theoretical efficiency of n with it being a fair bit
// less than half of twice the input size. This should be about right as the input can sometimes place a word
// in one comparison when the list is empty but sometimes has to iterate through a few words that
// begin with the same letter.

//----------------------------------------------------------------------------------------------------------------------

// Test Results 5:
// Input: One foot in front of the other, One more step, and then one more. Jack's only thoughts were to keep moving no matter how much his body screamed to stop and rest. He's lost almost all his energy and his entire body ached beyond belief, but he forced himself to take another step. Then another. And then one more.  Sometimes there isn't a good answer. No matter how you try to rationalize the outcome, it doesn't make sense. And instead of an answer, you are simply left with a question. Why?  Devon couldn't figure out the color of her eyes. He initially would have guessed that they were green, but the more he looked at them he almost wanted to say they were a golden yellow. Then there were the flashes of red and orange that seemed to be streaked throughout them. It was almost as if her eyes were made of opal with the sun constantly glinting off of them and bringing out more color. They were definitely the most unusual pair of eyes he'd ever seen. Sleeping in his car was never the plan but sometimes things don't work out as planned. This had been his life for the last three months and he was just beginning to get used to it. He didn't actually enjoy it, but he had accepted it and come to terms with it. Or at least he thought he had. All that changed when he put the key into the ignition, turned it and the engine didn't make a sound. Indescribable oppression, which seemed to generate in some unfamiliar part of her consciousness, filled her whole being with a vague anguish. It was like a shadow, like a mist passing across her soul's summer day. It was strange and unfamiliar; it was a mood. She did not sit there inwardly upbraiding her husband, lamenting at Fate, which had directed her footsteps to the path which they had taken. She was just having a good cry all to herself. The mosquitoes made merry over her, biting her firm, round arms and nipping at her bare insteps.
// Inputs size: 352 words
// Number of comparisons: 2448

// This list contains a natural paragraph with lots of duplicate words and lots of unique words.
// It has the largest number of inputs and therefore the largest number of comparisons out of
// all the tests. The efficiency is less than n but greater than nlog(n). It does not line up exactly
// with the theoretical efficiency in the slides, but it is somewhat close in that the number of
// comparisons does not completely balloon like an exponential efficiency. The number of comparisons
// are what I would expect in that they are similar to test four which should be somewhat comparable
// since they both contain a mix of unique and duplicate words. It has the same job of sometimes
// adding a word to the list in one try and other times needing to iterate over several words that
// begin with the same letter.

//----------------------------------------------------------------------------------------------------------------------

// The number of comparisons lines up in some cases but does not line up perfectly in others.
// I think this could be because the implementation only has 26 spaces available in the hash table
// , and the hashing function does not distribute the words uniformly when they are in a paragraph
// where some words are used more frequently than others.

// Code Section
// Working code goes here

import java.util.*;

public class testHashing {
    // Establishes global variables
    static LinkedList<LinkedList<hashTableData>> hashTable = new LinkedList<>();
    static int comparisons;

    // Creates class used for the nodes of the hash table
    static class hashTableData {
        String word;
        int occurrences;

        // Class object that is stored in the linked lists
        public hashTableData(String a, int b) {
            word = a;
            occurrences = b;
        }
    }

    // Creates the hash table by populating it with 26 linked lists using a for loop
    public static void createHashTable() {
        for (int i = 0; i < 26; i++) {
            hashTable.add(i, new LinkedList<>());
        }
    }

    // Makes the input lowercase, removes the unnecessary bits, and adds the
    // split words to a string list
    public static void wordSeparate(String needsDelimiting) {
        String makeLowercase = needsDelimiting.toLowerCase();
        String removeAll = makeLowercase.replaceAll("[.]|,|[?]|;|!", "");
        String[] processedArray = removeAll.split("\\s+");
        // Calls the hashing method on the string array
        hashing(processedArray);
    }

    // Method that actually goes through the string array and stores the values in the hash table
    public static void hashing(String[] delimitedArray) {
        // For loop which iterates through the elements in the string array
        for (String s : delimitedArray) {
            // Gets the char of the beginning of the current word
            char firstLetter = s.charAt(0);
            // Boolean which keeps track of whether a duplicate value has been found in the current index's linked list
            boolean noDuplicate = true;
            // Gets the ascii value of the first letter of the current word and subtracts 97 from it to
            // normalize it to the hash table index values
            int hashTableIndex = (int) firstLetter - 97;
            // Adds the current string value to the currently selected index's linked list if the list is
            // completely empty
            if (hashTable.get(hashTableIndex).isEmpty()) {
                comparisons++;
                // Adds the current string to the list and sets the occurrence of the word to 1
                hashTable.get(hashTableIndex).add(new hashTableData(s, 1));
            } else {
                // If the current list is not empty, this iterates through the list and checks for duplicates
                // It increments the number of occurrences of the current word if a duplicate is found
                for (int k = 0; k < hashTable.get(hashTableIndex).size(); k++) {
                    comparisons++;
                    if (hashTable.get(hashTableIndex).get(k).word.equals(s)) {
                        int currentOccurrence = hashTable.get(hashTableIndex).get(k).occurrences;
                        hashTable.get(hashTableIndex).set(k, new hashTableData(s, currentOccurrence + 1));
                        // Duplicate is found, so this boolean is now false
                        noDuplicate = false;
                    }
                }
                // Adds the current value to the list if no duplicate is found and sets the number of occurrences to 1
                if (noDuplicate) {
                    hashTable.get(hashTableIndex).add(new hashTableData(s, 1));
                }
            }
        }
    }

    // Method which shows the user a menu and follows their input whether the program should populate the arrays, run the sort algorithms, display the results, or exit the program
    public static void runTest() {
        // Sets up variables needed for the test method
        int numbersInput;
        String userInput = null;
        boolean cont = true;
        // Initializes scanners to receive user input
        Scanner scanOne = new Scanner(System.in);
        Scanner scanTwo = new Scanner(System.in);
        // While loop which keeps the main menu running until the user chooses to exit the program
        while (cont) {
            System.out.println("---------------MAIN MENU---------------");
            System.out.println("1. Read input text");
            System.out.println("2. Hash Input Text to Hash Table");
            System.out.println("3. Display Words and Occurrences");
            System.out.println("4. Display Efficiency Outputs");
            System.out.println("5. Exit program");
            System.out.println();
            System.out.print("Enter option number: ");
            numbersInput = scanOne.nextInt();
            // Takes in and stores the user input
            if (numbersInput == 1) {
                System.out.println();
                System.out.print("Enter text for hashing: ");
                userInput = scanTwo.nextLine();
                System.out.println();
                // Clears comparisons and hash tables for new input, creates new hash table for the new input
                // , and calls the word separate function on the user input which then calls the hashing method
            } else if (numbersInput == 2) {
                System.out.println();
                if (userInput != null) {
                    comparisons = 0;
                    hashTable.clear();
                    createHashTable();
                    wordSeparate(userInput);
                } else {
                    System.out.println("Please choose option 1 and input text first.");
                    System.out.println();
                }
                // Prints the words in the hash table and their occurrences
            } else if (numbersInput == 3) {
                System.out.println();
                if (userInput != null) {
                    System.out.println("Key Word   Word Count");
                    System.out.println("-----------------------");
                    for (LinkedList<testHashing.hashTableData> hashTableData : hashTable) {
                        for (testHashing.hashTableData hashTableDatum : hashTableData) {
                            System.out.printf("%-13s %-2d\n", hashTableDatum.word, hashTableDatum.occurrences);
                        }
                    }
                } else {
                    System.out.println("Please choose option 1 and input text first.");
                }
                System.out.println();
                // Prints the user input, input size, and number of comparisons
            } else if (numbersInput == 4) {
                System.out.println();
                int inputSize = 0;
                if (userInput != null) {
                    System.out.println("Test Results:");
                    for (LinkedList<testHashing.hashTableData> hashTableData : hashTable) {
                        for (testHashing.hashTableData hashTableDatum : hashTableData) {
                            inputSize += hashTableDatum.occurrences;
                        }
                    }
                    System.out.print("Input: ");
                    System.out.println(userInput);
                    System.out.print("Inputs size: ");
                    System.out.println(inputSize + " words");
                    System.out.print("Number of comparisons: ");
                    System.out.println(comparisons);
                } else {
                    System.out.println("Please choose option 1 and input text first.");
                }
                System.out.println();
                // Ends the while loop, and subsequently the program
            } else if (numbersInput == 5) {
                cont = false;
            }
        }
    }

    // Main method which only calls the runTest method
    public static void main(String[] args) {
        runTest();
    }
}
