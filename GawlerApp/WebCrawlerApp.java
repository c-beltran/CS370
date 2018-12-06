import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author Carlos Alberto
 * 
 * Main inventory class
 * which is in charge of running both
 * the Flag inputs and GUI
 */
public class WebCrawlerApp extends  viewGUI{
	
	/**
	 * @param args
	 * @throws IOException
	 * 
	 *  Main function
	 */
	public static void main(String[] args) throws java.io.IOException {
		
		UserAuth savedUser = UserAuth.getProdDB();
		Utility Util = new Utility();
		gameStopDB InitDB = gameStopDB.getProdDB();
		
		//store dummy user into db
		String Admin = "Admin";
		String pswd = "123456789";
		
		//encrypt the current user password
		try {
			pswd = EncryptionFeature.decrypt(pswd);
		} catch (Exception e) {
		}
		
		savedUser.newUser(Admin, pswd);
		UserRegistration reg = new UserRegistration();
		reg.initRegistration();

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
				System.out.println("Enter one of the following commands: [-i], [-o], [-p]");
				continue;
			}
			else if(commandLine.equals("-i"))
			{
				System.out.println("Enter the name of the file located in current directory: ");
				BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
				utility = input.readLine();
				if(utility.equals("")){
					Util.startJFile();
					System.out.println("Java File Chooser was used");
				}
				else{
					String result = Util.initCMD(utility);
					System.out.println(utility + " was used and " + result + " items were added");
				}
			}
			else if(commandLine.equals("-o"))
			{
				try {
					InitDB.printToTextFile();
				} catch (IOException err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
				}
				System.out.println("Output file was printed as 'printResult.txt' You can find it in your current directory");
			}
			else if(commandLine.equals("-p"))
			{
				String fileName = "queryLogFile.txt";
				Util.initCMD(fileName);
				try {
					InitDB.printToTextFile();
				} catch (IOException err) {
					// TODO Auto-generated catch block
				}
				System.out.println(fileName+" was loaded and output file was printed as 'printResult.txt' You can find it in your current directory");
			}
			else{
				System.out.println("TERMINATING APPLICATION....\nAPPLICATION ENDED");
				System.exit(1);
			}
		}//end of while loop
	}//main
}//class