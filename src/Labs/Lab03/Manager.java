package Labs.Lab03;

public class Manager {

    public static void main(String[] args){

        String[] first_names = {"Courtouis", "Militao", "Rudiger", "Camavinga", "Kroos", "Modric", "Fede", "Rodrygo", "Vinicius", "Benzema"};
        String[] second_names = {"Stegen", "Ferran", "Pique", "Araujo", "Busquets", "Pedri", "Gavi", "Fati", "Lewandowski", "Raphinha"};

        int noOfStudents = 20;
        Student[] students = new Student[noOfStudents];

        for (int i = 0; i < noOfStudents; i++) {

            students[i] = new Student(randomName(first_names), randomName(second_names));

            System.out.println(students[i].GetDetails());

        }

        System.out.println("Best Student: \n\n" + bestStudent(students));

    }

    public static String randomName(String[] arr){

        return arr[(int)(Math.random()*arr.length)];

    }

    public static String bestStudent(Student[] students){

        int bestScore = 0;
        int bestStudent = 0;

        int i;

        for (i = 0; i < students.length; i++) {

            if(students[i].isPass())

                if(students[i].getTotalGrades()>bestScore)
                    bestStudent = i;

        }

        return students[bestStudent].GetDetails();

    }

}
