package Labs.Lab14.problem3;

import java.util.LinkedList;
import java.util.Random;

public class TransportCompany {

    private LinkedList<Car> cars = new LinkedList<>();
    private LinkedList<Bike> bikes = new LinkedList<>();
    private LinkedList<Helicopter> helicopters = new LinkedList<>();
    private LinkedList<Driver> drivers = new LinkedList<>();
    private LinkedList<Rider> riders = new LinkedList<>();
    private LinkedList<Pilot> pilots = new LinkedList<>();

    public TransportCompany() {

        Random random= new Random();
        int r = random.nextInt(20);
        for (int i = 0; i < r; i++) {
            cars.add(new Car(34));
            bikes.add(new Bike(3));
            helicopters.add(new Helicopter(55));
            drivers.add(new Driver());
            riders.add(new Rider());
            pilots.add(new Pilot());
        }

    }

    public Vehicle request(int passengers){

        if (passengers > 4){

            // helicopter
            if (helicopters.isEmpty()){
                System.out.println("no helicopters available!");
                return null;
            }

            Helicopter h = helicopters.pop();

            if (!pilots.isEmpty()){
                h.setOperator(pilots.pop());
                return h;

            }

            System.out.println("no pilots available!");


        } else if (passengers > 1){

            // car
            if (cars.isEmpty()){
                System.out.println("no cars available!");
                return null;
            }

            Car c = cars.pop();

            if (!drivers.isEmpty()){
                c.setOperator(drivers.pop());
                return c;
            }

            if (!riders.isEmpty()){
                c.setOperator(riders.pop());
                return c;
            }

            System.out.println("no drivers or riders available!");


        } else if (passengers == 1){

            // bike
            if (bikes.isEmpty()){
                System.out.println("no bikes available!");
                return null;
            }

            Bike b = bikes.pop();

            if (!drivers.isEmpty()){
                b.setOperator(drivers.pop());
                return b;
            }

            if (!riders.isEmpty()){
                b.setOperator(riders.pop());
                return b;
            }

            System.out.println("no drivers or riders available!");

        } else {

            System.out.println("there are no passengers!");

        }

        return null;

    }

}
