//@author Mateusz Mrzygłód
package factorypattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Privileges implements DatabaseInterface {

    private List<String> privilegesList = new ArrayList<String>();

    @Override
    public void addItem(String nameOfProduct) {
        privilegesList.add(nameOfProduct);
    }

    @Override
    public void deleteItem(String nameOfProduct) {
        privilegesList.remove(nameOfProduct);
    }

    @Override
    public void listItems() {

        Iterator<String> iterator = privilegesList.iterator();
        if (privilegesList.size() > 0) {
            System.out.println("Lista uprawnien:");
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("Lista uprawnien jest pusta");
        }

    }

    @Override
    public void sortItems() {

        if (privilegesList.size() > 0) {
            List<String> privilegesListSorted = new ArrayList<String>(privilegesList);
            Collections.sort(privilegesListSorted);

            Iterator<String> iterator = privilegesListSorted.iterator();

            System.out.println("Posortowana lista uprawnien:");
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("Lista uprawnien jest pusta");
        }

    }
}
