package Final_Exam.question5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Hospital {

    LinkedList<Patient> patients = new LinkedList<>();

    /**
     * collects patient info, creates an object, and adds them to the database;
     * @param name
     * @param age
     * @param disease
     * @param admission
     */
    public void addPatient(String name, float age, String disease, Date admission){

        Patient patient = new Patient(name, age, disease, admission);
        patients.add(patient);

        writeToFile();

    }

    public void deletePatient(Patient patient){

        readFile();

        if (!patients.contains(patient)){
            System.out.println("No such patient found!");
            return;
        }

        patients.remove(patient);

        writeToFile();

    }

    private void writeToFile(){

        try {
            File file = new File("./src/Final_Exam/question5/database.txt");
            FileWriter writer = new FileWriter(file);

            String data = "";
            for (Patient p : patients) {
                data += p.toString() + '\n';
            }

            writer.write(data);
            writer.close();

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void readFile(){

        patients.clear();

        try {
            File file = new File("./src/Final_Exam/question5/database.txt");
            Scanner input = new Scanner(file);

            String data;
            String[] dataArray;
            while (input.hasNext()) {

                data = input.nextLine();
                dataArray = data.split(" ");
                Patient patient = new Patient(dataArray[0], Float.parseFloat(dataArray[1]), dataArray[2], new Date(dataArray[3]), new Date(dataArray[4]));
                patients.add(patient);

            }
        } catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }

    }

    public void displayAllPatients(){

        readFile();

        System.out.println("All patients:");
        for (Patient p : patients) {
            System.out.println(p + "\n");
        }

    }

    public void displayPatientsBelow12(){

        readFile();

        System.out.println("Patients below 12:");
        for (Patient p : patients) {
            if (p.getAge() < 12)
                System.out.println(p + "\n");
        }

    }

    public Patient searchByName(String name){

        readFile();

        for (Patient p: patients) {
            if (p.getName().equals(name)){
                System.out.println(p);
                return p;
            }
        }

        System.out.println("No such patient found!");
        return null;

    }

    public Patient searchByAdmissionDate(Date date){

        readFile();

        for (Patient p: patients) {
            if (p.getAdmission().equals(date)){
                System.out.println(p);
                return p;
            }
        }

        System.out.println("No such patient found!");
        return null;

    }

    public float avgLengthOfStay(){

        readFile();

        float total = 0;
        for (Patient p : patients) {
            total += p.getLengthOfStay();
        }

        return total/(patients.size());

    }

    public void frequencyOfDiseases(){

        readFile();

        LinkedList<String> diseases = new LinkedList<>();
        LinkedList<String> report = new LinkedList<>();

        for (Patient p: patients) {
            diseases.add(p.getDisease());
        }

        while(!diseases.isEmpty()){

            String disease = diseases.get(0);
            report.add(disease);

            int frequency = 0;
            while (diseases.contains(disease)){
                diseases.remove(disease);
                frequency++;
            }

            report.set(report.size() - 1, report.getLast() + " " + frequency);

        }

        System.out.println(report);

    }

    public String generateReport(Patient p){

        return p.toString();

    }

}
