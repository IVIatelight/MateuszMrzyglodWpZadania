import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FactoryDB {
	
	public static int usersCount(){
		
		int numberOfUsers = 0;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS COUNT FROM USERS");
			
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

	public static String[] userColumnHeaders() {
		
		String[] userColumnHeaders = {"Id","First name", "Last name", "Privileges"};
		return userColumnHeaders;		
	}
	
	public static String[][] sortedListUsers(int order)
	{
		int usersCount = usersCount();
		String[][] usersList = new String[usersCount][4];
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = null;
			switch (order) {
				case 0: resultSet = statement.executeQuery("SELECT * FROM USERS"); 
					break;
				case 1: resultSet = statement.executeQuery("SELECT * FROM USERS ORDER BY FIRST_NAME ASC"); 
					break;
				case 2: resultSet = statement.executeQuery("SELECT * FROM USERS ORDER BY FIRST_NAME DESC"); 
					break;
			}			
			
			int counter = 0;
			while( resultSet.next() ) {				
				usersList[counter][0] = resultSet.getString("ID_USER");
				usersList[counter][1] = resultSet.getString("FIRST_NAME");
				usersList[counter][2] = resultSet.getString("LAST_NAME");
				usersList[counter][3] = resultSet.getString("PRIVILEGES_ID");
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

	
	public static void addUser(int id_user, String first_name, String last_name, int privileges)
	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
			"INSERT INTO USERS VALUES(" + id_user +",'"+ first_name +"','"+ last_name +"'," + privileges + ")");
						
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
	
	public static void removeUser(int id_user)
	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
			"DELETE FROM USERS WHERE ID_USER = '" + id_user + "'");			
						
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
	
	public static void main(String[] args) {
		//System.out.println("Dzia³a");
		//listUsers();
		//addUser(5, "Ala", "Kalka", 3);
		//removeUser(5);
		//sortedListUsers();
		//System.out.println(usersCount());

		String[][] users = sortedListUsers(2);
		
		//System.out.println(users[0][0] + " - " + users[0][1] + " - " + users[0][2] + " - " + users[0][3]);
		//System.out.println(users[1][0] + " - " + users[1][1] + " - " + users[1][2] + " - " + users[1][3]);
	}

}
