package Labs.Lab02;

public class Person {
    
    String name;
    int age;
    Pet pet;
    
    public Person(String name, Pet pet){
        
        this.name = name;
        this.pet = pet;
        
    }
    
    public Person(String name, int age, Pet pet){
        
        this.name = name;
        this.age = age;
        this.pet = pet;
        
    }
    
    public String getDetails(){
        
        return "Name: " + name + "\nAge: " + age + "\n" + this.pet.getDetails();
        
    }
    
    public static void main(String[] args) {
        
        Pet pet = new Pet("Fido", "Labrador", 5);
        Person person = new Person("John", 25, pet);
        
        System.out.println(person.getDetails());
        
        System.out.println();
        
        String[] names = {"ter stegen", "pique", "araujo", "busquets", "xavi", "dembele", "gavi", "iniesta", "pedri", "ansu fati"};
        String[] pets = {"courtois", "carvajal", "eder militao", "rudiger", "kroos", "vazquez", "modric", "hazard", "benzema", "vinicius"}; 
        
        System.out.print("How many names do you want to generate? ");
        
        java.util.Scanner input = new java.util.Scanner(System.in);
        int num = input.nextInt();
        
        Person[] persons = new Person[num];
        
        System.out.println();
        
        for (int i = 0; i < num; i++) {
            
            Pet randomPet = new Pet(pets[(int)(Math.random()*10)], " - ", 1 + (int)(Math.random()*10));
            persons[i] = new Person(names[(int)(Math.random()*10)], 18 + (int)(Math.random()*42), randomPet);
        
        }
        
        for (int i = 0; i < persons.length; i++) {
            
            System.out.println((i+1) + ": ");
            System.out.println(persons[i].getDetails());
            System.out.println();
            
        }
        
    }
    
}
