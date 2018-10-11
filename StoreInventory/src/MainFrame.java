import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * @author Carlos Alberto
 * This class acts as the controller
 * which listens and tells the objects what to do
 */

public class MainFrame extends JFrame {
	
	private DetailsPanel detailsPanel;
	private EditDetailsPane editDetailsPanel;
	
	
	public MainFrame(String title) {
		super(title);
		
		//set layout manager
		getContentPane().setLayout(new BorderLayout());         
				
		//create swing component
		final JTextArea textArea = new JTextArea(10, 10);		
		
		////////panel that contains the labels and buttons.\\\\\\\
		detailsPanel = new DetailsPanel();
		editDetailsPanel = new EditDetailsPane();
		
		//adding a listener to detailsPanel to respond to event added to detailsPanel
		detailsPanel.addDetailListener(new DetailListener(){
			//this will contain the information of that detail event
			@Override
			public void detailEventOccurred(DetailEvent event) {
				String show = event.getText();
				
				textArea.append(show);
			}
		});
		
		detailsPanel.printDetailListener(new DetailListener(){
			//this will contain the information of that detail event
			@Override
			public void detailEventOccurred(DetailEvent event) {
				String show = event.getText();
				
				textArea.append(show);
			}
		});//end of listener
		
		editDetailsPanel.editDetailListener(new DetailListener(){

			@Override
			public void detailEventOccurred(DetailEvent event) {
				String show = event.getText();
								
				textArea.append(show);
			}
		});//end of listener
		
		editDetailsPanel.removeDetailListener(new DetailListener(){

			@Override
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
	}

}
