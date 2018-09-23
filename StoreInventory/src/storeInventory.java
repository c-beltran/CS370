import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
/**
 * @author Carlos Alberto
 * 
 */
public class storeInventory extends  viewGUI{
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		storeStorage newProduct = new storeStorage();
		viewGUI startUI = new viewGUI();
		startUI.start();
		newProduct.addNewProduct(0, "bluetooth");
		
		String name = newProduct.getName(0);
		System.out.println(name);
		newProduct.updateProduct(0, "name", "rayo");
		name = newProduct.getName(0);
		System.out.println(name);
		
		newProduct.setInventoryRecords(0, "price", "1000");
		String price = newProduct.getPrice(0);
		System.out.println(price);
		
		

//		HashMap<Integer, HashMap<String, String>> groceryStore = new HashMap<Integer, HashMap<String, String>>();
		
		//inserting datato MAINHASH
//		groceryStore.put(1, new HashMap(){{put("name", "Toyota");}});
//		groceryStore.get(1).put("price", "10000");
//		groceryStore.get(1).put("orderNum", "5");
//		groceryStore.get(1).put("quantity", "11");		
		
//		groceryStore.put(2, new HashMap(){{put("name", "Nissan");}});
		
//		String name = groceryStore.get(1).get("name");
//		String price = groceryStore.get(1).get("price");
//		String orderNum = groceryStore.get(1).get("orderNum");
//		String quantity = groceryStore.get(1).get("quantity");
		
//		System.out.println("this is the name " + name );
		
		//Print!!!!
//		print(name, price, orderNum, quantity);

//		System.out.println("Car 1 name is: " + groceryStore.get(1).get("name"));
//		System.out.println("Car 2 name is: " + groceryStore.get(2).get("name"));
		
		//print size of map
//		System.out.println("size of map is: " + groceryStore.size());
		
	}
	
	public static void print(String n, String p, String o, String q) throws IOException{
		File fout = new File("Result"+".txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write(String.format( "%-15s%-15s%-15s%-15s\n", "Name", "Price", "OrderNum", "Quantity"));
		bw.write(String.format( "%-15s%-15s%-15s%-15s\n", n, p, o, q));
		bw.close();
	}

}
