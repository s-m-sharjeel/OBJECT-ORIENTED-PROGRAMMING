package Final_Exam.question2;

public class Cuboid implements Shape{

    private float length;
    private float width;
    private float height;

    public Cuboid(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public float getVolume() {
        return (length * width * height);
    }

    @Override
    public String getShapeType() {
        return "Cuboid";
    }

    @Override
    public float getSurfaceArea() {
        return 2 * ((length * width) + (width * height) + (height * length));
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cuboid{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
