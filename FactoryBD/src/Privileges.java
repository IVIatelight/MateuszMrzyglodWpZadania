//autor Mateusz Mrzyg³ód
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Privileges implements DatabaseInterface {

	private String tableName = "PRIVILEGES";

	@Override
	public String[][] sortedList(int order)
	{
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
			"INSERT INTO " + tableName + "(name, description) VALUES('"+ name +"', 'null' )");
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
	
	@Override
	public void restoreOldVersion(String[] line, String operation)
	{
		System.out.println("restoreOldVersion");
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			
			String query = "";
			if(operation == "add")
				query = "DELETE FROM " + tableName +"  WHERE ID = '" + line[0] + "'";
			
			if(operation == "del")
				query = "INSERT INTO " + tableName +" VALUES(" + line[0] + ",'" + line[1] + "','" + line[2] + "')";
			
			statement.executeQuery( query);			
			
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
