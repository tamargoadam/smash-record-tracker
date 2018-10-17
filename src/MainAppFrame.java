import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class MainAppFrame extends JFrame {

	DataBase smashDB = new DataBase();
	
	private JPanel contentPane;
	private JTextField txtPlayer1;
	private JTextField txtPlayer2;
	JComboBox<String> comboBox_char1 = new JComboBox<String>();
	JComboBox<String> comboBox_char2 = new JComboBox<String>();


	/**
	 * Create the frame.
	 */
	public MainAppFrame() {
		
		String player1 = "";
		String player2 = "";
		String character1 = "";
		String character2 = "";
		
		ResultSet rs;
		try {
			//set starting players and characters 
			rs = smashDB.displayPlayers();
			rs.next();
			player1 = rs.getString("tag");
			character1 = smashDB.findMain(player1);
			rs.next();
			player2 = rs.getString("tag");
			character2 = smashDB.findMain(player2);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		setTitle("Smash Record Keeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 860);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblP1VSP2 = new JLabel(player1 + " VS " + player2);
		lblP1VSP2.setForeground(new Color(65, 105, 225));
		lblP1VSP2.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblP1VSP2.setHorizontalAlignment(SwingConstants.CENTER);
		lblP1VSP2.setBounds(15, 16, 628, 70);
		contentPane.add(lblP1VSP2);
		
		txtPlayer1 = new JTextField();
		txtPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer1.setText(player1);
		txtPlayer1.setBounds(15, 102, 245, 38);
		contentPane.add(txtPlayer1);
		txtPlayer1.setColumns(10);
		
		txtPlayer2 = new JTextField();
		txtPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer2.setText(player2);
		txtPlayer2.setColumns(10);
		txtPlayer2.setBounds(398, 102, 245, 38);
		contentPane.add(txtPlayer2);
		
		JLabel lblPlayerNotFound = new JLabel("");
		lblPlayerNotFound.setFont(new Font("Tahoma", Font.ITALIC, 18));
		String notFoundError = "At least one player was not found or both the players entered are the same.";
		lblPlayerNotFound.setForeground(Color.RED);
		lblPlayerNotFound.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerNotFound.setBounds(15, 197, 628, 20);
		contentPane.add(lblPlayerNotFound);
		
		JComboBox<String> comboBox_char1 = new JComboBox<String>();
		comboBox_char1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_char1.setBounds(15, 156, 245, 38);
		contentPane.add(comboBox_char1);
		rs = smashDB.displayCharactersByPlayer();
		try {
			while(rs.next()){
				if(rs.getString("tag").equals(player1)) comboBox_char1.addItem(rs.getString("name"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JComboBox<String> comboBox_char2 = new JComboBox<String>();
		comboBox_char2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_char2.setBounds(398, 156, 245, 38);
		contentPane.add(comboBox_char2);
		rs = smashDB.displayCharactersByPlayer();
		try {
			while(rs.next()){
				if(rs.getString("tag").equals(player2)) comboBox_char2.addItem(rs.getString("name"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblPlayer1 = new JLabel(player1 + "'s");
		lblPlayer1.setForeground(new Color(65, 105, 225));
		lblPlayer1.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer1.setBounds(15, 246, 300, 55);
		contentPane.add(lblPlayer1);
		
		JLabel lblPlayer2 = new JLabel(player2 + "'s");
		lblPlayer2.setForeground(new Color(65, 105, 225));
		lblPlayer2.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer2.setBounds(343, 246, 300, 55);
		contentPane.add(lblPlayer2);
		
		JLabel lblCharacter1 = new JLabel(character1);
		lblCharacter1.setForeground(new Color(65, 105, 225));
		lblCharacter1.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblCharacter1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCharacter1.setBounds(15, 289, 300, 55);
		contentPane.add(lblCharacter1);
		
		JLabel lblCharacter2 = new JLabel(character2);
		lblCharacter2.setForeground(new Color(65, 105, 225));
		lblCharacter2.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblCharacter2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCharacter2.setBounds(343, 289, 300, 55);
		contentPane.add(lblCharacter2);
		
		JPanel winPanel1 = new JPanel();
		winPanel1.setBackground(new Color(176, 196, 222));
		winPanel1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		winPanel1.setBounds(25, 360, 290, 380);
		contentPane.add(winPanel1);
		winPanel1.setLayout(null);
		
		JLabel lblNumWins1 = new JLabel(""+ smashDB.getWins(lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2), 
				lblCharacter1.getText(), lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2),
				lblCharacter2.getText()));
		lblNumWins1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 55));
		lblNumWins1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumWins1.setBounds(0, 0, 290, 245);
		winPanel1.add(lblNumWins1);
		
		JButton btnAddWin1 = new JButton("ADD WIN");
		btnAddWin1.setForeground(new Color(0, 128, 0));
		btnAddWin1.setBackground(new Color(144, 238, 144));
		btnAddWin1.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnAddWin1.setBounds(0, 242, 290, 69);
		winPanel1.add(btnAddWin1);
		btnAddWin1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO add 1 to wins in DB and load lblNumWins
				smashDB.addWin(lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2), 
						lblCharacter1.getText(), lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2),
						lblCharacter2.getText());
				lblNumWins1.setText(""+ smashDB.getWins(lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2), 
						lblCharacter1.getText(), lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2),
						lblCharacter2.getText()));
			}
		});
		
		JButton btnSubtractWin1 = new JButton("SUBTRACT WIN");
		btnSubtractWin1.setForeground(new Color(255, 0, 0));
		btnSubtractWin1.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnSubtractWin1.setBackground(new Color(255, 192, 203));
		btnSubtractWin1.setBounds(0, 311, 290, 69);
		winPanel1.add(btnSubtractWin1);
		btnSubtractWin1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Subtract 1 to wins in DB and load lblNumWins
				smashDB.subtractWin(lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2), 
						lblCharacter1.getText(), lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2),
						lblCharacter2.getText());
				lblNumWins1.setText(""+ smashDB.getWins(lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2), 
						lblCharacter1.getText(), lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2),
						lblCharacter2.getText()));
			}
		});
		
		JPanel winPanel2 = new JPanel();
		winPanel2.setBackground(new Color(176, 196, 222));
		winPanel2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		winPanel2.setBounds(353, 360, 290, 380);
		contentPane.add(winPanel2);
		winPanel2.setLayout(null);
		
		JLabel lblNumWins2 = new JLabel(""+ smashDB.getWins(lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2), 
				lblCharacter2.getText(), lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2),
				lblCharacter1.getText()));
		lblNumWins2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumWins2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 55));
		lblNumWins2.setBounds(0, 0, 290, 245);
		winPanel2.add(lblNumWins2);
		
		JButton btnAddWin2 = new JButton("ADD WIN");
		btnAddWin2.setForeground(new Color(0, 128, 0));
		btnAddWin2.setBackground(new Color(144, 238, 144));
		btnAddWin2.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnAddWin2.setBounds(0, 242, 290, 69);
		winPanel2.add(btnAddWin2);
		btnAddWin2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Add 1 to wins in DB and load lblNumWins
				smashDB.addWin(lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2), 
						lblCharacter2.getText(), lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2),
						lblCharacter1.getText());
				lblNumWins2.setText(""+ smashDB.getWins(lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2), 
						lblCharacter2.getText(), lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2),
						lblCharacter1.getText()));
			}
		});
		
		JButton btnSubtractWin2 = new JButton("SUBTRACT WIN");
		btnSubtractWin2.setForeground(new Color(255, 0, 0));
		btnSubtractWin2.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnSubtractWin2.setBackground(new Color(255, 182, 193));
		btnSubtractWin2.setBounds(0, 311, 290, 69);
		winPanel2.add(btnSubtractWin2);
		btnSubtractWin2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Subtract 1 to wins in DB and load lblNumWins
				smashDB.subtractWin(lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2), 
						lblCharacter2.getText(), lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2),
						lblCharacter1.getText());
				lblNumWins2.setText(""+ smashDB.getWins(lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2), 
						lblCharacter2.getText(), lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2),
						lblCharacter1.getText()));
			}
		});
		
		JButton btnLoadData = new JButton("Load");
		btnLoadData.setBounds(275, 102, 108, 38);
		contentPane.add(btnLoadData);
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//if tag is not found print error else change data
				if(!smashDB.playerExists(txtPlayer1.getText()) || !smashDB.playerExists(txtPlayer2.getText())
						|| txtPlayer1.getText().equals(txtPlayer2.getText())){
					lblPlayerNotFound.setText(notFoundError);
				}else{
					
					lblPlayerNotFound.setText("");
					//TODO change data here
					try {
						//Change combo boxes to match player if needed
						//P1
						ResultSet res = smashDB.displayCharactersByPlayer();
						if(!(lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2)).equals(txtPlayer1.getText())){
							comboBox_char1.removeAllItems();
							while(res.next()){
								if(res.getString("tag").equals(txtPlayer1.getText())){
									comboBox_char1.addItem(res.getString("name"));
								}
							}
						}else 
							//P2	
							if(!(lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2)).equals(txtPlayer2.getText())){
							res = smashDB.displayCharactersByPlayer();
							comboBox_char2.removeAllItems();
							while(res.next()){
								if(res.getString("tag").equals(txtPlayer2.getText())){
									comboBox_char2.addItem(res.getString("name"));
								}
							}
						}
						
						//Update characters in case any were added
						if((lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2)).equals(txtPlayer1.getText())){
							res = smashDB.displayCharactersByPlayer();
							Object selCom = comboBox_char1.getSelectedItem();
							comboBox_char1.removeAllItems();
							while(res.next()){
								if(res.getString("tag").equals(txtPlayer1.getText())){
									comboBox_char1.addItem(res.getString("name"));
								}
							}
							comboBox_char1.setSelectedItem(selCom);
						}
						if((lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2)).equals(txtPlayer2.getText())){
							res = smashDB.displayCharactersByPlayer();
							Object selCom = comboBox_char2.getSelectedItem();
								comboBox_char2.removeAllItems();
								while(res.next()){
									if(res.getString("tag").equals(txtPlayer2.getText())){
										comboBox_char2.addItem(res.getString("name"));
									}
								}
							comboBox_char2.setSelectedItem(selCom);
						}
						
						//Change tag and character labels
						lblP1VSP2.setText(txtPlayer1.getText() + " VS " + txtPlayer2.getText());
						lblPlayer1.setText(txtPlayer1.getText() + "'s");
						lblPlayer2.setText(txtPlayer2.getText() + "'s");
						lblCharacter1.setText((String) comboBox_char1.getSelectedItem());
						lblCharacter2.setText((String) comboBox_char2.getSelectedItem());
						
						//Change num wins labels
						lblNumWins1.setText(""+ smashDB.getWins(lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2), 
								lblCharacter1.getText(), lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2),
								lblCharacter2.getText()));
						lblNumWins2.setText(""+ smashDB.getWins(lblPlayer2.getText().substring(0, lblPlayer2.getText().length() - 2), 
								lblCharacter2.getText(), lblPlayer1.getText().substring(0, lblPlayer1.getText().length() - 2),
								lblCharacter1.getText()));
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		JButton btnAddATag = new JButton("Add a Tag");
		btnAddATag.setBounds(275, 156, 108, 38);
		contentPane.add(btnAddATag);
		btnAddATag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Send to create tag page
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddTagWindow window = new AddTagWindow();
							window.NewTagFrame.setVisible(true);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				});
			}
		});
		
		JButton btnAddACharacter = new JButton("Add a Character");
		btnAddACharacter.setBounds(250, 222, 158, 38);
		contentPane.add(btnAddACharacter);
		btnAddACharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Send to add character page
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddCharFrame window = new AddCharFrame();
							window.NewCharFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

	}
	
}
