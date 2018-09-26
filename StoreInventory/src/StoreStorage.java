import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class StoreStorage {
	public StoreStorage(){}
	
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
}

