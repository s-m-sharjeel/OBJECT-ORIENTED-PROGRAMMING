package Labs.Lab11.project;

import java.util.ArrayList;

public class Project {

    private int linesOfCode;
    private int linesOfCodeWritten;
    private int linesOfCodeTested;
    private int duration;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Project(int linesOfCode, int duration) {
        this.linesOfCode = linesOfCode;
        this.duration = duration;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public int doProject(){

        int days = 0;
        while (linesOfCodeTested < linesOfCode || linesOfCodeWritten < linesOfCode){

            for (Employee e : employees) {
                if (e instanceof Programmer) {
                    if (linesOfCodeWritten < linesOfCode)
                        linesOfCodeWritten += e.work();
                } else if (linesOfCodeTested < linesOfCode)
                    linesOfCodeTested += e.work();
            }

            days++;

        }

        int daysGap = days - duration;
        if (daysGap < 0)
            System.out.println("Project completed " + Math.abs(daysGap) + " days early!");
        else if (daysGap > 0)
            System.out.println("Project completed " + Math.abs(daysGap) + " days late!");
        else System.out.println("Project completed just in time!");

        return days;

    }
}
