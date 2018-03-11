//Autor Mateusz Mrzygłód
package store;

public class Store {

    public static void main(String[] args) {
        
        Laptop laptop1 = new Laptop("Bugatti C6", 3000);        
        ExternalGraphicsCard laptopZGPU = new ExternalGraphicsCard(laptop1, "AMD 580", 1300);
        
        System.out.println(laptopZGPU.about());
        System.out.println("Cena: " + laptopZGPU.getPrice());
        
        
        Laptop laptop2 = new Laptop("Pavarotti L7", 2500);        
        BacklitKeyboard laptopZSwiecacaKlawiatura = new BacklitKeyboard(laptop2, "LED All Color", 100);        
        
        System.out.println(laptopZSwiecacaKlawiatura.about());
        System.out.println("Cena: " + laptopZSwiecacaKlawiatura.getPrice());
        
        
        Laptop laptop3 = new Laptop("Vivaldi 4S", 2500);
        ExternalGraphicsCard laptopZGPU2 = new ExternalGraphicsCard(laptop3, "GTX 1080 Ti", 5300);        
        BacklitKeyboard superLaptop = new BacklitKeyboard(laptopZGPU2, "LED Nasty red", 300);
        
        System.out.println(superLaptop.about());
        System.out.println("Cena: " + superLaptop.getPrice());
    }
    
}
