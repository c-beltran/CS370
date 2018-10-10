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
public class InventoryApp extends  viewGUI{
	/**
	 * @param args
	 * @throws IOException
	 * 
	 *  Main function
	 */
	public static void main(String[] args) throws java.io.IOException {
		
		Utility util = new Utility();
		StoreStorage initDB = StoreStorage.getProdDB();
		viewGUI startUI = new viewGUI();
	 
		BufferedReader console = new BufferedReader (new InputStreamReader(System.in));
		String commandLine;
		String utility;
		
		/**
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
				System.out.println("Enter one of the following commands: [-i], [-o], [-s]");
				continue;
			}
			else if(commandLine.equals("-i")){
				System.out.println("Enter the name of the file located in current directory: ");
				BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
				utility = input.readLine();
				if(utility.equals("")){
					util.startJFile();
					System.out.println("Java File Chooser was used");
				}
				else{
					String result = util.initCMD(utility);
					System.out.println(utility + " was used and " + result + " products were added");
				}
			}
			else if(commandLine.equals("-o")){
				try {
					initDB.printToTextFile();
				} catch (IOException err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
				}
				System.out.println("Output file was printed as 'Result.txt' You can find it in your current directory");
			}
			else if(commandLine.equals("-s")){
				startUI.start();
				System.out.println("Graphical User Interface has started");
			}
			else{
				System.out.println("TERMINATING APPLICATION....\nAPPLICATION ENDED");
				System.exit(1);
			}
		}//end of while loop
	}//main
}//class
