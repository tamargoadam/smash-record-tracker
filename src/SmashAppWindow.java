import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class SmashAppWindow {

	JFrame frmWelcomeToSmash;
	private JTextField textFldTag;
	public static String user;
	
	DataBase smashDB = new DataBase();

	//Create the application.
	 
	public SmashAppWindow() {
		initialize();
	}

	//Initialize the contents of the frame.
	 
	private void initialize() {
		frmWelcomeToSmash = new JFrame();
		frmWelcomeToSmash.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmWelcomeToSmash.getContentPane().setForeground(new Color(0, 0, 0));
		frmWelcomeToSmash.setTitle("Welcome to Smash Tracker");
		frmWelcomeToSmash.setBounds(100, 100, 1030, 649);
		frmWelcomeToSmash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeToSmash.getContentPane().setLayout(null);
		
		JLabel lblMeleeBanner = new JLabel(new ImageIcon(".\\images\\SmashBrosSymbol.jpg"));
		lblMeleeBanner.setSize(983, 230);
		lblMeleeBanner.setLocation(10, 94);
		frmWelcomeToSmash.getContentPane().add(lblMeleeBanner);
		
		JLabel lblSmashTracker = new JLabel("SMASH TRACKER");
		lblSmashTracker.setForeground(new Color(65, 105, 225));
		lblSmashTracker.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmashTracker.setFont(new Font("Segoe UI Black", Font.BOLD, 60));
		lblSmashTracker.setBounds(0, 0, 1008, 118);
		frmWelcomeToSmash.getContentPane().add(lblSmashTracker);
		
		JLabel lblYourTag = new JLabel("Enter Your Tag : ");
		lblYourTag.setForeground(new Color(65, 105, 225));
		lblYourTag.setHorizontalAlignment(SwingConstants.TRAILING);
		lblYourTag.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblYourTag.setBounds(129, 340, 222, 40);
		frmWelcomeToSmash.getContentPane().add(lblYourTag);
		
		textFldTag = new JTextField();
		textFldTag.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFldTag.setBounds(366, 340, 491, 40);
		frmWelcomeToSmash.getContentPane().add(textFldTag);
		textFldTag.setColumns(10);
		
		JLabel lblTagNotFound = new JLabel("");
		lblTagNotFound.setForeground(new Color(255, 0, 0));
		lblTagNotFound.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblTagNotFound.setHorizontalAlignment(SwingConstants.CENTER);
		lblTagNotFound.setBounds(15, 486, 978, 40);
		frmWelcomeToSmash.getContentPane().add(lblTagNotFound);
		
		JButton btnPlaySmash = new JButton("PLAY SMASH");
		btnPlaySmash.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlaySmash.setBounds(404, 420, 200, 50);
		frmWelcomeToSmash.getContentPane().add(btnPlaySmash);
		btnPlaySmash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//if tag is not found print error else run new window
				if(!smashDB.playerExists(textFldTag.getText())){
					lblTagNotFound.setText(textFldTag.getText() + " was not found. Please try again or create an account.");
				}else if(!smashDB.hasTwoPlayers()){
					lblTagNotFound.setText("There must be two or more players in the database to log in. Please add an account.");
				}else{
					//Send to main app window here
					user = textFldTag.getText();
					frmWelcomeToSmash.dispose();
					MainAppFrame appFrame = new MainAppFrame();
					appFrame.setVisible(true);
				}
			}
		});
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Send to create tag page
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddTagWindow window = new AddTagWindow();
							window.NewTagFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnCreateAccount.setBounds(400, 542, 208, 29);
		frmWelcomeToSmash.getContentPane().add(btnCreateAccount);
		
	}
}
