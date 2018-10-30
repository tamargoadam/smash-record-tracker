import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ChangeMainWindow extends JFrame {

	DataBase smashDB = new DataBase();
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ChangeMainWindow() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewMain = new JLabel("Enter New Main: ");
		lblNewMain.setForeground(new Color(65, 105, 225));
		lblNewMain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewMain.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewMain.setBounds(37, 60, 213, 40);
		contentPane.add(lblNewMain);
		
		JComboBox<String> comboBox_char = new JComboBox<String>();
		comboBox_char.setBounds(265, 66, 180, 30);
		contentPane.add(comboBox_char);
		
		
		ResultSet rs = smashDB.displayCharactersByPlayer();
		try {
			while(rs.next()){
				if(rs.getString("tag").equals(SmashAppWindow.user)) comboBox_char.addItem(rs.getString("name"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JButton btnChangeMain = new JButton("Change Main");
		btnChangeMain.setBounds(189, 140, 200, 40);
		contentPane.add(btnChangeMain);
		btnChangeMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				smashDB.changeMain((String) comboBox_char.getSelectedItem());
				dispose();
			}
		});
	}
}
