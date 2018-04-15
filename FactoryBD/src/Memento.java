//autor Mateusz Mrzyg³ód
public class Memento {

    private String[] state = new String[5];

    public Memento(String[] state) {
        this.state = state;
    }

    public String[] getState() {
        return state;
    }
	
}
