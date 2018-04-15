//autor Mateusz Mrzyg��d
import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    private List<Memento> mementoList = new ArrayList<Memento>();

    public void addMemento(Memento state) {

        mementoList.add(state);
    }
    
    //trzeba pami�ta� �eby nie cofa� zmian jesli ich nie ma
    public Memento getMemento() {        
    	
    	Memento memento =  mementoList.get(mementoList.size()-1);
    	mementoList.remove(mementoList.size()-1);
    	return memento;
    }
	
	
}
