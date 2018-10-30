import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;

public class StatsWindow extends JFrame {

	private JPanel contentPane;

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
		
		JPanel stataPanel = new JPanel();
		stataPanel.setBackground(new Color(65, 105, 225));
		stataPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		stataPanel.setBounds(55, 132, 528, 398);
		contentPane.add(stataPanel);
		stataPanel.setLayout(null);
		
		JPanel TWLPanel = new JPanel();
		TWLPanel.setBounds(15, 16, 249, 122);
		stataPanel.add(TWLPanel);
		TWLPanel.setLayout(null);
		
		JPanel PWLPanel = new JPanel();
		PWLPanel.setBounds(15, 136, 249, 122);
		stataPanel.add(PWLPanel);
		
		JPanel CWLPanel = new JPanel();
		CWLPanel.setBounds(15, 259, 249, 122);
		stataPanel.add(CWLPanel);
		
		JPanel TWLStatPanel = new JPanel();
		TWLStatPanel.setBounds(264, 16, 249, 122);
		stataPanel.add(TWLStatPanel);
		
		JPanel PWLStatPanel = new JPanel();
		PWLStatPanel.setBounds(264, 136, 249, 122);
		stataPanel.add(PWLStatPanel);
		
		JPanel CWLStatPanel = new JPanel();
		CWLStatPanel.setBounds(264, 259, 249, 122);
		stataPanel.add(CWLStatPanel);
		
		JLabel lblNewLabel_3 = new JLabel("User's Statistics");
		lblNewLabel_3.setForeground(new Color(65, 105, 225));
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.BOLD, 28));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(96, 16, 446, 80);
		contentPane.add(lblNewLabel_3);
		
		JButton btnLoadStats = new JButton("Load Statistics");
		btnLoadStats.setBounds(239, 550, 160, 50);
		contentPane.add(btnLoadStats);
	}
}
