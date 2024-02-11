package Labs.Lab13;

import java.util.Scanner;

public class Subtraction {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("number 1: ");
        String num1 = input.next();

        System.out.print("number 2: ");
        String num2 = input.next();

        String answer = "";

        try {

            answer += Integer.parseInt(num1) - Integer.parseInt(num2);

        } catch (Exception e){

            if (isNum(num1) ^ isNum(num2)){

                if (isNum(num1)){

                    // first number is a num and the second is a string
                    answer = "not possible";

                } else {

                    if (Integer.parseInt(num2) <= num1.length())
                        answer = num1.substring(0, num1.length() - Integer.parseInt(num2));
                    else answer = "not possible";

                }

            } else {

                answer = "not possible";

            }

        }

        System.out.println("answer: " + answer);

    }

    private static boolean isNum(String str){

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                return false;
        }

        return true;

    }

}
