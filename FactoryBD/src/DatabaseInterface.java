//autor Mateusz Mrzyg³ód
public interface DatabaseInterface {
	
	public int usersCount();
	
	public void addItem( String name);

	public void deleteItem(String id);

    //public void listItems();

	public String[][] sortedList(int order); //0 - no order, 1 - sorted asc, 2 - sorted desc
}
