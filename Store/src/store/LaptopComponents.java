
package store;


abstract class LaptopComponents implements LaptopInterface {
    
    private LaptopInterface laptopInterface;
    
    public LaptopComponents(LaptopInterface laptopInterface){
        this.laptopInterface = laptopInterface;
    }
    
    public double getPrice(){
        return laptopInterface.getPrice() + getComponentPrice(laptopInterface.getPrice());
    }
    
    public String about(){
        return laptopInterface.about() +"\n"+ getComponentInfo(laptopInterface.about());
    }
    
    
    abstract protected double getComponentPrice(double price);
    abstract protected String getComponentInfo(String name);
}
