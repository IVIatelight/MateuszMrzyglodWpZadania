//Autor Mateusz Mrzygłód PGK1 Wp
package store;

abstract class LaptopComponents implements LaptopInterface {
    
    private final LaptopInterface laptopInterface;
    
    public LaptopComponents(LaptopInterface laptopInterface){
        this.laptopInterface = laptopInterface;
    }
        
    @Override
    public double getPrice(){
        return laptopInterface.getPrice() + getComponentPrice(laptopInterface.getPrice());
    }
    
    @Override
    public String about(){
        return laptopInterface.about() +"\n"+ getComponentInfo(laptopInterface.about());
    }    
    
    abstract protected double getComponentPrice(double price);
    abstract protected String getComponentInfo(String name);
}
