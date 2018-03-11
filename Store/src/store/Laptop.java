//Autor Mateusz Mrzygłód
package store;

public class Laptop implements LaptopInterface{
    private String name;
    private double price;
    
    public Laptop(String name, double price){
        this.name = name;
        this.price = price;
    }
    private Laptop(){
        
    }
    
    public double getPrice(){
        return price;
    }
    
    public String about(){
        return "Laptop: " + name;
    }
            
    
}
