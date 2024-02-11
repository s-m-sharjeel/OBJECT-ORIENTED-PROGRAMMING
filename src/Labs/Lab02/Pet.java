package Labs.Lab02;

public class Pet {
    
    String name;
    String breed;
    int age;
    
    public Pet(String name){
        
        this.name = name;
        
    }
    
    public Pet(String name, String breed, int age){
        
        this.name = name;
        this.breed = breed;
        this.age = age;
        
    }
    
    public String getDetails(){
        
        return "Pet Name: " + name + "\nPet breed: " + breed + "\nPet Age: " + age;
        
    }
    
}
