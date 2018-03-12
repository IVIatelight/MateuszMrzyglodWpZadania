//Autor Mateusz Mrzygłód PGK1 Wp
package store;

public class BacklitKeyboard extends LaptopComponents{
    private double price;
    private String type;
   
    BacklitKeyboard(LaptopInterface laptopInterface,String type, double price){
        super(laptopInterface);
        this.type = type;
        this.price = price;
    }
    
    @Override
    protected double getComponentPrice(double price){
        return price;
    }
    
    @Override
    protected String getComponentInfo(String name){
        return "\t "+"Klawiatura podswietlana: " + type;
    }
}
