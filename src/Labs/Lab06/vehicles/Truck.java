package Labs.Lab06.vehicles;

public class Truck {

    private Stack stackOfCars;

    public Truck(){
        stackOfCars = new Stack();
    }

    public void load(Car car){
        stackOfCars.load(car);
    }

    public Car unload(){
        return stackOfCars.unload();
    }

    public int getLength(){
        return stackOfCars.getLength();
    }

}

