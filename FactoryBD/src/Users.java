//autor Mateusz Mrzyg³ód
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users implements DatabaseInterface {

	private String tableName = "USERS";
	
	@Override
	public int usersCount(){
		
		int numberOfUsers = 0;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM " + tableName);
			
			while( resultSet.next() ) {
				numberOfUsers = resultSet.getInt(1);
			}
					
			statement.execute( "SHUTDOWN" );
			statement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Wyst¹pi³ nastepuj¹cy b³¹d z baza danych: ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Wyst¹pi³ nastepuj¹cy b³¹d z zapytaniem SQL: ");
			e.printStackTrace();
		}
		
		return numberOfUsers;
	}

	@Override
	public String[][] sortedList(int order)
	{
		int usersCount = usersCount();
		String[][] usersList = new String[usersCount][3];
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = null;
			switch (order) {
				case 0: resultSet = statement.executeQuery("SELECT * FROM " + tableName); 
					break;
				case 1: resultSet = statement.executeQuery("SELECT * FROM " + tableName + " ORDER BY NAME ASC"); 
					break;
				case 2: resultSet = statement.executeQuery("SELECT * FROM " + tableName +  " ORDER BY NAME DESC"); 
					break;
			}			
			
			int counter = 0;
			while( resultSet.next() ) {				
				usersList[counter][0] = resultSet.getString("ID");
				usersList[counter][1] = resultSet.getString("NAME");
				usersList[counter][2] = resultSet.getString("DESCRIPTION");
				counter++;				
			}
						
			statement.execute( "SHUTDOWN" );
			statement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Wyst¹pi³ nastepuj¹cy b³¹d z baza danych: ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Wyst¹pi³ nastepuj¹cy b³¹d z zapytaniem SQL: ");
			e.printStackTrace();
		}
		
		return usersList;
	}

	@Override
	public void addItem( String name)
	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			statement.executeQuery(
			"INSERT INTO " + tableName + "(name) VALUES('"+ name +"')");
			statement.execute( "SHUTDOWN" );
			statement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Wyst¹pi³ nastepuj¹cy b³¹d z baza danych: ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Wyst¹pi³ nastepuj¹cy b³¹d z zapytaniem SQL: ");
			e.printStackTrace();
		}		
	}
	
	@Override
	public void deleteItem(String id)
	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			statement.executeQuery(
			"DELETE FROM " + tableName +"  WHERE ID = '" + id + "'");			
			
			statement.execute( "SHUTDOWN" );
			statement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Wyst¹pi³ nastepuj¹cy b³¹d z baza danych: ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Wyst¹pi³ nastepuj¹cy b³¹d z zapytaniem SQL: ");
			e.printStackTrace();
		}		
	}
}
