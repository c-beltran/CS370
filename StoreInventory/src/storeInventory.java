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
		HashMap<Integer, String> carsMap = new HashMap<>();
		//shows map is empty
		print(carsMap);
		String[] arr = {"Toyota", "Honda", "Nissan", "Hyundai", "Mercedes", "BMW"};
		
		for(int i=0; i< arr.length; i++){
			carsMap.put(i, arr[i]);
		}
		
		//print size of map
		System.out.println("size of map is: " + carsMap.size());
		
		//print all values of hashmap
		for(int i=0; i< carsMap.size(); i++){
			if(carsMap.containsKey(i)){
				System.out.println(carsMap.get(i));
			}else{
				System.out.println("ERROR");
			}
		}
	}
	
	public static void print(Map<Integer, String> map){
		if(map.isEmpty()){
			System.out.println("Cars Map is Empty");
		}else{
			System.out.println(map);
		}
	}

}
