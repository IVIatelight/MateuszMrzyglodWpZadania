//autor Mateusz Mrzyg³ód
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame {

	private JPanel contentPane;
	private GUI_database mainFrame;
	private JTextField tfName;	
	
	/**
	 * Create the frame.
	 */
		public AddFrame(GUI_database mF) {
		mainFrame = mF;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 272, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPodajNazw = new JLabel("Podaj nazw\u0119:");
		lblPodajNazw.setBounds(10, 11, 152, 14);
		contentPane.add(lblPodajNazw);
		
		tfName = new JTextField();
		tfName.setBounds(10, 33, 236, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JButton btnZatwierdz = new JButton("Zatwierdz");
		btnZatwierdz.setBounds(10, 64, 112, 23);
		contentPane.add(btnZatwierdz);
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setBounds(134, 64, 112, 23);
		contentPane.add(btnAnuluj);
		
		///////////////////////-----OBS£UGA-PRZYCISKÓW-----///////////////////////
		
		btnZatwierdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String name = tfName.getText();
				if(!name.isEmpty()) {
					mainFrame.addItem(tfName.getText());				
					tfName.setText("");
				}else
				{
					mainFrame.noAddItem();
				}
			}
		});
		
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.noAddItem();
				tfName.setText("");
			}
		});
	}

}
