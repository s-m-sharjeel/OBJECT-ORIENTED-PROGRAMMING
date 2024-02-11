package Final_Exam.question1;

import java.util.Scanner;

public class InvalidInputException extends Exception{

    public InvalidInputException(String message) {
        super(message);
    }

    private static int validateInput(int n){

        try{
            if (n <= 0){
                throw new InvalidInputException("Please enter a number greater than zero!");
            }

        } catch (InvalidInputException e){
            e.printStackTrace();
        }
        return n;

    }

    public static void main(String[] args) {

        System.out.print("Please enter a number: ");
        int n = 0;

        try{
            Scanner input = new Scanner(System.in);
            n = input.nextInt();
            System.out.println(validateInput(n));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
