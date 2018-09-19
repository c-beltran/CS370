import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
/**
 * @author Carlos Alberto
 *This 
 */
public class storeInventory {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		HashMap<Integer, HashMap<String, String>> carsMap = new HashMap<Integer, HashMap<String, String>>();
		//shows map is empty
//		print(carsMap);
		
		//inserting datato MAINHASH
		carsMap.put(1, new HashMap(){{put("name", "Toyota");}});
		carsMap.get(1).put("price", "10000");
		carsMap.get(1).put("orderNum", "5");
		carsMap.get(1).put("quantity", "11");		
		
		carsMap.put(2, new HashMap(){{put("name", "Nissan");}});
		
		String name = carsMap.get(1).get("name");
		String price = carsMap.get(1).get("price");
		String orderNum = carsMap.get(1).get("orderNum");
		String quantity = carsMap.get(1).get("quantity");
		
		System.out.println("this is the name " + name );
		
		//Print!!!!
		print(name, price, orderNum, quantity);

		System.out.println("Car 1 name is: " + carsMap.get(1).get("name"));
		System.out.println("Car 2 name is: " + carsMap.get(2).get("name"));
		
		//print size of map
		System.out.println("size of map is: " + carsMap.size());
		
		
	}
	
	public static void print(String n, String p, String o, String q) throws IOException{
		File fout = new File("Output//Result"+".txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write("Name \t" + "Price \t" + "OrderNum \t" + "Quantity \n");
		bw.write(n + " \t" + p + " \t" + o + " \t" + q);
		bw.close();
	}

}
