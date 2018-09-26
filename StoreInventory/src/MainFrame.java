import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	public MainFrame(String title) {
		super(title);
		
		//set layout manager
		setLayout(new BorderLayout());
				
		//create swing component
		JTextArea textArea = new JTextArea();
		JButton button = new JButton("Click here");
		
		//add swing components to content pane
		Container con = getContentPane();
		
		con.add(textArea, BorderLayout.CENTER);
		con.add(button, BorderLayout.SOUTH);
		
		//add behavior 
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("hello\n");
			}
		});
	}

}
