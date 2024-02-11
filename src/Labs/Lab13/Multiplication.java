package Labs.Lab13;

import java.util.Scanner;

public class Multiplication {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("number 1: ");
        String num1 = input.next();

        System.out.print("number 2: ");
        String num2 = input.next();

        String answer = "";

        try {

            answer += Integer.parseInt(num1) * Integer.parseInt(num2);

        } catch (Exception e1) {

            if (isNum(num1) ^ isNum(num2)) {

                if (isNum(num1)) {

                    for (int i = 0; i < Integer.parseInt(num1); i++) {
                        answer += num2;
                    }

                } else {

                    for (int i = 0; i < Integer.parseInt(num2); i++) {
                        answer += num1;
                    }

                }

            } else answer = "not possible";

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
