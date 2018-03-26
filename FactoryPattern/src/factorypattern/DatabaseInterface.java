//@author Mateusz Mrzygłód
package factorypattern;

public interface DatabaseInterface {

    public void addItem(String nameOfProduct);

    public void deleteItem(String nameOfProduct);

    public void listItems();

    public void sortItems();

}
