import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class viewGUI {
	public viewGUI(){}
	
	public void start(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainFrame("Store Inventory App");
				frame.setSize(1200,800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}