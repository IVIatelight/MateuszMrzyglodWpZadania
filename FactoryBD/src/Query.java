import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query implements QueryInterface{

	public String[][] select(String query, String tableName){
		
		String[][] usersList = {};
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement1 = connection.createStatement();
			ResultSet resultSet1 = statement1.executeQuery("SELECT COUNT(*) AS COUNT FROM " + tableName);
			int numberOfUsers = 0;
			while( resultSet1.next() ) {
				numberOfUsers = resultSet1.getInt(1);
			}
			usersList = new String[numberOfUsers][3];
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = null;

			resultSet = statement.executeQuery(query); 
		
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
	
	public void insert(String query) {
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			statement.executeQuery(query);
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

	
	public void delete(String query) {
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			statement.executeQuery(query);		
			
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
