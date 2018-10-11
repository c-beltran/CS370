import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * @author Carlos Alberto
 * 
 * Main inventory class
 * which is in charge of running both
 * the Flag inputs and GUI
 */
public class StoreInventoryApp extends  viewGUI{
	
	/**
	 * @param args
	 * @throws IOException
	 * 
	 *  Main function
	 */
	public static void main(String[] args) throws java.io.IOException {
		
		Utility Util = new Utility();
		StoreStorage InitDB = StoreStorage.getProdDB();
		viewGUI StartUI = new viewGUI();
		StartUI.start();

		BufferedReader console = new BufferedReader (new InputStreamReader(System.in));
		String commandLine;
		String utility;
		
		/**
		 * -FLAGS-
		 * This while look runs and checks for specific
		 * input of the user. Different input will output
		 * different results.
		 */
		while (true) {
			// read what the user entered
			System.out.print("Usage:");
			commandLine = console.readLine();
			
			// if the user entered a return, just loop again
			if (commandLine.equals("")) {
				System.out.println("Enter one of the following commands: [-i], [-o]");
				continue;
			}
			else if(commandLine.equals("-i")){
				System.out.println("Enter the name of the file located in current directory: ");
				BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
				utility = input.readLine();
				if(utility.equals("")){
					Util.startJFile();
					System.out.println("Java File Chooser was used");
				}
				else{
					String result = Util.initCMD(utility);
					System.out.println(utility + " was used and " + result + " products were added");
				}
			}
			else if(commandLine.equals("-o")){
				try {
					InitDB.printToTextFile();
				} catch (IOException err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
				}
				System.out.println("Output file was printed as 'Result.txt' You can find it in your current directory");
			}
			else{
				System.out.println("TERMINATING APPLICATION....\nAPPLICATION ENDED");
				System.exit(1);
			}
		}//end of while loop
	}//main
}//class
