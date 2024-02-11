package Labs.Lab02;

public class Car {
    
    String make;
    String model;
    int year;
    String color;
    int wheel;
    int door;
    
    public Car(String make, String model, int year, String color, int wheel, int door){
        
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.wheel = wheel;
        this.door = door;
        
    }
    
    public String detail(){
        
        return "Make :" + make + "\nModel: " + model + "\nYear: " + year + "\nColor: " + color + "\nNumber of Wheels: " + wheel + "\nNumber of doors: " + door;  
        
    }
    
    public static void main(String[] args) {
        
        Car car[] = new Car[3];
        
        car[0] = new Car("Ford", " Mustang",  2020, "Red", 4, 2);
        
        car[1] = new Car("Honda", " Civic",  2019, "White", 4, 4);
        
        car[2] = new Car("Tesla", " Model S",  2018, "Black", 4, 4);
        
        for (int i = 0; i < car.length; i++) {
            
            System.out.println("Car " + (i+1) + ": \n" + car[i].detail());
            
            System.out.println();
        
        }
        
        
        
    }
    
    
}
