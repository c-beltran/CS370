import java.util.HashMap;
import java.util.Map;
/**
 * @author Carlos Alberto
 *This 
 */
public class storeInventory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<Integer, HashMap<String, String>> carsMap = new HashMap<Integer, HashMap<String, String>>();
		//shows map is empty
//		print(carsMap);
		
		//inserting datato MAINHASH
		carsMap.put(1, new HashMap(){{put("name", "Toyota");}});
		carsMap.put(2, new HashMap(){{put("name", "Nissan");}});
		

		System.out.println(carsMap.get(1).get("name"));
		
		
		//print size of map
		System.out.println("size of map is: " + carsMap.size());
		
		//print all values of hashmap
//		for(int i=0; i< carsMap.size(); i++){
//			if(carsMap.containsKey(i)){
//				System.out.println(carsMap.get(i));
//			}else{
//				System.out.println("ERROR");
//			}
//		}
	}
	
//	public static void print(Map<Integer, Object> map){
//		if(map.isEmpty()){
//			System.out.println("Cars Map is Empty");
//		}else{
//			System.out.println(map);
//		}
//	}

}
