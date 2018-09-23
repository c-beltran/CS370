import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class viewGUI {
	public viewGUI(){}
	
	public void start(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Store Inventory App");
				frame.setSize(500, 400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}