import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Carlos Alberto
 * This class acts as the controller
 * which listens and tells the objects what to do
 */

public class MainFrame extends JFrame {
	
	private DetailsPanel detailsPanel;
	private EditDetailsPane editDetailsPanel;
	public static JTextArea textArea = new JTextArea(10, 10);
	
	public MainFrame(String title) {
		super(title);
		
		//set layout manager
		getContentPane().setLayout(new BorderLayout());         
		
		////////panel that contains the labels and buttons.\\\\\\\
		detailsPanel = new DetailsPanel();
		editDetailsPanel = new EditDetailsPane();
		
		//adding a listener to detailsPanel to respond to event added to detailsPanel
		detailsPanel.addDetailListener(new DetailListener(){
			
			//this will contain the information of that detail event
			public void detailEventOccurred(DetailEvent event) {
				String show = event.getText();
				textArea.append(show);
			}
		});//end of listener
		
		detailsPanel.printDetailListener(new DetailListener(){
			
			//this will contain the information of that detail event
			public void detailEventOccurred(DetailEvent event) {
				String show = event.getText();
				
				textArea.append(show);
			}
		});//end of listener
				
		editDetailsPanel.editDetailListener(new DetailListener(){
			
			//this will contain the information of that detail event
			public void detailEventOccurred(DetailEvent event) {
				String show = event.getText();
								
				textArea.append(show);
			}
		});//end of listener
		
		editDetailsPanel.removeDetailListener(new DetailListener(){

			//this will contain the information of that detail event
			public void detailEventOccurred(DetailEvent event) {
				String show = event.getText();
						
				textArea.append(show);
			}
		});//end of listener
		
		//add swing components to content pane
		Container con = getContentPane();
		con.add(textArea, BorderLayout.CENTER);
		con.add(detailsPanel, BorderLayout.WEST);
		con.add(editDetailsPanel, BorderLayout.SOUTH);
		
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setVisible(true);
		 
		//create swing component
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void close(){
   	 
   	 	WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
   	 	Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
   	 
   	 }

}
