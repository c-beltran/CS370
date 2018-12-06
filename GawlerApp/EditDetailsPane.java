import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;
/**
 * @author Carlos Alberto
 *Contains important functions of GUI
 *such as edit and remove upload 
 *functionalities
 */
public class EditDetailsPane extends JPanel {

	gameStopDB InitDB = gameStopDB.getProdDB();
	Utility Util = new Utility();
	public EventListenerList listenerList = new EventListenerList();
	boolean isUserAdmin = UserRegistration.getUser();

	public EditDetailsPane(){
		
		//set a border around it
		setBorder(BorderFactory.createTitledBorder("Edit/Remove Search Queries"));

		//set layout manager
		setLayout(new BorderLayout());
		
		//Labels for product update
		JLabel prodIdLabel = new JLabel("Search Id: ");
		JLabel prodCategoryLabel = new JLabel("Category: ");
		JLabel prodUpdateLabel = new JLabel("Update: ");
		
		//textfields for product update
		final JTextField prodIdField = new JTextField(10);
		final JTextField prodCategoryField = new JTextField(10);
		final JTextField prodUpdateField = new JTextField(10);
		
		//labels for product removal
		JLabel remIdLabel = new JLabel("Search Id to be removed: ");

		//textfield for product removal
		final JTextField remIdField = new JTextField(10);
		final JTextField remCategoryField = new JTextField(10);
		final JTextField removeProdField = new JTextField(10);
		
		//button to add
		JButton updateProdBtn = new JButton("UPDATE SEARCH");
		JButton removeProdBtn = new JButton("REMOVE SEARCH");
		
		//set the lay out
		setLayout(new GridBagLayout());
		
		//GridBagConstraints is a class where you specify where you want each control to go
		GridBagConstraints gc = new GridBagConstraints();
		
		/*this function adds a product to the db*/
		updateProdBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				if(isUserAdmin == true){
					String prodIdString = prodIdField.getText();
					int prodId = Integer.parseInt(prodIdString);
					String category = prodCategoryField.getText();
					String updatedContent = prodUpdateField.getText();
					
					InitDB.updateSearch(prodId, category, updatedContent);
					
					String search = InitDB.getSearchTerm(prodId);
					String gameTitle = InitDB.getGameTitle(prodId);
					String consoleType = InitDB.getConsoleType(prodId);
					
					String time = timeStamp();
					String show = "THE FOLLOWING SEARCH WAS UPDATED \n" + "SearchID: " + prodId + "| SearchTerm: " + search + "| " + time + "\n" + "\n";
									
					//fire event
					fireDetailEvent(new DetailEvent(this, show));
					
					//update log file
					try {
						Util.updateLog(show);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						InitDB.queryLogFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Sorry, You Must Be Admin User to Use This Feature");
				}
			}
		});//end of addProdBtn
		
		removeProdBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(isUserAdmin == true){
					// TODO Auto-generated method stub
					String remIdString = remIdField.getText();
					int prodId = Integer.parseInt(remIdString);
					
					String search = InitDB.getSearchTerm(prodId);
					String gameTitle = InitDB.getGameTitle(prodId);
					String consoleType = InitDB.getGameTitle(prodId);
	//				String re = InitDB.getConsoleType(prodId);
					String time = timeStamp();
					String show = "THE FOLLOWING PRODUCT WAS REMOVED \n" + "SearchID: " + prodId + "| SearchTerm: " + search + "| " + time + "\n" + "\n";
					
					InitDB.removeProduct(prodId);
					
					//fire event
					fireDetailEvent(new DetailEvent(this, show));
					
					//update log file
					try {
						Util.updateLog(show);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						InitDB.queryLogFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Sorry, You Must Be Admin User to Use This Feature");
				}
			}
			
		});//end of removeProdBtn
		
		//========SETTING UP CONTROLS=====//
		
		//FIRST COLUMN//
		//separating into x,y grids
		// y goes from up down
		//x goes from left to right
		
		gc.anchor = GridBagConstraints.LINE_END; //Aligns right
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy =0;
		add(prodIdLabel, gc);
		
		gc.gridx =0;
		gc.gridy = 1; //goes to next row down
		add(prodCategoryLabel, gc);
		
		gc.gridx =0;
		gc.gridy = 2; //goes to next row down
		add(prodUpdateLabel, gc);
		
		//SECOND COLUMN//
		gc.anchor = GridBagConstraints.LINE_START; //Aligns left
		gc.gridx = 1;
		gc.gridy = 0;
		add(prodIdField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(prodCategoryField, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(prodUpdateField, gc);
		
		//LAST ROW OF SECOND COLUMN//
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 3;
		//add product button
		add(updateProdBtn, gc);
		
		//THIRD COLUMN FOR REMOVE//
		gc.anchor = GridBagConstraints.LINE_END; //Aligns right
		gc.gridx = 4;
		gc.gridy = 0;
		add(remIdLabel, gc);
		
		//FIFTH COLUMN FOR REMOVE
		gc.anchor = GridBagConstraints.LINE_START; //Aligns left
		gc.gridx = 5;
		gc.gridy = 0;
		add(remIdField, gc);
		
		//LAST ROW OF THIRD COLUMN//
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 5;
		gc.gridy = 1;
		//add product button
		add(removeProdBtn, gc);
	}
	
	private String timeStamp() {
		Date dnt = new Date(); 
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd 'at' hh:mm:ss a zzz");
	    return ft.format(dnt);
	}//end of timeStamp
	
	public void fireDetailEvent(DetailEvent event){
		Object[] listeners = listenerList.getListenerList();
		
		for(int i=0; i < listeners.length; i++){
			if(listeners[i] == DetailListener.class){
				((DetailListener)listeners[i+1]).detailEventOccurred(event);
			}
		}
	}//end of fireDetailEvent
	
	public void editDetailListener(DetailListener listener){
		listenerList.add(DetailListener.class, listener);
	}//end of editDetailListener
	
	public void removeDetailListener(DetailListener listener){
		listenerList.remove(DetailListener.class, listener);
	}//end of removeDetailListener

}//end of EditDetailsPane class
