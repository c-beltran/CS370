import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

/**
 * @author Carlos Alberto
 *This is the main GUI class
 *which sets the layout
 */
public class viewGUI {
	public viewGUI(){}
	
	/**
	 *Initiates the main GUI
	 *@start 
	 */
	public void start(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainFrame("Store Inventory App");
				frame.setSize(1200,800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}//end of start
}