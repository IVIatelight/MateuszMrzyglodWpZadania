
package store;

public class BacklitKeyboard extends LaptopComponents{
    private double price;
    private String type;
   
    BacklitKeyboard(LaptopInterface laptopInterface,String type, double price){
        super(laptopInterface);
        this.type = type;
        this.price = price;
    }
    
    protected double getComponentPrice(double price){
        return price;
    }
    
    protected String getComponentInfo(String name){
        return "\t "+"Klawiatura podswietlana: " + type;
    }
}
