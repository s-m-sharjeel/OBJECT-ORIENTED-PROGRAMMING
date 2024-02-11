package Final_Exam.question2;

public class Cube extends Cuboid{

    public Cube(float length) {
        super(length, length, length);
    }

    @Override
    public String getShapeType() {
        return "Cube";
    }

    @Override
    public String toString() {
        return "Cube{" +
                "length=" + getLength() +
                '}';
    }
}
