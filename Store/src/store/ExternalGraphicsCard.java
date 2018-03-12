//Autor Mateusz Mrzygłód PGK1 Wp
package store;

public class ExternalGraphicsCard extends LaptopComponents{
    
    private double priceOfGPU;
    private String nameOfGPU;
    
    ExternalGraphicsCard(LaptopInterface laptopInterface,String nameOfGPU, double priceOfGPU){
        super(laptopInterface);
        this.nameOfGPU = nameOfGPU;
        this.priceOfGPU = priceOfGPU;
    }
    @Override
    protected double getComponentPrice(double price){
        return priceOfGPU;
    }
    
    @Override
    protected String getComponentInfo(String name){
        return "\t "+"Karta graficzna: " + nameOfGPU;
    }
    
}
