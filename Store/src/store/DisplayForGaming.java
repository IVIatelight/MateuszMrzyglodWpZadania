//Autor Mateusz Mrzygłód PGK1 Wp
package store;

public class DisplayForGaming extends LaptopComponents{
    
    private double price;
    private int displayHertz;
    private String syncTechnology;
    DisplayForGaming(LaptopInterface laptopInterface,int displayHertz, String syncTechnology, double price){
        super(laptopInterface);
        this.displayHertz = displayHertz;
        this.price = price;
        this.syncTechnology = syncTechnology;
    }
    @Override
    protected double getComponentPrice(double price){
        return price;
    }
    
    @Override
    protected String getComponentInfo(String name){
        return "\t "+"Display: " + displayHertz + "Hz " + syncTechnology;
    }    
}
