package Labs.Lab06.vehicles;

public class Ferry {

    private Queue queueOfTrucks;
    private String location;

    public Ferry(){
        queueOfTrucks = new Queue();
    }

    public void load(Truck truck) {

        queueOfTrucks.load(truck);

    }

    /**
     * unloads a single truck (FIFO)
     * @return
     */
    private Truck unloadTruck(){

        return queueOfTrucks.unloadTruck();

    }

    /**
     * unloads all the trucks (FIFO)
     * @return
     */
    public Truck[] unload(){

        return queueOfTrucks.unload();

    }

    public int getLength(){
        return queueOfTrucks.getLength();
    }

}

