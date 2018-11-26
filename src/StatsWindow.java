import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class StatsWindow extends JFrame {

	DataBase smashDB = new DataBase();
	
	private JPanel contentPane;
	private JTextField txtPWL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatsWindow frame = new StatsWindow();
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
	public StatsWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 680);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ResultSet rs;
		
		JPanel stataPanel = new JPanel();
		stataPanel.setBackground(new Color(65, 105, 225));
		stataPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		stataPanel.setBounds(55, 132, 528, 398);
		contentPane.add(stataPanel);
		stataPanel.setLayout(null);
		
		JPanel TWLPanel = new JPanel();
		TWLPanel.setBounds(15, 14, 249, 122);
		stataPanel.add(TWLPanel);
		TWLPanel.setLayout(null);
		
		JLabel lblTWL = new JLabel("Total Wins/Losses:");
		lblTWL.setHorizontalAlignment(SwingConstants.CENTER);
		lblTWL.setBounds(0, 0, 249, 122);
		TWLPanel.add(lblTWL);
		
		JPanel PWLPanel = new JPanel();
		PWLPanel.setBounds(15, 137, 249, 122);
		stataPanel.add(PWLPanel);
		PWLPanel.setLayout(null);
		
		JLabel lblPWL = new JLabel("Wins/Losses Against:");
		lblPWL.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPWL.setHorizontalAlignment(SwingConstants.CENTER);
		lblPWL.setBounds(0, 0, 249, 61);
		PWLPanel.add(lblPWL);
		
		txtPWL = new JTextField();
		txtPWL.setHorizontalAlignment(SwingConstants.CENTER);
		txtPWL.setText("Enter Tag");
		txtPWL.setBounds(51, 66, 146, 26);
		PWLPanel.add(txtPWL);
		txtPWL.setColumns(10);
		
		JPanel CWLPanel = new JPanel();
		CWLPanel.setBounds(15, 260, 249, 122);
		stataPanel.add(CWLPanel);
		CWLPanel.setLayout(null);
		
		JLabel lblCWL1 = new JLabel("Wins/Losses Playing As:");
		lblCWL1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCWL1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCWL1.setBounds(0, 0, 249, 31);
		CWLPanel.add(lblCWL1);
		
		JLabel lblCWL2 = new JLabel("Against:");
		lblCWL2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCWL2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCWL2.setBounds(0, 60, 249, 20);
		CWLPanel.add(lblCWL2);
		
		JComboBox<String> CWLComboBox1 = new JComboBox<String>();
		CWLComboBox1.setBounds(51, 32, 146, 26);
		CWLPanel.add(CWLComboBox1);
		//Make main default
		CWLComboBox1.addItem(smashDB.findMain(SmashAppWindow.user));
		//Fill with other chars
		rs = smashDB.displayCharactersByPlayer();
		try {
			while(rs.next()){
				if(rs.getString("tag").equals(SmashAppWindow.user) && !rs.getString("name").equals(smashDB.findMain(SmashAppWindow.user))) {
					CWLComboBox1.addItem(rs.getString("name"));
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JComboBox<String> CWLComboBox2 = new JComboBox<String>();
		CWLComboBox2.setBounds(51, 80, 146, 26);
		CWLPanel.add(CWLComboBox2);
		rs = smashDB.displayCharacters();
		try {
			while(rs.next()){
				CWLComboBox2.addItem(rs.getString("name"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JPanel TWLStatPanel = new JPanel();
		TWLStatPanel.setBounds(264, 14, 249, 122);
		stataPanel.add(TWLStatPanel);
		TWLStatPanel.setLayout(null);
		
		JLabel lblTStatW = new JLabel("Wins: " + smashDB.totalWins(SmashAppWindow.user));
		lblTStatW.setHorizontalAlignment(SwingConstants.CENTER);
		lblTStatW.setBounds(0, 0, 249, 61);
		TWLStatPanel.add(lblTStatW);
		
		JLabel lblTStatL = new JLabel("Losses: " + smashDB.totalLosses(SmashAppWindow.user));
		lblTStatL.setHorizontalAlignment(SwingConstants.CENTER);
		lblTStatL.setBounds(0, 61, 249, 61);
		TWLStatPanel.add(lblTStatL);
		
		JPanel PWLStatPanel = new JPanel();
		PWLStatPanel.setBounds(264, 137, 249, 122);
		stataPanel.add(PWLStatPanel);
		PWLStatPanel.setLayout(null);
		
		JLabel lblPStatW = new JLabel("Wins: " + smashDB.PlayerWins(SmashAppWindow.user, txtPWL.getText()));
		lblPStatW.setHorizontalAlignment(SwingConstants.CENTER);
		lblPStatW.setBounds(0, 0, 249, 61);
		PWLStatPanel.add(lblPStatW);
		
		JLabel lblPStatL = new JLabel("Losses: " + smashDB.PlayerLosses(SmashAppWindow.user, txtPWL.getText()));
		lblPStatL.setHorizontalAlignment(SwingConstants.CENTER);
		lblPStatL.setBounds(0, 61, 249, 61);
		PWLStatPanel.add(lblPStatL);
		
		JPanel CWLStatPanel = new JPanel();
		CWLStatPanel.setBounds(264, 260, 249, 122);
		stataPanel.add(CWLStatPanel);
		CWLStatPanel.setLayout(null);
		
		JLabel lblCStatW = new JLabel("Wins: " + smashDB.CharVSCharWins(SmashAppWindow.user, (String) CWLComboBox1.getSelectedItem(), (String) CWLComboBox2.getSelectedItem()));
		lblCStatW.setHorizontalAlignment(SwingConstants.CENTER);
		lblCStatW.setBounds(0, 0, 249, 61);
		CWLStatPanel.add(lblCStatW);
		
		JLabel lblCStatL = new JLabel("Losses: " + smashDB.CharVSCharLosses(SmashAppWindow.user, (String) CWLComboBox1.getSelectedItem(), (String) CWLComboBox2.getSelectedItem()));
		lblCStatL.setHorizontalAlignment(SwingConstants.CENTER);
		lblCStatL.setBounds(0, 61, 249, 61);
		CWLStatPanel.add(lblCStatL);
		
		JLabel lblUser = new JLabel(SmashAppWindow.user + "'s Statistics");
		lblUser.setForeground(new Color(65, 105, 225));
		lblUser.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(96, 16, 446, 80);
		contentPane.add(lblUser);
		
		JButton btnLoadStats = new JButton("Load Statistics");
		btnLoadStats.setBounds(239, 550, 160, 50);
		contentPane.add(btnLoadStats);
		
		btnLoadStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//Reload total wins/losses
				lblTStatW.setText("Wins: " + smashDB.totalWins(SmashAppWindow.user));
				lblTStatL.setText("Losses: " + smashDB.totalLosses(SmashAppWindow.user));
				
				//load wins/losses against specified player
				lblPStatW.setText("Wins: " + smashDB.PlayerWins(SmashAppWindow.user, txtPWL.getText()));
				lblPStatL.setText("Losses: " + smashDB.PlayerLosses(SmashAppWindow.user, txtPWL.getText()));
				
				//load wins/losses of specified character match up
				lblCStatW.setText("Wins: " + smashDB.CharVSCharWins(SmashAppWindow.user, (String) CWLComboBox1.getSelectedItem(), (String) CWLComboBox2.getSelectedItem()));
				lblCStatL.setText("Losses: " + smashDB.CharVSCharLosses(SmashAppWindow.user, (String) CWLComboBox1.getSelectedItem(), (String) CWLComboBox2.getSelectedItem()));
			}
		});
		
	}
}
