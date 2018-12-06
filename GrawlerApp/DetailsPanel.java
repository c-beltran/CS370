import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.event.EventListenerList;

/**
 * @author Carlos Alberto
 *Contains important functions of GUI
 *such as add, print and file upload 
 *functionalities
 */
public class DetailsPanel extends JPanel {
	
	gameStopDB InitDB = gameStopDB.getProdDB();
	UserRegistration userReg = new UserRegistration();
	UserSettings updateUser = new UserSettings();
	Utility Util = new Utility();
	boolean isUserAdmin = UserRegistration.getUser();
	private static JFileChooser fileChooser = new JFileChooser();
	
	//java event listener list class, for creating a list of events
	public EventListenerList listenerList = new EventListenerList();
	int searchCount = 0;
	String userSearchURL= null;
	
	public DetailsPanel() {		
		int dbSize = InitDB.getLastKeyValue();
		if(dbSize > 0){
			searchCount = dbSize++;
		}
		
		/* Enabling Multiple Selection */
        fileChooser.setMultiSelectionEnabled(true);

        /* Setting Current Directory */
        fileChooser.setCurrentDirectory(new File("C:\\Documents and Settings"));
		
		//set the dimensions of the panel
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);
		
		//set a border around it
		setBorder(BorderFactory.createTitledBorder("Search"));
		
		//Labels for input text
		JLabel nameLabel = new JLabel("Search: ");
		
		final JTextField nameField = new JTextField(10);
		
		//buttons
		JButton searchdBtn = new JButton("Search!");
		JButton printProdBtn = new JButton("Print Log file");
		JButton printImgdBtn = new JButton("Download Image");
		JButton viewContentBtn = new JButton("View Online Page");
		JButton viewHistoryBtn = new JButton("Show History");
		JButton settingsBtn = new JButton("Settings");
		final JButton logOutBtn = new JButton("Log Out");
		
		/**this function adds a product to the db*/
		searchdBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				ArrayList<String> searchData = new ArrayList<String>();
				String userSearch = nameField.getText();
				userSearch = userSearch.replaceAll(" ", "_");
				String time = timeStamp();
				int prodId = ++searchCount;

				//adding to hashmap storage
				InitDB.addNewSearchTerm(prodId, userSearch);
				InitDB.setInventoryRecords(prodId, "timeStamp", time);
				String searchTerm = InitDB.getSearchTerm(prodId);
				String getListOfResults = "";	
				
				try {
					userSearch = userSearch.replaceAll(" ", "+").toLowerCase();
					userSearchURL = userSearch;
					WebpageReaderWithoutAgent.initiateReader(userSearch);
					searchData = Utility.searchItems("URLResult.txt", prodId);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				for(int i=0; i<searchData.size(); i++){
					getListOfResults = getListOfResults + "\n" + searchData.get(i);
				}
								
				String show = "THE FOLLOWING SEARCH WAS MADE \n" + "SearchID: " + prodId + "| SearchTerm: " + searchTerm + "| " + time + "\n" + getListOfResults + "\n" + "\n" ;
				
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
			}
		});//end of addProdBtn
		
		/**this function prints all the products stored in db*/
		printProdBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				if(isUserAdmin == true){
					//calls the print function to create Result.txt
					try {
						InitDB.printToTextFile();
					} catch (IOException err) {
						// TODO Auto-generated catch block
						err.printStackTrace();
					}
					String time = timeStamp();
					String show = "A LOG FILE WAS PRINTED TO PROJECT FOLDER AS 'printResult.txt' FILE | " + time + " \n" + "\n";
					
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
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry, You Must Be Admin User to Use This Feature");
				}
			}
		});//end of printProdBtn
		
		printImgdBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(isUserAdmin == true){
					int count = 0;
	//				count = openFile(count);
					
					//calls the utility function to 
					//read the html file
					try {
						Utility.imgReadFile("URLResult.txt");
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						
					}
	
					String time = timeStamp();
					String show = "AN IMAGE WAS DOWNLOADED TO THE PROJECT FOLDER | " + time + "\n" + "\n";
					
					//fire event
					fireDetailEvent(new DetailEvent(this, show));
					
					//update log file
					try {
						Util.updateLog(show);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry, You Must Be Admin User to Use This Feature");
				}
			}
		});//end of listener
		
		viewContentBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				JEditorPane jep = new JEditorPane();
			    jep.setSize(512, 342);
			    jep.setEditable(false);   

				try {
				  jep.setPage("https://www.gamestop.com/browse?nav=16k-3-" + userSearchURL);
				}catch (MalformedURLException err) {
				  jep.setContentType("text/html");
				  jep.setText("<html>Could not load</html>");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

				JScrollPane scrollPane = new JScrollPane(jep);     
				JFrame f = new JFrame("GAMESTOP");
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.getContentPane().add(scrollPane);
				f.setSize(512,342);
				f.setVisible(true);
			}
		});
		
		logOutBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				userReg.initRegistration();
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(logOutBtn);
				frame.dispose();
				MainFrame.textArea.setText("");
			}
		});
		
		viewHistoryBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(isUserAdmin == true){
					ArrayList<String> history = new ArrayList<String>();
					String getListOfResults = "";
					
					try {
						history = InitDB.printHistory();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					for(int i=0; i<history.size(); i++)
					{
						getListOfResults = getListOfResults + "\n" + history.get(i);
					}
					
					MainFrame.textArea.append("--------DISPLAYING HISTORY LOGS-------- \n");
					MainFrame.textArea.append(String.format( "%-15s%-15s%-15s%-15s%-15s%-15s\n", "SearchID", "search", "gameTitle", "consoleType", " ", "timeStamp"));
					MainFrame.textArea.append(getListOfResults);
					MainFrame.textArea.append("\n"+"\n");
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry, You Must Be Admin User to Use This Feature");
				}
			}
		});
		
		settingsBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				if(isUserAdmin == true){
					updateUser.updateUserInfo();
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry, You Must Be Admin User to Use This Feature");
				}
			}
		});
				
		//setting the lay out
		//GridBagLayout lets you add conjunctions with constraints
		setLayout(new GridBagLayout()); 
		
		//GridBagConstraints is a class where you specify where you want each control to go
		GridBagConstraints gc = new GridBagConstraints();
		
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
		add(nameLabel, gc);
		
//		gc.gridx =0;
//		gc.gridy = 1; //goes to next row down
//		add(prodIdLabel, gc);
//		
//		gc.gridx =0;
//		gc.gridy = 2; //goes to next row down
//		add(priceLabel, gc);
//		
//		gc.gridx =0;
//		gc.gridy = 3; //goes to next row down
//		add(quantityLabel, gc);
//		
//		gc.gridx =0;
//		gc.gridy = 4; //goes to next row down
//		add(remainingLabel, gc);
//		
		//SECOND COLUMN//
		gc.anchor = GridBagConstraints.LINE_START; //Aligns left
		gc.gridx = 1;
		gc.gridy = 0;
		add(nameField, gc);
		
		//LAST ROW//
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		//search button
		add(searchdBtn, gc);
		
		//view webpage button
		gc.gridx = 0;
		gc.gridy = 8;
		add(viewHistoryBtn, gc);
		
		//view webpage button
		gc.gridx = 1;
		gc.gridy = 8;
		add(viewContentBtn, gc);
		
		//print button
		gc.gridx = 0;
		gc.gridy = 9;
		add(printProdBtn, gc);
		
		//print img button
		gc.gridx = 1;
		gc.gridy = 9;
		add(printImgdBtn, gc);
		
		//account settings
		gc.gridx = 0;
		gc.gridy = 10;
		add(settingsBtn, gc);
		
		//log out
		gc.gridx = 1;
		gc.gridy = 10;
		add(logOutBtn, gc);
		
	}//end of DetailsPanel
	
	public int openFile(int count) {
		JFileChooser chooser;
		int status;
		chooser = new JFileChooser();
		status = chooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION){
			count = InitDB.initSeedsTextFile(count, chooser.getSelectedFile());
		}else{
			JOptionPane.showMessageDialog(null, "No files were selected");
		}	
		return count;
		
	} //end of openFile
	
	private String timeStamp() {
		Date dnt = new Date(); 
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd_'at'_hh:mm:ss_a_zzz");
	    return ft.format(dnt);
	}//end of timeStamp
	
	public void fireDetailEvent(DetailEvent event){
		Object[] listeners = listenerList.getListenerList();
		
		for(int i=0; i < listeners.length; i+=2){
			if(listeners[i] == DetailListener.class){
				((DetailListener)listeners[i+1]).detailEventOccurred(event);
			}
		}
	}//end of fireDetailEvent
	
	public void addDetailListener(DetailListener listener){
		listenerList.add(DetailListener.class, listener);
	}//end of addDetailListener
	
	public void seedDetailListener(DetailListener listener){
		listenerList.add(DetailListener.class, listener);
	}//end of seedDetailListener
	
	public void printDetailListener(DetailListener listener){
		listenerList.remove(DetailListener.class, listener);
	}//end of printDetailListener
	
}//end of DetailsPanel class
