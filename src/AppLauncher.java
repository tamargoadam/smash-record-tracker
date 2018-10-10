import java.awt.EventQueue;

public class AppLauncher {
	//Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SmashAppWindow window = new SmashAppWindow();
					window.frmWelcomeToSmash.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
