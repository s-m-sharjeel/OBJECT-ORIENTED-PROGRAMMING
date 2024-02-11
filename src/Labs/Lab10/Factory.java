package Labs.Lab10;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Factory {

    private LinkedList<City> pakistan = new LinkedList<>();
    private LinkedList<City> sindh = new LinkedList<>();
    private LinkedList<City> punjab = new LinkedList<>();
    private LinkedList<City> kpk = new LinkedList<>();
    private LinkedList<City> balochistan = new LinkedList<>();
    private LinkedList<City> kashmir = new LinkedList<>();
    private LinkedList<City> gilgit = new LinkedList<>();

    public Factory(){

        setUpFactory();

    }

    private void setUpFactory(){

        try{

            File file = new File("./src/Lab10/pk.csv");
            Scanner input = new Scanner(file);

            input.nextLine();

            while(input.hasNextLine()){

                String line = input.nextLine();
                String[] data = line.split(",");

                City city = (new City(data[0], Float.parseFloat(data[1]), Float.parseFloat(data[2]), data[3], data[4], data[5]));

                switch (data[5]) {
                    case "Sindh" -> sindh.add(city);
                    case "Punjab" -> punjab.add(city);
                    case "Khyber Pakhtunkhwa" -> kpk.add(city);
                    case "Balochistan" -> balochistan.add(city);
                    case "Azad Kashmir" -> kashmir.add(city);
                    case "Gilgit-Baltistan" -> gilgit.add(city);
                }

                pakistan.add(city);

            }

        } catch (Exception e){
            System.out.println("File not found!");
            e.printStackTrace();
        }

    }

    public City getCity(String admin_name){

        admin_name = admin_name.toLowerCase();

        City city = null;

        switch (admin_name) {
            case "sindh" -> city = sindh.pop();
            case "punjab" -> city = punjab.pop();
            case "khyber pakhtunkhwa" -> city = kpk.pop();
            case "balochistan" -> city = balochistan.pop();
            case "azad kashmir" -> city = kashmir.pop();
            case "gilgit-baltistan" -> city = gilgit.pop();
        }

        pakistan.removeFirstOccurrence(city);

        return city;

    }

    public LinkedList<City> getCountry(){

        return pakistan;

    }

}
