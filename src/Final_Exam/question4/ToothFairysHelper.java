package Final_Exam.question4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ToothFairysHelper {

    LinkedList<Child> children = new LinkedList<>();
    private int candyCount = 0;
    private int necklaceCount = 0;
    private int watchCount = 0;

    public void readFile() {

        try {
            File file = new File("./src/Final_Exam/question4/ToothFairyList.txt");
            Scanner input = new Scanner(file);

            String data;
            String[] dataArray;
            while (input.hasNext()) {

                data = input.nextLine();
                dataArray = data.split(" ");
                Child child = new Child(dataArray[0].charAt(0), dataArray[1], dataArray[2], dataArray[3]);
                children.add(child);

            }
        } catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }

    }

    public void assignGifts(){

        for (Child c : children) {

            if (c.getStatus().equals("Bad")) {
                c.setGift("Candy");
                candyCount++;
            } else {

                if (c.getGender() == 'F'){
                    c.setGift("Necklace");
                    necklaceCount++;
                } else {
                    c.setGift("Watch");
                    watchCount++;
                }

            }

        }

    }

    public void writeToFile() {


        try {
            File file = new File("./src/Final_Exam/question4/ToothFairyShoppingList.txt");
            FileWriter writer = new FileWriter(file);

            String data = "";
            for (Child c : children) {
                data += c.toString() + '\n';
            }

            data += "Candies: " + candyCount + '\n';
            data += "Necklaces: " + necklaceCount + '\n';
            data += "Watches: " + watchCount + '\n';

            writer.write(data);
            writer.close();

        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
