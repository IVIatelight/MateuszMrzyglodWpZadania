//autor Mateusz Mrzyg³ód
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class GUI_database {

	private JFrame frame;
	private DefaultTableModel model;	
	AddFrame addframe = new AddFrame(this);
	
	private static String[] className = {"Users","Products", "Privileges"};
	private String[][] dataNoSorted;
	private String[][] dataSortedAsc;
	private String[] columnHeaders;
	private JTable table;
	
	DatabaseFactory databaseFactory = new DatabaseFactory();
    DatabaseInterface database = databaseFactory.getDatabase("USERS");
	
	public static String[] tableColumnHeaders() {
		
		String[] userColumnHeaders = {"Id","Name", "Description"};
		return userColumnHeaders;	
	}
	
	public void selectTable(String tableName)
	{
        database = databaseFactory.getDatabase(tableName);
	}
	
	public void addItem(String name)
	{
		database.addItem(name);
		getTable();
		model.setDataVector(dataNoSorted, columnHeaders);
		table.setModel(model);
		table.repaint();
		addframe.setVisible(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(2).setPreferredWidth(109);		
	}
	public void noAddItem()
	{
		addframe.setVisible(false);
	}
	
	public void setColumnWidth()
	{
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		table.getColumnModel().getColumn(1).setPreferredWidth(99);
		table.getColumnModel().getColumn(2).setPreferredWidth(109);
	}
	
	public void getTable()
	{
		dataNoSorted = database.sortedList(0);
		dataSortedAsc = database.sortedList(1);
		//dataSortedDesc = sortedListUsers(2);
		columnHeaders = tableColumnHeaders();		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////	
	
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
		
		JComboBox comboBox = new JComboBox(className);		
		JButton btnAdd = new JButton("Add");
		JButton btnDel = new JButton("Del");
		JButton btnGet = new JButton("Get");
		JButton btnSort = new JButton("Sort");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());		
		
		
		getTable();
		model = new DefaultTableModel(dataNoSorted, columnHeaders);
		table = new JTable(model);		
		table = new JTable(dataNoSorted, columnHeaders);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setEnabled(true);
		setColumnWidth();
		
		///////////////////////-----OBS£UGA-PRZYCISKÓW-----///////////////////////
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {					
				addframe.setVisible(true);				
			}
		});		
		
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i = table.getSelectedRow();
				if(i>= 0)
				{
					String idItem = (String) model.getValueAt(i, 0);
					database.deleteItem(idItem);
					model.removeRow(i);
					table.setModel(model);
					table.repaint();
					setColumnWidth();
					getTable();
				}				
			}
		});		
		
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name = (String)comboBox.getSelectedItem();
				selectTable(name);
				getTable();
				
				model.setDataVector(dataNoSorted, columnHeaders);
				table.setModel(model);
				table.repaint();
				setColumnWidth();				
			}
		});
		
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setDataVector(dataSortedAsc, columnHeaders);
				table.setModel(model);
				table.repaint();
				setColumnWidth();				
			}
		});		
		
		///////////////////////-----ROZMIESZCZENIE-ELEMENTÓW-----///////////////////////
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnAdd, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBox, Alignment.TRAILING, 0, 116, Short.MAX_VALUE))
								.addComponent(btnDel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnGet, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSort, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGet)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSort))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		frame.getContentPane().setLayout(groupLayout);
	}
}
