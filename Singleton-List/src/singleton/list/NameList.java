
package singleton.list;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NameList {
    
    private static NameList instance = null;
    
    private List<String> nameList = new LinkedList<String>();
    
    public static NameList getInstance()
    {
        if(instance == null)
            instance = new NameList();
        
        return instance;
    }
    
    public void dodaj(String name)
    {
        nameList.add(name);
    }
     
    public void usun()
    {
        if(nameList.size()>0)
            nameList.remove(nameList.size()-1);
    }
    
    public void wyswietl()
    {
        Iterator<String> iterator = nameList.iterator();        
        if(nameList.size()>0)
        {
            System.out.println("Lista imion:");
            while(iterator.hasNext())
                System.out.println(iterator.next());
        }
        else
            System.out.println("Lista jest pusta");
    }
     
    public void podajIle()
    {
        System.out.println( "Na liscie znajduje sie imion: " + nameList.size());
    }
    
}
