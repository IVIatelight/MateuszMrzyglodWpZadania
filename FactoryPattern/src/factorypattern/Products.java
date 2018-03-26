//@author Mateusz Mrzygłód
package factorypattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Products implements DatabaseInterface {

    private List<String> productsList = new ArrayList<String>();

    @Override
    public void addItem(String nameOfProduct) {
        productsList.add(nameOfProduct);
    }

    @Override
    public void deleteItem(String nameOfProduct) {
        productsList.remove(nameOfProduct);
    }

    @Override
    public void listItems() {

        Iterator<String> iterator = productsList.iterator();
        if (productsList.size() > 0) {
            System.out.println("Lista produktow:");
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("Brak produktow");
        }

    }

    @Override
    public void sortItems() {

        if (productsList.size() > 0) {
            List<String> productsListSorted = new ArrayList<String>(productsList);
            Collections.sort(productsListSorted);

            Iterator<String> iterator = productsListSorted.iterator();

            System.out.println("Posortowana lista produktow:");
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("Brak produktow");
        }
        
    }

}
