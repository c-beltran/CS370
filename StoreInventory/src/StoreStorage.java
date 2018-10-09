import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyStore.Entry;

public class StoreStorage {
	
	private static StoreStorage product = null;
	
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
	
	public void getKeys() throws IOException {
		String name = null, price = null, quantity = null, remaining = null;
		int prodId;
		
		File fout = new File("Result"+".txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write(String.format( "%-15s%-15s%-15s%-15s%-15s\n", "prodID", "Name", "Price", "quantity", "Quantity"));
		
		Set<java.util.Map.Entry<Integer, HashMap<String, String>>> hashSet = groceryStore.entrySet();
        for(java.util.Map.Entry<Integer, HashMap<String, String>> entry:hashSet ) {
        	
        	prodId = entry.getKey();
        	name = groceryStore.get(prodId).get("name");
        	price = groceryStore.get(prodId).get("price");
        	quantity = groceryStore.get(prodId).get("quantity");
        	remaining = groceryStore.get(prodId).get("remaining");
        	
        	bw.write(String.format("%-15s%-15s%-15s%-15s%-15s\n", prodId, name, price, quantity, remaining));
        	
            System.out.println("Key=" + entry.getKey()+", Value="+entry.getValue());
            System.out.println("printing name: " + name);
        }
        
		bw.close();
	}
	

	public Object entrySet() {
		return groceryStore.entrySet();
	}
}

