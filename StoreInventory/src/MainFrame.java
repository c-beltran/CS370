import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	
	private DetailsPanel detailsPanel;

	public MainFrame(String title) {
		super(title);
		
		//set layout manager
		setLayout(new BorderLayout());
				
		//create swing component
		JTextArea textArea = new JTextArea();
		
		//panel that contains the labels and buttons.
		detailsPanel = new DetailsPanel();
		
		//add swing components to content pane
		Container con = getContentPane();
		
		con.add(textArea, BorderLayout.CENTER);
		con.add(detailsPanel, BorderLayout.WEST);
		
		//add behavior 
		
	}

}
