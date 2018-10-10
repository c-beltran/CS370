import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utility extends JFrame {
	
    private static JButton button = new JButton("Open");
    private static JFileChooser fileChooser = new JFileChooser();
	private Scanner seed;

    StoreStorage storage = StoreStorage.getProdDB();
    
    public void startJFile() {
        add(button);
        setSize(400, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        int count = 0;

        /* Enabling Multiple Selection */
        fileChooser.setMultiSelectionEnabled(true);

        /* Setting Current Directory */
        fileChooser.setCurrentDirectory(new File("C:\\Documents and Settings"));

        /* Adding action listener to open file */
        button.addActionListener(new ActionListener() {
	
	        public void actionPerformed(ActionEvent event) {
	        	JFileChooser chooser;
	    		int status;
	    		chooser = new JFileChooser();
	    		status = chooser.showOpenDialog(null);
	    		if (status == JFileChooser.APPROVE_OPTION)
	    			storage.initSeedsTextFile(count, chooser.getSelectedFile());
	    		else{
	    			JOptionPane.showMessageDialog(null, "No files were selected");
	    		}
	        }
        });
        
        /* Running the Application */
        new Utility();
	}
    
public String initCMD(String input){
		
		int count = 0;
		String totalCount;
		
		try{
			seed = new Scanner(new File(input));
		}
		catch(Exception err){
			System.out.println("could not find file");
		}
		
		while(seed.hasNext()){
			String id = seed.next();
			String name = seed.next();
			String price = seed.next();
			String quantity = seed.next();
			String remaining = seed.next();
			
			int prodId = Integer.parseInt(id);
			
			storage.addNewProduct(prodId, name);
			storage.setInventoryRecords(prodId, "prodId", id);
			storage.setInventoryRecords(prodId, "price", price);
			storage.setInventoryRecords(prodId, "quantity", quantity);
			storage.setInventoryRecords(prodId, "remaining", remaining);
		
			count++;
		}
		seed.close();
		
		totalCount = Integer.toString(count);
		
		return totalCount;
	}

}
