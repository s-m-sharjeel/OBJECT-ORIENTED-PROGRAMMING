package Final_Exam.question3;

import java.util.Scanner;

public class VehicleManager {

    public static void main(String[] args) {

        System.out.print("Please enter the type of vehicle: ");

        Scanner input = new Scanner(System.in);
        String type = input.next();

        Vehicle v = VehicleFactory.getVehicle(type);

        if (v == null)
            return;

        v.start();
        v.stop();

    }

}
