import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static class hashTableData{
        String word;
        int occurrences;

        public hashTableData(String a, int b){
            word = a;
            occurrences = b;
        }
    }

    public static void main(String[] args) {
        LinkedList<hashTableData> hashTable = new LinkedList<>();
        boolean noDuplicate = true;
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        String lowercase = userInput.toLowerCase();
        String[] unparsedData = lowercase.split("\\s+");
        for (String unparsedDatum : unparsedData) {
            if(hashTable.size() == 0){
                hashTable.add(0, new hashTableData(unparsedDatum, 0));
            }
            for (int j = 0; j < hashTable.size(); j++) {
                if (unparsedDatum.equals(hashTable.get(j).word)) {
                    hashTable.set(j, new hashTableData(unparsedDatum, hashTable.get(j).occurrences + 1));
                    noDuplicate = false;
                }
            }
            if (noDuplicate) {
                hashTable.add(new hashTableData(unparsedDatum, 0));
            }
        }
        for (Main.hashTableData hashTableData : hashTable) {
            System.out.println(hashTableData.word);
            System.out.println(hashTableData.occurrences);
        }
    }
}