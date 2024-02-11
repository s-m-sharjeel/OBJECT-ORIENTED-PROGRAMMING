package Final_Exam.question2;

public class Sphere implements Shape{

    private float radius;

    public Sphere(float radius) {
        this.radius = radius;
    }

    @Override
    public float getVolume() {
        return (4 * (float)Math.PI * radius * radius * radius)/3;
    }

    @Override
    public String getShapeType() {
        return "Sphere";
    }

    @Override
    public float getSurfaceArea() {
        return 4 * (float)Math.PI * radius * radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "radius=" + radius +
                '}';
    }
}
