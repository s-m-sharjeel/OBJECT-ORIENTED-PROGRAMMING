package Labs.Lab13;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Array {

    public static void main(String[] args) {

        int[] array = new int[5];
        int index = 0;
        int value = 0;

        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*1000);
        }
        display(array);

        Scanner input = new Scanner(System.in);

        try {

            System.out.print("index: ");
            index = input.nextInt();

            System.out.print("value: ");
            value = input.nextInt();

            array[index] = value;
            display(array);

        } catch (IndexOutOfBoundsException e) {

            int[] newArray = new int[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[newArray.length - 1] = value;
            display(newArray);

        } catch (InputMismatchException e) {

            e.printStackTrace();

        }

    }

    private static void display(int[] array){

        System.out.print("[ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");

    }

}
