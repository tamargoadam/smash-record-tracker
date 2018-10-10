import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;


public class AddTagWindow {

	DataBase smashDB = new DataBase();
	
	JFrame NewTagFrame;
	private JTextField text_tag;


	/**
	 * Create the application.
	 */
	public AddTagWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		NewTagFrame = new JFrame();
		NewTagFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
		NewTagFrame.setTitle("Create A Tag");
		NewTagFrame.setBounds(100, 100, 600, 500);
		NewTagFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		NewTagFrame.getContentPane().setLayout(null);
		
		JLabel lblMain = new JLabel("Select Your Main : ");
		lblMain.setForeground(new Color(65, 105, 225));
		lblMain.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMain.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMain.setBounds(75, 240, 175, 40);
		NewTagFrame.getContentPane().add(lblMain);
		
		JLabel lblTag = new JLabel("Enter Your Tag : ");
		lblTag.setForeground(new Color(65, 105, 225));
		lblTag.setBackground(Color.WHITE);
		lblTag.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTag.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTag.setBounds(75, 116, 175, 40);
		NewTagFrame.getContentPane().add(lblTag);
		
		text_tag = new JTextField();
		text_tag.setFont(new Font("Tahoma", Font.PLAIN, 20));
		text_tag.setBounds(265, 120, 180, 30);
		NewTagFrame.getContentPane().add(text_tag);
		text_tag.setColumns(10);
		
		JComboBox<String> comboBox_main = new JComboBox<String>();
		comboBox_main.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_main.setBounds(265, 246, 180, 30);
		comboBox_main.addItem("Fox");
		comboBox_main.addItem("Falco");
		comboBox_main.addItem("Sheik");
		comboBox_main.addItem("Marth");
		comboBox_main.addItem("Captain Falcon");
		comboBox_main.addItem("Jigglypuff");
		comboBox_main.addItem("Ice Climbers");
		comboBox_main.addItem("Peach");
		comboBox_main.addItem("Pikachu");
		comboBox_main.addItem("Samus");
		comboBox_main.addItem("Dr. Mario");
		comboBox_main.addItem("Yoshi");
		comboBox_main.addItem("Luigi");
		comboBox_main.addItem("Mario");
		comboBox_main.addItem("Link");
		comboBox_main.addItem("Young Link");
		comboBox_main.addItem("Donkey Kong");
		comboBox_main.addItem("Ganondorf");
		comboBox_main.addItem("Roy");
		comboBox_main.addItem("Mr. Game & Watch");
		comboBox_main.addItem("Mewtwo");
		comboBox_main.addItem("Zelda");
		comboBox_main.addItem("Ness");
		comboBox_main.addItem("Pichu");
		comboBox_main.addItem("Bowser");
		comboBox_main.addItem("Kirby");
		NewTagFrame.getContentPane().add(comboBox_main);
		
		JLabel lblTagError = new JLabel("");
		lblTagError.setHorizontalAlignment(SwingConstants.CENTER);
		lblTagError.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblTagError.setForeground(Color.RED);
		lblTagError.setBounds(15, 172, 548, 20);
		NewTagFrame.getContentPane().add(lblTagError);
		
		JButton btnCreateTag = new JButton("Create");
		btnCreateTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Boolean tagExists = false;
				String tagText = text_tag.getText();
				ResultSet rs = smashDB.displayPlayers();
				try {
					while(rs.next()){
						if(rs.getString("tag").equals(tagText)) tagExists = true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if(tagText != "" && !tagExists){
					
					smashDB.addPlayer(tagText);
					smashDB.addCharacter(tagText, (String) comboBox_main.getSelectedItem(), true);
					
					NewTagFrame.dispose();
				}else{
					lblTagError.setText("The tag entered is invalid. Please try again.");
				}
			}
		});
		btnCreateTag.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCreateTag.setBounds(232, 350, 115, 40);
		NewTagFrame.getContentPane().add(btnCreateTag);
		
	}
}
