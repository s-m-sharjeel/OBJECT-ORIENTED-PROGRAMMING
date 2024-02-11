package Final_Exam.question5;

public class Patient {

    private String name;
    private float age;
    private String disease;
    private Date admission;
    private Date discharge;

    public Patient(String name, float age, String disease, Date admission) {
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.admission = admission;
    }

    public Patient(String name, float age, String disease, Date admission, Date discharge) {
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.admission = admission;
        this.discharge = discharge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setAdmission(Date admission) {
        this.admission = admission;
    }

    public void setDischarge(Date discharge) {
        this.discharge = discharge;
    }

    public float getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getDisease() {
        return disease;
    }

    public Date getAdmission() {
        return admission;
    }

    public Date getDischarge() {
        return discharge;
    }

    public float getLengthOfStay(){

        float totalDays = 0;

        float years = discharge.getYear() - admission.getYear();
        totalDays += years * 365;

        float months = discharge.getMonth() - admission.getMonth();
        totalDays += months * 30;

        float days = discharge.getDay() - admission.getDay();
        totalDays += days;

        return totalDays;

    }

    /**
     * prints (displays) the details
     */
    public void display(){
        System.out.println(this);
    }

    /**
     * returns the details as String
     */
    @Override
    public String toString() {
        return name + " " + age + " " + disease + " " + admission + " " + discharge;
    }
}
