//@author Mateusz Mrzygłód
package factorypattern;

public class FactoryPattern {

    public static void main(String[] args) {

        DatabaseFactory myDatabase = new DatabaseFactory();
        DatabaseInterface myDatabase1 = myDatabase.getDatabase("products");

        myDatabase1.addItem("Dluto");
        myDatabase1.addItem("Wkretak");
        myDatabase1.addItem("Mloteczek");
        myDatabase1.addItem("Mlotek");
        myDatabase1.deleteItem("Mlotek");
        myDatabase1.listItems();
        myDatabase1.sortItems();

        DatabaseInterface myDatabase2 = myDatabase.getDatabase("users");

        myDatabase2.addItem("Marek");
        myDatabase2.listItems();
    }

}
