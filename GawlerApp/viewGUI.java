import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
				JFrame frame = new MainFrame("GameStop Crawler");
				frame.setSize(1200,800);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}//end of start
}