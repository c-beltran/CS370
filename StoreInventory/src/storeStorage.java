import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class storeStorage {
	public storeStorage(){
		
	}
	HashMap<Integer, HashMap<String, String>> groceryStore = new HashMap<Integer, HashMap<String, String>>();
	
	public void addNewProduct(int id, String name){
		groceryStore.put(id, new HashMap(){{put("name", name);}});
	}
	
	public String getName(int id){
		return groceryStore.get(id).get("name");
	}
	
	public void updateProduct(int id, String category, String newUpdate){
		groceryStore.get(id).put(category, newUpdate);
	}
}

