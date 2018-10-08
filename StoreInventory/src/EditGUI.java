import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class EditGUI{
	public EditGUI() {}
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				JFrame editFrame = new MainEditFrame("Edit Product Details");
				editFrame.setSize(300, 300);
				editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				editFrame.setVisible(true);
			}
		});
	}
}
