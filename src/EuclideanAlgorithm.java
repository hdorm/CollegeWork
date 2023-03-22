public class EuclideanAlgorithm {

    public static int EA(int a, int b){

        // establishes variables
        int gcd;
        int largerNumber = 0;
        int smallerNumber = 0;
        int rmd = 1;
        int prevrmd = 0;
        boolean equalTo = false;

        // finds which number is the largest or if they are equal
        if(a == b){
            equalTo = true;
        } else if (a > b) {
            largerNumber = a;
            smallerNumber = b;
        }
        else{
            largerNumber = b;
            smallerNumber = a;
        }

        // sets gcd equal to one of the two numbers if they are equal
        if(equalTo){
            gcd = a;
        } else if (a == 0 || b == 0) {
            System.out.println("Output is zero but result is actually undefined.");
            gcd = 0;
        }
        // runs the calculations unless the rmd starts off as zero as then the smaller number is the gcd
        else{
            if((largerNumber % smallerNumber) == 0){
                gcd = smallerNumber;
                // runs a while loop until the remainder is zero
                // uses the previous remainder as the gcd once the remainder is zero as that is the gcd
                // changes the largest and smallest number in the calculation after each loop
            } else {
                while (rmd != 0) {
                    prevrmd = rmd;
                    rmd = largerNumber % smallerNumber;
                    largerNumber = smallerNumber;
                    smallerNumber = rmd;
                }
                gcd = prevrmd;
            }
        }
        return gcd;
    }
    // main method which runs test variables
    public static void main(String[] args){
        System.out.println(EA(25, 100));
        System.out.println(EA(26, 100));
        System.out.println(EA(100, 26));
        System.out.println(EA(1701, 3768));
        System.out.println(EA(10, 45));
        System.out.println(EA(710, 310));
        System.out.println(EA(310, 710));
    }
}
