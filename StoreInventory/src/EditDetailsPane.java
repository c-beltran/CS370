import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

public class EditDetailsPane extends JPanel {

	StoreStorage editProduct = StoreStorage.getProdDB();
	public EventListenerList listenerList = new EventListenerList();

	public EditDetailsPane(){
		
		//set a border around it
		setBorder(BorderFactory.createTitledBorder("Edit/Remove Products"));

		//set layout manager
		setLayout(new BorderLayout());
		
		//Labels for product update
		JLabel prodIdLabel = new JLabel("Product Id: ");
		JLabel prodCategoryLabel = new JLabel("Category: ");
		JLabel prodUpdateLabel = new JLabel("Update: ");
		
		//textfields for product update
		final JTextField prodIdField = new JTextField(10);
		final JTextField prodCategoryField = new JTextField(10);
		final JTextField prodUpdateField = new JTextField(10);
		
		//labels for product removal
		JLabel remIdLabel = new JLabel("Product Id to be removed: ");
		JLabel remCategoryLabel = new JLabel("Category: ");
		JLabel remProdLabel = new JLabel("Remove: ");
		
		//textfield for product removal
		final JTextField remIdField = new JTextField(10);
		final JTextField remCategoryField = new JTextField(10);
		final JTextField removeProdField = new JTextField(10);
		
		//button to add
		JButton updateProdBtn = new JButton("UPDATE PRODUCT");
		JButton removeProdBtn = new JButton("REMOVE PRODUCT");
		
		//set the lay out
		setLayout(new GridBagLayout());
		
		//GridBagConstraints is a class where you specify where you want each control to go
		GridBagConstraints gc = new GridBagConstraints();
		
		/*this function adds a product to the db*/
		updateProdBtn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				String prodIdString = prodIdField.getText();
				int prodId = Integer.parseInt(prodIdString);
				String category = prodCategoryField.getText();
				String updatedContent = prodUpdateField.getText();
				
				String getname = editProduct.getName(prodId);
				System.out.println("GETTING NAME: " + getname);
				System.out.println(prodId + " " + category + " " + updatedContent);
				
				editProduct.updateProduct(prodId, category, updatedContent);
				
				getname = editProduct.getName(prodId);
				System.out.println("NEW NAME IS: " + getname);
				
				//testing
				String named = editProduct.getName(prodId);
				String priced = editProduct.getPrice(prodId);
				String quan = editProduct.getQuantity(prodId);
				String re = editProduct.getRemaining(prodId);
				
				String show = "THE FOLLOWING PRODUCT WAS UPDATED \n" + named + "|" + priced + "|" + quan + "|" + re + "\n" + "\n";
				
				System.out.println("PRINT SHOW: " + show + "\n");
				
				//firing
				fireDetailEvent(new DetailEvent(this, show));

				//testing output
				System.out.println(named);
				System.out.println(priced);
				System.out.println(quan);
				System.out.println(re);
			}
		});//end of addProdBtn
		
		removeProdBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String remIdString = prodIdField.getText();
				int prodId = Integer.parseInt(remIdString);
				
				//testing
				String named = editProduct.getName(prodId);
				String priced = editProduct.getPrice(prodId);
				String quan = editProduct.getQuantity(prodId);
				String re = editProduct.getRemaining(prodId);
				
				String show = "THE FOLLOWING PRODUCT WAS REMOVED \n" + named + "|" + priced + "|" + quan + "|" + re + "\n" + "\n";
				
				editProduct.removeProduct(prodId);
				
				fireDetailEvent(new DetailEvent(this, show));
			}
			
		});
		
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
	
	public void fireDetailEvent(DetailEvent event){
		Object[] listeners = listenerList.getListenerList();
		
		for(int i=0; i < listeners.length; i++){
			if(listeners[i] == DetailListener.class){
				((DetailListener)listeners[i+1]).detailEventOccurred(event);
			}
		}
	}
	
	public void editDetailListener(DetailListener listener){
		listenerList.add(DetailListener.class, listener);
	}
	
	public void removeDetailListener(DetailListener listener){
		listenerList.remove(DetailListener.class, listener);
	}
}
