package Final_Exam.question2;

public class ShapeManager {

    public static void main(String[] args) {

        Shape s = new Sphere(21);
        float surfaceAreaOfS = s.getSurfaceArea();
        System.out.println(surfaceAreaOfS);
        float volumeOfS = s.getVolume();
        System.out.println(volumeOfS);
        System.out.println(s.toString());

        Shape c = new Cube(21);
        float surfaceAreaOfC = c.getSurfaceArea();
        System.out.println(surfaceAreaOfC);
        float volumeOfC = c.getVolume();
        System.out.println(volumeOfC);
        System.out.println(c.toString());

        Shape cb = new Cuboid(21, 12, 14);
        float surfaceAreaOfCB = cb.getSurfaceArea();
        System.out.println(surfaceAreaOfCB);
        float volumeOfCB = cb.getVolume();
        System.out.println(volumeOfCB);
        System.out.println(cb.toString());


    }

}
