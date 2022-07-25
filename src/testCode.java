public class testCode {
    public static double averageNumber(int n){
        int sum = 0;
        for(int i=0;i<n;i++){
            sum = sum + i;
        }
        return sum / (double) n;
    }

    public static void main(String[] args){
        System.out.println(averageNumber(25));
    }
}
