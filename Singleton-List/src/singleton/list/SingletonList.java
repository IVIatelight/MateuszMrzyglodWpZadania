// NAPISANE PRZEZ MATEUSZ MRZYGŁÓD
// PGK1 INFORMATYKA ROK 2
package singleton.list;

public class SingletonList {

    public static void main(String[] args) {
        
        NameList lista = NameList.getInstance();
        
        lista.dodaj("Ola");
        lista.dodaj("Ala");
        lista.dodaj("Kasia");

        lista.podajIle();
        lista.usun();
        lista.wyswietl();
        lista.podajIle();
        lista.usun();
        lista.podajIle();
        lista.usun();
        lista.podajIle();
        lista.usun();
        lista.wyswietl();
        
    }
    
}
