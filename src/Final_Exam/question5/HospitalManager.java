package Final_Exam.question5;

public class HospitalManager {

    public static void main(String[] args) {

        Hospital hospital = new Hospital();

        hospital.addPatient("Sharjeel", 18, "Cancer", new Date("23/05/2023"));
        hospital.addPatient("Sharjeel", 18, "Cancer", new Date("23/05/2023"));
        hospital.addPatient("Sharjeel", 18, "Cancer", new Date("23/05/2023"));
        hospital.addPatient("Shahzain", 19, "Dengue", new Date("23/04/2023"));
        hospital.addPatient("Hussain", 19, "TB", new Date("23/05/2022"));
        hospital.addPatient("Ali", 11, "Malaria", new Date("21/05/2023"));

        Patient p = hospital.searchByName("Ali");
        hospital.deletePatient(p);

        hospital.displayAllPatients();
        hospital.displayPatientsBelow12();

        hospital.frequencyOfDiseases();

    }

}
