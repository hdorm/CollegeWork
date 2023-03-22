import java.util.Scanner;

public class HunterDormineyTrapezoidArea {

    // this method takes input from the user and calculates the trapezoid area value
    public static void trapezoidArea(){
        // these variables are used to store the values related to the trapezoid
        float baseOne;
        float baseTwo;
        float height;
        // creates a scanner to receive input
        Scanner scan  = new Scanner(System.in);
        // relays to the user what they should be inputting and then stores that value into one of the variables
        System.out.print("Enter the length of base one: ");
        baseOne = scan.nextFloat();
        System.out.print("Enter the length of base two: ");
        baseTwo = scan.nextFloat();
        System.out.print("Enter the height: ");
        height = scan.nextFloat();
        // presents the user with the area of the trapezoid based on the dimensions they input using the formula A+B/2 * C
        System.out.print("The area is: " + ((baseOne + baseTwo) / 2) * height);
        System.out.println();
        // calls the method that lets the user determine whether they want to calculate more areas
        keepGoing(true);
        }

        // this method takes input from the user to determine whether to terminate or run again
        public static void keepGoing(boolean correctInput){
        // variable to store answer
        String answer;
        // creates scanner to take user input
        Scanner scan  = new Scanner(System.in);
        // checks whether the user's last input was an incorrect value
        if(correctInput) {
            System.out.print("Do you wish to continue? (Y/N): ");
        }
        else {
            System.out.print("Please enter Y or N: ");
        }
        // stores user's input
        answer = scan.next();
        // checks whether the user wants to continue, quit, or input an incorrect value
        if (!answer.equals("Y") && !answer.equals("N")){
            keepGoing(false);
        }
        else if(answer.equals("Y")){
            trapezoidArea();
        }
        else{
            System.exit(0);
        }
        }
    // main method which calls the method to calculate trapezoid area
    public static void main(String[] args){
        trapezoidArea();
    }
}
