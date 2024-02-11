package Labs.Lab06.manager;

import java.io.*;
import java.util.Scanner;

import Labs.Lab06.vehicles.*;

public class VehiclesManager {

    public static void main(String[] args){

        File file = new File("./src/Lab06/cars.txt");
        int noOfCars = 4;
        Car[] cars = new Car[noOfCars];

        try{

            Scanner input = new Scanner(file);

            int i = 0;
            while(input.hasNextLine()){

                cars[i] = new Car();

                String temp;
                String[] data;

                cars[i].setModel(input.nextLine());

                data = input.nextLine().split("\t");
                temp = data[data.length - 1];
                temp = temp.substring(2);
                cars[i].setPrice(Float.parseFloat(temp));

                data = input.nextLine().split("\t");
                temp = data[data.length - 1];
                temp = temp.substring(2);
                cars[i].setPriceRoadworthy(Float.parseFloat(temp));

                data = input.nextLine().split("\t");
                cars[i].setTax( data[data.length - 1] );

                data = input.nextLine().split("\t");
                temp = data[data.length - 1];
                cars[i].setBodyType(temp.split(", "));

                data = input.nextLine().split("\t");
                cars[i].setTransmission( data[data.length - 1] );

                data = input.nextLine().split("\t");
                cars[i].setSeats( data[data.length - 1] );

                data = input.nextLine().split("\t");
                cars[i].setSegment( data[data.length - 1] );

                data = input.nextLine().split("\t");
                cars[i].setIntroduction( data[data.length - 1] );

                data = input.nextLine().split("\t");
                cars[i].setEnd( data[data.length - 1] );

                i++;

                if(input.hasNextLine())
                    input.nextLine();

            }
            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        //int totalCars = 200 + (int)(Math.random()*800);
        int totalCars = 23;
        System.out.println("Total no. of Cars\t\t:\t" + totalCars);

        int totalTrucks = (int)(Math.ceil(totalCars/20.0));

        System.out.println("Total no. of Trucks\t\t:\t" + totalTrucks);

        Ferry ferry = new Ferry();

        System.out.println("\nLoading...");

        /*
        int x = 0;
        for (int i = 0; i < totalTrucks; i++) {
            Truck truck = new Truck();
            System.out.println("\nTruck # " + (i + 1) + ":");
            for (int j = 0; j < 20; j++) {
                int random = (int)(Math.random()*4);
                Car car = new Car( cars[random] );
                truck.load(car);
                System.out.println(car.getModel());
                x++;
                if(x == totalCars)
                    break;
            }
            ferry.load(truck);
        }

         */

        Truck[] trucks = new Truck[totalTrucks];

        int x = 0;
        for (int i = 0; i < totalTrucks; i++) {
            trucks[i] = new Truck();
            System.out.println("\nTruck # " + (i + 1) + ":");
            for (int j = 0; j < 20; j++) {
                int random = (int)(Math.random()*4);
                Car car = new Car( cars[random] );
                trucks[i].load(car);
                System.out.println(car.getModel());
                x++;
                if(x == totalCars)
                    break;
            }
        }

        for (int i = 0; i < totalTrucks; i++) {
            ferry.load(trucks[i]);
        }

        System.out.println("\nLoaded!");



        System.out.println("\nUnloading...");

        /*
        for (int i = 0; i < ferry.getLength(); i++) {
            Truck truck = ferry.unloadTruck();
            System.out.println("\nTruck # " + (i+1));
            for (int j = 0; j < truck.getLength(); j++) {
                System.out.println(truck.unload().getModel());

            }
        }

         */

        trucks = ferry.unload();

        for (int i = 0; i < ferry.getLength(); i++) {

            System.out.println("\nTruck # " + (i+1));

            for (int j = 0; j < trucks[i].getLength(); j++) {
                System.out.println(trucks[i].unload().getModel());
            }

        }

        System.out.println("\nUnloaded!");

    }
}

