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

public class DetailsPanel extends JPanel {
	
	StoreStorage newProduct = new StoreStorage();
	
	public DetailsPanel() {
		//set the dimensions of the panel
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);
		
		//set a border around it
		setBorder(BorderFactory.createTitledBorder("Add Product Details"));
		
		//Labels to put text
		JLabel nameLabel = new JLabel("Name: ");
		JLabel prodIdLabel = new JLabel("Pro Id: ");
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
				System.out.println(named);
				System.out.println(priced);
				System.out.println(quan);
				System.out.println(re);
			}
		});
				
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
		add(addProdBtn, gc);

//		gc.gridx = 2;
//		gc.gridy = 1;
//		add(addPrdIdBtn, gc);

	}
}
