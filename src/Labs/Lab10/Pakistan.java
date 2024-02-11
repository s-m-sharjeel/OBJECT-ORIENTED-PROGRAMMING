package Labs.Lab10;

public class Pakistan {

    public static void main(String[] args){

        Factory factory = new Factory();

        System.out.println(factory.getCity("Sindh").toString());
        System.out.println(factory.getCity("Punjab").toString());
        System.out.println(factory.getCity("Khyber Pakhtunkhwa").toString());
        System.out.println(factory.getCity("Balochistan").toString());
        System.out.println(factory.getCity("Azad Kashmir").toString());
        System.out.println(factory.getCity("Gilgit-Baltistan").toString());

    }

}
