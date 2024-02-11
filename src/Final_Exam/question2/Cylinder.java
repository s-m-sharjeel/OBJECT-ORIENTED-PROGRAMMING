package Final_Exam.question2;

public class Cylinder implements Shape{

    private float radius;
    private float height;

    public Cylinder(float radius, float height) {
        this.radius = radius;
        this.height = height;
    }

    @Override
    public float getVolume() {
        return height * (float)Math.PI * radius * radius ;
    }

    @Override
    public String getShapeType() {
        return "Cylinder";
    }

    @Override
    public float getSurfaceArea() {
        return 2 * (float)Math.PI * radius * (radius + height);
    }

    public float getRadius() {
        return radius;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "radius=" + radius +
                ", height=" + height +
                '}';
    }
}
