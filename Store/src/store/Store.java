//Autor Mateusz Mrzygłód PGK1 Wp
package store;

public class Store {

    public static void main(String[] args) {        

        LaptopInterface laptop1 = new Laptop("Bugatti C6", 3000);
        laptop1 = new ExternalGraphicsCard(laptop1, "AMD 580", 1300);
        System.out.println(laptop1.about());
        System.out.println("Cena: " + laptop1.getPrice());
        System.out.println(" ");
        
        LaptopInterface laptop3 = new Laptop("Vivaldi 4S", 2500);
        laptop3 = new DisplayForGaming(laptop3, 120, "VSYNC", 250);
        laptop3 = new ExternalGraphicsCard(laptop3, "Nvidia GTX 1080 Ti", 5300);
        laptop3 = new ExternalGraphicsCard(laptop3, "AMD Radeon RX 580", 5300); 
        laptop3 = new BacklitKeyboard(laptop3, "LED Nasty red", 300);        
        laptop3 = new OledDisplay(laptop3, 200);
        System.out.println(laptop3.about());
        System.out.println("Cena: " + laptop3.getPrice());        
    }
    
}
