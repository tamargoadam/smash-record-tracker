import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddCharFrame extends JFrame {

	DataBase smashDB = new DataBase();
	
	JFrame NewCharFrame;
	private JTextField text_tag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCharFrame frame = new AddCharFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddCharFrame() {
		NewCharFrame = new JFrame();
		NewCharFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		NewCharFrame.setTitle("Create A Character");
		NewCharFrame.setBounds(100, 100, 600, 500);
		NewCharFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		NewCharFrame.getContentPane().setLayout(null);
		
		JLabel lblCharacter = new JLabel("Select New Character : ");
		lblCharacter.setForeground(new Color(65, 105, 225));
		lblCharacter.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCharacter.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCharacter.setBounds(37, 240, 213, 40);
		NewCharFrame.getContentPane().add(lblCharacter);
		
		JLabel lblTag = new JLabel("Enter Tag : ");
		lblTag.setForeground(new Color(65, 105, 225));
		lblTag.setBackground(Color.WHITE);
		lblTag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTag.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTag.setBounds(115, 116, 135, 40);
		NewCharFrame.getContentPane().add(lblTag);
		
		text_tag = new JTextField();
		text_tag.setFont(new Font("Tahoma", Font.PLAIN, 20));
		text_tag.setBounds(265, 120, 180, 30);
		NewCharFrame.getContentPane().add(text_tag);
		text_tag.setColumns(10);
		
		JLabel lblError = new JLabel("");
		lblError.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(15, 296, 548, 20);
		NewCharFrame.getContentPane().add(lblError);
		
		JComboBox<String> comboBox_char = new JComboBox<String>();
		comboBox_char.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_char.setBounds(265, 246, 180, 30);
		comboBox_char.addItem("Fox");
		comboBox_char.addItem("Falco");
		comboBox_char.addItem("Sheik");
		comboBox_char.addItem("Marth");
		comboBox_char.addItem("Captain Falcon");
		comboBox_char.addItem("Jigglypuff");
		comboBox_char.addItem("Ice Climbers");
		comboBox_char.addItem("Peach");
		comboBox_char.addItem("Pikachu");
		comboBox_char.addItem("Samus");
		comboBox_char.addItem("Dr. Mario");
		comboBox_char.addItem("Yoshi");
		comboBox_char.addItem("Luigi");
		comboBox_char.addItem("Mario");
		comboBox_char.addItem("Link");
		comboBox_char.addItem("Young Link");
		comboBox_char.addItem("Donkey Kong");
		comboBox_char.addItem("Ganondorf");
		comboBox_char.addItem("Roy");
		comboBox_char.addItem("Mr. Game & Watch");
		comboBox_char.addItem("Mewtwo");
		comboBox_char.addItem("Zelda");
		comboBox_char.addItem("Ness");
		comboBox_char.addItem("Pichu");
		comboBox_char.addItem("Bowser");
		comboBox_char.addItem("Kirby");
		NewCharFrame.getContentPane().add(comboBox_char);
		
		JButton btnAddChar = new JButton("Create");
		btnAddChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Boolean tagExists = false;
				Boolean charExists = false;
				String tagText = text_tag.getText();
				
				ResultSet rs;

				//Adds specified character to corresponding player if tag exists and does not yet have this character
				try {
					rs = smashDB.displayPlayers();
					while(rs.next()){
						if(rs.getString("tag").equals(tagText)) tagExists = true;
					}
					
					rs = smashDB.displayCharactersByPlayer();
					while(rs.next()){
						if(rs.getString("name").equals(comboBox_char.getSelectedItem())) charExists = true;
					}
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
				if(tagExists){

					if(!charExists){
						smashDB.addCharacter(tagText, (String) comboBox_char.getSelectedItem(), false);
						NewCharFrame.dispose();
					}else{
						lblError.setText((String) comboBox_char.getSelectedItem() + " already exists for " + tagText + ". Please try again.");
					}
				}else{
					lblError.setText("Tag, " + tagText + ", was not found. Please try again.");
				}
			}
		});
		btnAddChar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddChar.setBounds(232, 350, 115, 40);
		NewCharFrame.getContentPane().add(btnAddChar);
		
	}
}


