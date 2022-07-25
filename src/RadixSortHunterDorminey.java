// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment Sorting #5
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.*;

public class RadixSortHunterDorminey {

    public static int extractDigits(int num, int multiplier) {
        int place = 1;
        place = place * multiplier;
        return num/place%10;
    }

    public static int findDigits(int[] list){
        int i;
        int largest = list[0];
        int digits = 0;
        for(i = 1; i < list.length; i++){
            if(list[i] > largest){
                largest = list[i];
            }
        }
        while(largest != 0){
            largest /= 10;
            ++digits;
        }
        return digits;
    }

    public static void radixSort(int[] list, int left, int right, int timesRan, int currentMultiplier) {
        if (left < right) {
            int index = 0;
            Queue<Integer> zero = new Queue<>();
            Queue<Integer> one = new Queue<>();
            Queue<Integer> two = new Queue<>();
            Queue<Integer> three = new Queue<>();
            Queue<Integer> four = new Queue<>();
            Queue<Integer> five = new Queue<>();
            Queue<Integer> six = new Queue<>();
            Queue<Integer> seven = new Queue<>();
            Queue<Integer> eight = new Queue<>();
            Queue<Integer> nine = new Queue<>();
            while(timesRan < findDigits(list)) {
                while(index < right + 1) {
                    int currentDigit = extractDigits(list[index], currentMultiplier);
                    if (currentDigit == 0) {
                        zero.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 1) {
                        one.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 2) {
                        two.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 3) {
                        three.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 4) {
                        four.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 5) {
                        five.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 6) {
                        six.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 7) {
                        seven.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 8) {
                        eight.enqueue(list[index]);
                        index++;
                    } else if (currentDigit == 9) {
                        nine.enqueue(list[index]);
                        index++;
                    }
                }
                index = 0;
                while (zero.size() > 0) {
                    list[index] = zero.dequeue();
                    index++;
                }
                while (one.size() > 0) {
                    list[index] = one.dequeue();
                    index++;
                }
                while (two.size() > 0) {
                    list[index] = two.dequeue();
                    index++;
                }
                while (three.size() > 0) {
                    list[index] = three.dequeue();
                    index++;
                }
                while (four.size() > 0) {
                    list[index] = four.dequeue();
                    index++;
                }
                while (five.size() > 0) {
                    list[index] = five.dequeue();
                    index++;
                }
                while (six.size() > 0) {
                    list[index] = six.dequeue();
                    index++;
                }
                while (seven.size() > 0) {
                    list[index] = seven.dequeue();
                    index++;
                }
                while (eight.size() > 0) {
                    list[index] = eight.dequeue();
                    index++;
                }
                while (nine.size() > 0) {
                    list[index] = nine.dequeue();
                    index++;
                }
                timesRan++;
                currentMultiplier = currentMultiplier * 10;
                radixSort(list, 0, list.length - 1, timesRan, currentMultiplier);
            }
        }
    }

    public static void main (String[] args){

        int[] intArray;
        int numbersInput;
        String contInput;
        int index = 0;
        boolean cont = true;
        while(cont) {
            Scanner scan = new Scanner(System.in);
            System.out.print("How many integer numbers do you have?: ");
            numbersInput = scan.nextInt();
            intArray = new int[numbersInput];
            System.out.print("Enter " + numbersInput + " integer numbers: ");
            while(scan.hasNextInt()){
                intArray[index] = scan.nextInt();
                if(index == intArray.length - 1){
                    break;
                }
                index++;
            }
            System.out.println("------------------------------------------------------");
            System.out.print("Inputs array before sorting (radix): ");
            for (int i : intArray) {
                System.out.print(i + " ");
            }
            System.out.println();
            radixSort(intArray, 0, intArray.length - 1, 0, 1);
            System.out.print("Inputs array after sorting (radix): ");
            for (int i : intArray) {
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.print("Do you want to continue? (Y/N): ");
            contInput = scan.nextLine();
            while(!contInput.equals("Y") && !contInput.equals("N")){
                System.out.print("Please enter Y or N: ");
                contInput = scan.nextLine();
            }
            if(contInput.equals("N")){
                cont = false;
            }
            index = 0;
        }
    }
}
