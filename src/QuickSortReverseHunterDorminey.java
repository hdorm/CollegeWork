// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment Sorting #5
// IDE: 		    IntelliJ, JDK 18.0.1

import java.util.*;

public class QuickSortReverseHunterDorminey {

    public static void recursivePartitionPass(int[] list, int left, int right) {
        if (left < right) {
            int partitionLocation;
            int pivot = list[left];
            int low = left + 1;
            int high = right;
            while (high > low) {
                while (low <= high && list[low] >= pivot)
                    low++;
                while (low <= high && list[high] < pivot)
                    high--;
                if (high > low) {
                    int temp = list[high];
                    list[high] = list[low];
                    list[low] = temp;
                }
            }
            while (high > left && list[high] <= pivot)
                high--;
            if (pivot < list[high]) {
                list[left] = list[high];
                list[high] = pivot;
                partitionLocation = high;
            } else {
                partitionLocation = left;
            }
            recursivePartitionPass(list, left, partitionLocation - 1);
            recursivePartitionPass(list, partitionLocation + 1, right);
        }
    }

    public static void main(String[] args){
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
            System.out.print("Inputs array before sorting (quick): ");
            for (int i : intArray) {
                System.out.print(i + " ");
            }
            System.out.println();
            recursivePartitionPass(intArray, 0, intArray.length - 1);
            System.out.print("Inputs array after sorting (quick): ");
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
