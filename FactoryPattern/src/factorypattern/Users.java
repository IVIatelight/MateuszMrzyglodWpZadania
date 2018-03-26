//@author Mateusz Mrzygłód
package factorypattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Users implements DatabaseInterface {

    private List<String> userList = new ArrayList<String>();

    @Override
    public void addItem(String nameOfProduct) {
        userList.add(nameOfProduct);
    }

    @Override
    public void deleteItem(String nameOfProduct) {
        userList.remove(nameOfProduct);
    }

    @Override
    public void listItems() {

        Iterator<String> iterator = userList.iterator();
        if (userList.size() > 0) {
            System.out.println("Lista uzytkownikow:");
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("Brak użytkowników");
        }

    }

    @Override
    public void sortItems() {

        if (userList.size() > 0) {
            List<String> userListSorted = new ArrayList<String>(userList);
            Collections.sort(userListSorted);

            Iterator<String> iterator = userListSorted.iterator();

            System.out.println("Posortowana lista użytkowników:");
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("Brak użytkowników");
        }
        
    }
}
