package Final_Exam.question5;

public class Date {

    private int year;
    private int month;
    private int day;

    public Date(int day, int month, int year) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(String date){

        if (!date.equals("null")) {

            String[] temp = date.split("/");

            this.day = Integer.parseInt(temp[0]);
            this.month = Integer.parseInt(temp[1]);
            this.year = Integer.parseInt(temp[2]);
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void display(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
