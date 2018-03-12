//Autor Mateusz Mrzygłód PGK1 Wp
package store;

public class OledDisplay extends LaptopComponents{

    private double price;    
    
    OledDisplay(LaptopInterface laptopInterface, double price){
        super(laptopInterface);        
        this.price = price;
    }
    @Override
    protected double getComponentPrice(double price){
        return price;
    }
    
    @Override
    protected String getComponentInfo(String name){
        return "\t "+"Display: OLED ";
    }    
}
