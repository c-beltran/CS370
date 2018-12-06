import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyStore.Entry;

/**
 * @author Carlos Alberto
 * 
 *This contains the storage structure
 *is made out of a nested HashMap.
 *In addition this contains various methods
 *for querying the data
 */
public class StoreStorage {
	
	private static StoreStorage product = null;
	private Scanner seed;
	
	public static StoreStorage getProdDB(){
		if(product == null){
			product = new StoreStorage();
		}
		return product;
	}
	
	HashMap<Integer, HashMap<String, String>> groceryStore = new HashMap<Integer, HashMap<String, String>>();
	
	public void addNewProduct(int id, String name){
		groceryStore.put(id, new HashMap(){{put("name", name);}});
	}
	
	public void setInventoryRecords(int id, String category, String record){
		groceryStore.get(id).put(category, record);
	}
	
	public void updateProduct(int id, String category, String newUpdate){
		groceryStore.get(id).put(category, newUpdate);
	}
	
	public void removeProduct(int id){
		groceryStore.remove(id);
	}
	
	public String getName(int id){
		return groceryStore.get(id).get("name");
	}
	
	public String getPrice(int id){
		return groceryStore.get(id).get("price");
	}
	
	public String getQuantity(int id){
		return groceryStore.get(id).get("quantity");
	}
	
	public String getRemaining(int id){
		return groceryStore.get(id).get("remaining");
	}
	
	/**
	 * Function is used to print a .txt file
	 * it outputs a 'Result.txt' file to the
	 * current directory
	 * @throws IOException
	 * 
	 */
	public void printToTextFile() throws IOException {
		String name = null, price = null, quantity = null, remaining = null;
		int prodId;
		
		File fout = new File("Result"+".txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write(String.format( "%-15s%-15s%-15s%-15s%-15s\n", "prodID", "Name", "Price", "Quantity", "Remaining"));
		
		Set<java.util.Map.Entry<Integer, HashMap<String, String>>> hashSet = groceryStore.entrySet();
        for(java.util.Map.Entry<Integer, HashMap<String, String>> entry:hashSet ) {
        	
        	prodId = entry.getKey();
        	name = groceryStore.get(prodId).get("name");
        	price = groceryStore.get(prodId).get("price");
        	quantity = groceryStore.get(prodId).get("quantity");
        	remaining = groceryStore.get(prodId).get("remaining");
        	
        	bw.write(String.format("%-15s%-15s%-15s%-15s%-15s\n", prodId, name, price, quantity, remaining));
        }
        
		bw.close();
	}//END
	
	/**
	 * initiates the seeds file
	 * from a .txt document
	 * @param count
	 * @param uploadFile
	 * @return
	 */
	public int initSeedsTextFile(int count, File uploadFile){
		try{
			seed = new Scanner(uploadFile);
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
			
			addNewProduct(prodId, name);
			setInventoryRecords(prodId, "prodId", id);
			setInventoryRecords(prodId, "price", price);
			setInventoryRecords(prodId, "quantity", quantity);
			setInventoryRecords(prodId, "remaining", remaining);
			
			count++;
		}
		seed.close();
		return count;
	}//end of initSeedsTextFile
	
}//end of StoreStorage

