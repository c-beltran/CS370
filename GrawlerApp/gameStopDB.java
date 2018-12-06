import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author Carlos Alberto
 * 
 *This contains the storage structure
 *is made out of a nested HashMap.
 *In addition this contains various methods
 *for querying the data
 */
public class gameStopDB {
	
	private static gameStopDB product = null;
	private Scanner seed;
	
	public static gameStopDB getProdDB(){
		if(product == null){
			product = new gameStopDB();
		}
		return product;
	}
	
	HashMap<Integer, HashMap<String, String>> gameStopDB = new HashMap<Integer, HashMap<String, String>>();
	
	public void addNewSearchTerm(int id, String name){
		gameStopDB.put(id, new HashMap(){{put("search", name);}});
	}
	
	public void setInventoryRecords(int id, String category, String record){
		gameStopDB.get(id).put(category, record);
	}
	
	public void updateSearch(int id, String category, String newUpdate){
		gameStopDB.get(id).put(category, newUpdate);
	}
	
	public void removeProduct(int id){
		gameStopDB.remove(id);
	}
	
	public String getSearchTerm(int id){
		return gameStopDB.get(id).get("search");
	}
	
	public String getGameTitle(int id){
		return gameStopDB.get(id).get("gameTitle");
		
	}
	
	public String getConsoleType(int id){
		return gameStopDB.get(id).get("consoleType");
	}
	
	public String getTimeStamp(int id){
		return gameStopDB.get(id).get("timeStamp");
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
		
		File fout = new File("printResult"+".txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write(String.format( "%-15s%-15s%-15s%-15s%-15s\n", "SearchID", "Search", "GameTitle", "ConsoleType", "TimeStamp"));
		
		Set<java.util.Map.Entry<Integer, HashMap<String, String>>> hashSet = gameStopDB.entrySet();
        for(java.util.Map.Entry<Integer, HashMap<String, String>> entry:hashSet ) {
        	
        	prodId = entry.getKey();
        	name = gameStopDB.get(prodId).get("search");
        	price = gameStopDB.get(prodId).get("gameTitle");
        	quantity = gameStopDB.get(prodId).get("consoleType");
        	remaining = gameStopDB.get(prodId).get("timeStamp");
        	
        	bw.write(String.format("%-15s%-15s%-15s%-15s%-15s\n", prodId, name, price, quantity, remaining));
        }
        
		bw.close();
	}//END
	
	/**
	 * Function is used to print a .txt file
	 * it outputs a 'Result.txt' file to the
	 * current directory
	 * @throws IOException
	 * 
	 */
	public void queryLogFile() throws IOException {
		String search = null, gameTitle = null, consoleType = null, timeStamp = null;
		int prodId;
		
		File fout = new File("queryLogFile"+".txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//		bw.write(String.format( "%-15s%-15s%-15s%-15s%-15s%-15s\n", "SearchID", "Search", "GameTitle", "ConsoleType", " ", "TimeStamp"));
		
		Set<java.util.Map.Entry<Integer, HashMap<String, String>>> hashSet = gameStopDB.entrySet();
        for(java.util.Map.Entry<Integer, HashMap<String, String>> entry:hashSet ) {
        	
        	prodId = entry.getKey();
        	search = gameStopDB.get(prodId).get("search");
        	gameTitle = gameStopDB.get(prodId).get("gameTitle");
        	consoleType = gameStopDB.get(prodId).get("consoleType");
        	timeStamp = gameStopDB.get(prodId).get("timeStamp");
        	
        	bw.write(prodId+" "+search+" "+gameTitle+" "+consoleType+" "+timeStamp+"\n");
        }
        
		bw.close();
	}//END
	
	public ArrayList<String> printHistory() throws IOException {
		ArrayList<String> historyData = new ArrayList<String>();
		String search = null, gameTitle = null, consoleType = null, timeStamp = null;
		int prodId;
		
		Set<java.util.Map.Entry<Integer, HashMap<String, String>>> hashSet = gameStopDB.entrySet();
        for(java.util.Map.Entry<Integer, HashMap<String, String>> entry:hashSet ) {
        	
        	prodId = entry.getKey();
        	search = gameStopDB.get(prodId).get("search");
        	gameTitle = gameStopDB.get(prodId).get("gameTitle");
        	consoleType = gameStopDB.get(prodId).get("consoleType");
        	timeStamp = gameStopDB.get(prodId).get("timeStamp");

        	historyData.add(String.format("%-10s%-10s%-10s%-10s%-10s%-9s", prodId, search, gameTitle, consoleType, " ", timeStamp));
        }
        return historyData;
     }//END
	
	/**
	 * returns the size of the DB
	 * @return
	 */
	public int getLastKeyValue(){
		int value = 0;
		
		Set<java.util.Map.Entry<Integer, HashMap<String, String>>> hashSet = gameStopDB.entrySet();
        for(java.util.Map.Entry<Integer, HashMap<String, String>> entry:hashSet ) {
        	value = entry.getKey();
       }
		return value;
	}
	
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
			String gameTitle = seed.next();
			String consoleType = seed.next();
			String timeStamp = seed.next();
			
			int prodId = Integer.parseInt(id);
			
			addNewSearchTerm(prodId, name);
			setInventoryRecords(prodId, "prodId", id);
			setInventoryRecords(prodId, "gameTitle", gameTitle);
			setInventoryRecords(prodId, "consoleType", consoleType);
			setInventoryRecords(prodId, "timeStamp", timeStamp);
			
			count++;
		}
		seed.close();
		return count;
	}//end of initSeedsTextFile
	
}//end of StoreStorage

