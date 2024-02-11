package Final_Exam.question4;

public class Child {

    public Child(char gender, String lastName, String firstName, String status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.status = status;
        this.gender = gender;
    }

    private String lastName;
    private String firstName;
    private String status;
    private char gender;
    private String gift;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStatus() {
        return status;
    }

    public char getGender() {
        return gender;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + " " + gift;
    }
}
