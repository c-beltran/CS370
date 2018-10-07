import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainEditFrame extends JFrame{
	
	private EditDetailsPane editDetailsPanel;
	
	public MainEditFrame(String title){
		super(title);
		
		//set layout manager
		setLayout(new BorderLayout());
						
		//create swing component
		final JTextArea textArea = new JTextArea();
				
		////////panel that contains the labels and buttons.\\\\\\\
		editDetailsPanel = new EditDetailsPane();
		
		//add swing components to content pane
		Container con = getContentPane();
				
		con.add(editDetailsPanel, BorderLayout.WEST);
	}

}
