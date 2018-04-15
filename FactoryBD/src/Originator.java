//autor Mateusz Mrzyg³ód
public class Originator {

    private String[] state = new String[5];

    public void setState(String[] state) {
        this.state = state;
    }
    
    public String[] getStage() {
    	return state;
    }
    
    
    public Memento save() {
        return new Memento(state);
    }
    public void getStageFromMemento(Memento memento) {
        state = memento.getState();
    }
	
	
}
