package Labs.Lab11.project;

public class ProjectManager {

    public static void main(String[] args){

        Project project = new Project(50000, 50);

        project.addEmployee(new Programmer("Sharjeel", "26932"));
        project.addEmployee(new Programmer("Sharjeel", "26932"));
        project.addEmployee(new Programmer("Sharjeel", "26932"));
        project.addEmployee(new Tester("Shahzain", "26937"));
        project.addEmployee(new Tester("Shahzain", "26937"));
        project.addEmployee(new Tester("Hussain", "23483"));
        project.addEmployee(new Tester("Hussain", "23483"));

        System.out.println(project.doProject());

    }

}
