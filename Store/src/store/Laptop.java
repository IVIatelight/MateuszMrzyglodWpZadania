//Autor Mateusz Mrzygłód
package store;

public class Laptop implements LaptopInterface{
    private String name;
    private double price;
    //Autor Mateusz Mrzygłód PGK1 Wp
    public Laptop(String name, double price){
        this.name = name;
        this.price = price;
    }
    private Laptop(){        
    }
    
    @Override
    public double getPrice(){
        return price;
    }
    
    @Override
    public String about(){
        return "Laptop: " + name;
    }
}
