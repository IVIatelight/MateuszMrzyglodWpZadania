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
	JButton btnUndo = new JButton("Undo");
	
	DatabaseFactory databaseFactory = new DatabaseFactory();
    DatabaseInterface database = databaseFactory.getDatabase("USERS");
	String currentTable = "USERS";
    
    Originator originator = new Originator();
    Caretaker caretaker = new Caretaker();
    private int updateCounter = 0;
    
    
	public static String[] tableColumnHeaders() {
		
		String[] userColumnHeaders = {"Id","Name", "Description"};
		return userColumnHeaders;	
	}
	
	public void selectTable(String tableName)
	{
        currentTable = tableName;
		database = databaseFactory.getDatabase(tableName);
	}
	
	public void updateTable()
	{
		getTable();
		model.setDataVector(dataNoSorted, columnHeaders);
		table.setModel(model);
		table.repaint();
		setColumnWidth();
	}
	
	public void addItem(String name)
	{		
		updateCounter++;
		database.addItem(name);
		updateTable();
		addframe.setVisible(false);
		int n = dataNoSorted.length - 1;
		String id = dataNoSorted[n][0];
		System.out.println("Id: " + id);
		String[] line = {id, name, "", "add", currentTable};
		originator.setState(line);
		caretaker.addMemento(originator.save());
		btnUndo.setText("Undo (" + updateCounter + ")");
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
		//JButton btnUndo = new JButton("Undo");
		
		JScrollPane scrollPane = new JScrollPane();
		
		///////////////////////-----INICJALIZACJA-TABELI-----///////////////////////
		
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
				updateCounter++;
				int i = table.getSelectedRow();
				if(i>= 0)
				{
					String idItem = (String) model.getValueAt(i, 0);
					String nameItem = (String) model.getValueAt(i, 1);
					String descriptionItem = (String) model.getValueAt(i, 2);
					
					database.deleteItem(idItem);
					model.removeRow(i);
					table.setModel(model);
					table.repaint();
					setColumnWidth();
					getTable();
					
					String[] line = {idItem, nameItem, descriptionItem, "del", currentTable};
					originator.setState(line);
					caretaker.addMemento(originator.save());
					
					btnUndo.setText("Undo (" + updateCounter + ")");
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
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(updateCounter>0) {
					originator.getStageFromMemento(caretaker.getMemento());
					String[] stage = originator.getStage();
					selectTable(stage[4]);
					String[] line = {stage[0], stage[1], stage[2]};
					String operation = stage[3];
					database.restoreOldVersion(line, operation);				
					updateTable();
					updateCounter--;
					btnUndo.setText("Undo (" + updateCounter + ")");
				}				
			}
		});
		
///////////////////////-----UMIEJSCOWIENIE-ELEMENTOW-----///////////////////////
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAdd, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox, Alignment.TRAILING, 0, 116, Short.MAX_VALUE))
									.addComponent(btnDel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnGet, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnSort, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
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
							.addComponent(btnSort)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUndo))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		frame.getContentPane().setLayout(groupLayout);
	}
}