import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_database {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JPanel usersList;
	private JPanel addUser;
	private JPanel removeUser;
	private JRadioButton descending;
	private JRadioButton ascending;
	private JTable table;
	private JTable tableNoSorted;
	private JTable tableSortedAsc;
	private JTable tableSortedDesc;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField prividitesId;
	private JTextField removeId;
	private JTextField removeFirstName;
	private JTextField removeLastName;
	private DefaultTableModel model;
	private int sortType = 0;
	
	private String[][] dataNoSorted;
	private String[][] dataSortedAsc;
	private String[][] dataSortedDesc;
	private String[] columnHeaders;

	
	
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

	
	public static void addUser(String id_user, String first_name, String last_name, String privileges)
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
	
	public static void removeUser(String id_user)
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
	
	public static void removeUser(String firstName, String lastName)
	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:data/factory.db", "sa", "");
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
			"DELETE FROM USERS WHERE FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");			
						
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
	
	public void getTable()
	{
		dataNoSorted = sortedListUsers(0);
		dataSortedAsc = sortedListUsers(1);
		dataSortedDesc = sortedListUsers(2);
		columnHeaders = userColumnHeaders();		
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_database window = new GUI_database();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_database() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		usersList = new JPanel();
		tabbedPane.addTab("Users list", null, usersList, null);
		
		JRadioButton noSort = new JRadioButton("no sort");
		noSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				model.setDataVector(dataNoSorted, columnHeaders);
				System.out.println("dZIALA0");
				descending.setSelected(false);
				ascending.setSelected(false);
				table.setModel(model);
				table.repaint();
			}
		});
		
		descending = new JRadioButton("descending order");
		descending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setDataVector(dataSortedDesc, columnHeaders);
				ascending.setSelected(false);
				noSort.setSelected(false);
				table.setModel(model);
				table.repaint();
				
			}
		});
		
		ascending = new JRadioButton("ascending order");
		ascending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.setDataVector(dataSortedAsc, columnHeaders);
				descending.setSelected(false);
				noSort.setSelected(false);
				table.setModel(model);
				table.repaint();
			}
		});
		getTable();
		model = new DefaultTableModel(dataNoSorted, columnHeaders);
		table = new JTable(model);
		
		table = new JTable(sortedListUsers(0), userColumnHeaders());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblFirstName = new JLabel("               ID                      First Name             Last Name             Privileges Id");
		
		JButton btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getTable();
				model.setDataVector(dataNoSorted, columnHeaders);
				System.out.println("DzialaGet");
				noSort.setSelected(true);
				descending.setSelected(false);
				ascending.setSelected(false);
				table.setModel(model);
				table.repaint();
				
			}
		});
		
		GroupLayout gl_usersList = new GroupLayout(usersList);
		gl_usersList.setHorizontalGroup(
			gl_usersList.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_usersList.createSequentialGroup()
					.addGroup(gl_usersList.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_usersList.createSequentialGroup()
							.addComponent(noSort)
							.addGap(18)
							.addComponent(ascending)
							.addGap(18)
							.addComponent(descending)
							.addGap(29)
							.addComponent(btnGet))
						.addGroup(gl_usersList.createSequentialGroup()
							.addContainerGap()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
						.addGroup(gl_usersList.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFirstName, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_usersList.setVerticalGroup(
			gl_usersList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_usersList.createSequentialGroup()
					.addGroup(gl_usersList.createParallelGroup(Alignment.BASELINE)
						.addComponent(noSort)
						.addComponent(ascending)
						.addComponent(descending)
						.addComponent(btnGet))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFirstName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addContainerGap())
		);
		usersList.setLayout(gl_usersList);
		
		addUser = new JPanel();
		tabbedPane.addTab("Add user", null, addUser, null);
		
		JTextField userId = new JTextField();
		userId.setColumns(10);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		
		prividitesId = new JTextField();
		prividitesId.setColumns(10);
		
		JButton btnAddUser = new JButton("Add user");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addUser(userId.getText(), firstName.getText(), lastName.getText(), prividitesId.getText());
			
				userId.setText("");
				firstName.setText("");
				lastName.setText("");
				prividitesId.setText("");
			}
		});
		
		JLabel lblNewLabel = new JLabel("  User Id                First Name                        Last Name              Prividices Id");
		GroupLayout gl_addUser = new GroupLayout(addUser);
		gl_addUser.setHorizontalGroup(
			gl_addUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addUser.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addUser.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_addUser.createSequentialGroup()
							.addComponent(userId, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lastName, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(prividitesId, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_addUser.createSequentialGroup()
							.addComponent(btnAddUser)
							.addGap(62))))
		);
		gl_addUser.setVerticalGroup(
			gl_addUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addUser.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_addUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(userId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(firstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(prividitesId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAddUser)
					.addContainerGap(130, Short.MAX_VALUE))
		);
		addUser.setLayout(gl_addUser);
		
		removeUser = new JPanel();
		tabbedPane.addTab("Remove user", null, removeUser, null);
		
		JLabel lblNewLabel_1 = new JLabel("Id user");
		
		JLabel lblOr = new JLabel("or");
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblFirstName_1 = new JLabel("First Name");
		
		JLabel lblLastName = new JLabel("Last Name");
		
		removeId = new JTextField();
		removeId.setColumns(10);
		
		removeFirstName = new JTextField();
		removeFirstName.setColumns(10);
		
		removeLastName = new JTextField();
		removeLastName.setColumns(10);
		
		JButton removeByName = new JButton("Remove");
		removeByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				removeUser(removeFirstName.getText(), removeLastName.getText());
				removeFirstName.setText("");
				removeLastName.setText("");
				
			}
		});
		
		JButton removeById = new JButton("Remove");
		removeById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				removeUser(removeId.getText());
				removeId.setText("");
				
			}
		});
		GroupLayout gl_removeUser = new GroupLayout(removeUser);
		gl_removeUser.setHorizontalGroup(
			gl_removeUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_removeUser.createSequentialGroup()
					.addGroup(gl_removeUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_removeUser.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_removeUser.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_removeUser.createSequentialGroup()
									.addComponent(removeId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(removeById, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_removeUser.createSequentialGroup()
									.addComponent(removeFirstName, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(removeLastName, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(removeByName, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_removeUser.createSequentialGroup()
									.addGap(24)
									.addGroup(gl_removeUser.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblOr, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFirstName_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
									.addGap(70)
									.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_removeUser.createSequentialGroup()
							.addGap(67)
							.addComponent(lblNewLabel_1)))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_removeUser.setVerticalGroup(
			gl_removeUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_removeUser.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_removeUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(removeById))
					.addGap(22)
					.addComponent(lblOr, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_removeUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(lblFirstName_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_removeUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(removeLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(removeByName))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		removeUser.setLayout(gl_removeUser);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
