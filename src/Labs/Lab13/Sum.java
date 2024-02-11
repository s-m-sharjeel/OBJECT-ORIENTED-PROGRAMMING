package Labs.Lab13;

import java.util.Scanner;

public class Sum {

    static class CustomException extends Exception{

        public CustomException(String message) {
            super(message);
        }

    }

    public static void main(String[] args) throws Exception {

        int answer = 0;

        try {

            Scanner input = new Scanner(System.in);
            System.out.print("num: ");
            int num = input.nextInt();

            if (num < 0 || num > 999){

                throw new CustomException("number has more than 3 digits!");

            } else {

                int temp = num;
                while (temp > 0){
                    answer += temp % 10;
                    temp /= 10;
                }
                System.out.println("answer: " + answer);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
