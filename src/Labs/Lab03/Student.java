package Labs.Lab03;

public class Student {

    String firstName;
    String lastName;
    private int totalGrades = 0;
    private boolean pass = true;
     private String[] courses = {"Math", "", "Science", "", "English", "", "Urdu", "", "Chinese", ""};

    public Student(String firstName, String lastName){

        this.firstName = firstName;
        this.lastName = lastName;

        for (int i = 1; i < courses.length; i+=2) {

            int grade = randomGrade();
            courses[i] += grade;
            totalGrades += grade;

            if(grade<60)
                pass = false;

        }

    }

    private int randomGrade(){

        return 40 + (int)(Math.random()*61);

    }

    private String CalculateGrade(){

        String grades = "";

        for (int i = 0; i < courses.length; i++) {

            if(i%2==0){

                grades += courses[i] + ": ";

            }else{

                grades += courses[i] + " ";

                int grade = Integer.parseInt(courses[i]);

                if(grade>=90)
                    grades += " A\n";
                else if(grade>=80)
                    grades += " B\n";
                else if(grade>=70)
                    grades += " C\n";
                else if(grade>= 60)
                    grades += " D\n";
                else
                    grades += " F\n";

            }

        }

        return grades;

    }

    public String GetDetails(){

        return "First name: " + firstName + "\nLast name: " + lastName + "\n\n" + CalculateGrade();

    }

    public int getTotalGrades(){

        return totalGrades;

    }

    public boolean isPass(){

        return pass;

    }

}
