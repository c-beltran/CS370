import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

/**
 * @author Carlos Alberto
 *Contains important functions of GUI
 *such as add, print and file upload 
 *functionalities
 */
public class DetailsPanel extends JPanel {
	
	StoreStorage newProduct = StoreStorage.getProdDB();
	//java event listener list class is for creating a list of events
	public EventListenerList listenerList = new EventListenerList();
	private static JFileChooser fileChooser = new JFileChooser();
	
	public DetailsPanel() {
		
		/* Enabling Multiple Selection */
        fileChooser.setMultiSelectionEnabled(true);

        /* Setting Current Directory */
        fileChooser.setCurrentDirectory(new File("C:\\Documents and Settings"));
		
		//set the dimensions of the panel
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);
		
		//set a border around it
		setBorder(BorderFactory.createTitledBorder("Add Product"));
		
		//Labels to put text
		JLabel nameLabel = new JLabel("Name: ");
		JLabel prodIdLabel = new JLabel("Prod Id: ");
		JLabel priceLabel = new JLabel("Price: ");
		JLabel quantityLabel = new JLabel("Quantity: ");
		JLabel remainingLabel = new JLabel("Remaining: ");
		
		final JTextField nameField = new JTextField(10);
		final JTextField prodIdField = new JTextField(10);
		final JTextField priceField = new JTextField(10);
		final JTextField quantityField = new JTextField(10);
		final JTextField remainingField = new JTextField(10);
		
		//button to add
		JButton addProdBtn = new JButton("Add Product");
		JButton printProdBtn = new JButton("Print");
		JButton fileProdBtn = new JButton("File Upload");
		
		/*this function adds a product to the db*/
		addProdBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				String name = nameField.getText();
				String prodIdString = prodIdField.getText();
				int prodId = Integer.parseInt(prodIdString);
				String price = priceField.getText();
				String quantity = quantityField.getText();
				String remaining = remainingField.getText();
				
				//adding to hashmap storage
				newProduct.addNewProduct(prodId, name);
				newProduct.setInventoryRecords(prodId, "prodId", prodIdString);
				newProduct.setInventoryRecords(prodId, "price", price);
				newProduct.setInventoryRecords(prodId, "quantity", quantity);
				newProduct.setInventoryRecords(prodId, "remaining", remaining);
				
				
				String named = newProduct.getName(prodId);
				String priced = newProduct.getPrice(prodId);
				String quan = newProduct.getQuantity(prodId);
				String re = newProduct.getRemaining(prodId);
				
				String time = timeStamp();
				
				String show = "THE FOLLOWING PRODUCT WAS ADDED \n" + named + "| " + priced + "| " + quan + "| " + re + "| " + time + "\n" + "\n";
				
				//firing
				fireDetailEvent(new DetailEvent(this, show));

			}
		});//end of addProdBtn
		
		printProdBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
			
				try {
					newProduct.printToTextFile();
				} catch (IOException err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
				}
				
				String time = timeStamp();
				
				String show = "ALL PRODUCTS WERE PRINTED TO 'Result.txt' FILE | " + time + " \n" + "\n";
				
				fireDetailEvent(new DetailEvent(this, show));
				
			}
		});//end of printProdBtn
		
		fileProdBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				int count = 0;
				
				String time = timeStamp();
				
				count = openFile(count);
				
				String show = "Seed File Initiated.... " + count + " Records were Added! | " + time + "\n" + "\n";
				
				fireDetailEvent(new DetailEvent(this, show));
			}
		});//end of listener
				
		//set the lay out
		setLayout(new GridBagLayout()); //GridBagLayout lets you add conjunctions with constraints
		
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
		
		gc.gridx =0;
		gc.gridy = 1; //goes to next row down
		add(prodIdLabel, gc);
		
		gc.gridx =0;
		gc.gridy = 2; //goes to next row down
		add(priceLabel, gc);
		
		gc.gridx =0;
		gc.gridy = 3; //goes to next row down
		add(quantityLabel, gc);
		
		gc.gridx =0;
		gc.gridy = 4; //goes to next row down
		add(remainingLabel, gc);
		
		//SECOND COLUMN//
		gc.anchor = GridBagConstraints.LINE_START; //Aligns left
		gc.gridx = 1;
		gc.gridy = 0;
		add(nameField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(prodIdField, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(priceField, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		add(quantityField, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		add(remainingField, gc);
		
		//LAST ROW//
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 5;
		//add product button
		add(addProdBtn, gc);
		
		//print button
		gc.gridx = 0;
		gc.gridy = 10;
		add(printProdBtn, gc);
		
		//file upload button
		gc.gridx = 1;
		gc.gridy = 10;
		add(fileProdBtn, gc);

	}
	
	public int openFile(int count) {

		JFileChooser chooser;
		int status;
		chooser = new JFileChooser();
		status = chooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION)
			count = newProduct.initSeedsTextFile(count, chooser.getSelectedFile());
		else{
			JOptionPane.showMessageDialog(null, "No files were selected");
		}
		
		return count;
		
	} //end of openFile
	
	private String timeStamp() {
		Date dnt = new Date(); 
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd 'at' hh:mm:ss a zzz");
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
	
}//end of DetailsPanel
