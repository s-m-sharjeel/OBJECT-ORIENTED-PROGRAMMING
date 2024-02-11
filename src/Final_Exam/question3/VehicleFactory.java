package Final_Exam.question3;

public class VehicleFactory {

    public static Vehicle getVehicle(String type){

        Vehicle v = null;

        type = type.toLowerCase();

        if (type.equals("car"))
            v = new Car();
        else if (type.equals("motorcycle"))
            v = new Motorcycle();
        else {
            System.out.println("Invalid type entered!");
        }

        return v;

    }

}
