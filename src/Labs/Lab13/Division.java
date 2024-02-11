package Labs.Lab13;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Division {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int answer;
        int num1 = 0;
        int num2 = 0;
        boolean flag = false;

        try {

            System.out.print("number 1: ");
            num1 = input.nextInt();

        } catch (InputMismatchException e) {

            e.printStackTrace();

        }

        do {

            try {

                System.out.print("number 2: ");
                num2 = input.nextInt();

                answer = num1 / num2;

                System.out.println("answer: " + answer);
                flag = true;

            } catch (ArithmeticException e) {

                System.out.println("enter a non-zero number!");

            }

        } while (!flag);

    }

}
