import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Carlos Alberto
 *Utility class which contains
 *methods used in the flag implementation
 */
public class Utility extends JFrame {
	
    private static JButton button = new JButton("Open");
    private static JFileChooser fileChooser = new JFileChooser();
	private Scanner seed;
	private static Scanner urlFileReader;
	private static List imgList = new List();
	static ArrayList<String> searchData = new ArrayList<String>();
//	public static List searchData = new List();

    static gameStopDB InitDB = gameStopDB.getProdDB();
    
    /**
     * starts a file chooser GUI
     * @startJFile
     */
    public void startJFile() {
        add(button);
        setSize(400, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        int count = 0;

        // Enabling Multiple Selection 
        fileChooser.setMultiSelectionEnabled(true);

        // Setting Current Directory 
        fileChooser.setCurrentDirectory(new File("C:\\Documents and Settings"));

        // Adding action listener to open file 
        button.addActionListener(new ActionListener() {
	
	        public void actionPerformed(ActionEvent event) {
	        	JFileChooser chooser;
	    		int status;
	    		chooser = new JFileChooser();
	    		status = chooser.showOpenDialog(null);
	    		if (status == JFileChooser.APPROVE_OPTION)
	    			InitDB.initSeedsTextFile(count, chooser.getSelectedFile());
	    		else{
	    			JOptionPane.showMessageDialog(null, "No files were selected");
	    		}
	        }
        });
        
        // Running the Application 
        new Utility();
	}//end of startJFile
    
    /**
     * takes an input from the -i
     * input from command line
     * and seeds the storage structure
     * @param input
     * @return
     */
    public String initCMD(String input){
		
		int count = 0;
		String totalCount;
		
		try{
			seed = new Scanner(new File(input));
		}
		catch(Exception err){
			System.out.println("could not find file");
		}
		
		while(seed.hasNext()){
			String id = seed.next();
			String search = seed.next();
			String gameTitle = seed.next();
			String consoleType = seed.next();
			String timeStamp = seed.next();
			
			int prodId = Integer.parseInt(id);
			InitDB.addNewSearchTerm(prodId, search);
//			InitDB.setInventoryRecords(prodId, "prodId", id);
			InitDB.setInventoryRecords(prodId, "gameTitle", gameTitle);
			InitDB.setInventoryRecords(prodId, "consoleType", consoleType);
			InitDB.setInventoryRecords(prodId, "timeStamp", timeStamp);
		
			count++;
		}
		seed.close();
		
		totalCount = Integer.toString(count);
		
		return totalCount;
	}//end of initCMD
    
	public void updateLog(String log) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("outputLog.txt", true));
	    writer.append(log);
		    
	    writer.close();
	}
	
	public static void updateUserSearch(String log) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("searchedData.txt", true));
	    writer.append(log);
		    
	    writer.close();
	}
	
	/**
	 * Reads the input file 
	 * and saves to storage
	 */			
	public static void imgReadFile(String uploadFile) throws Exception{
		try{
			urlFileReader = new Scanner(new File(uploadFile));
		}
		catch(Exception err){
			System.out.println("could not find file");
		}
		
		String regexStr = "https?://.*.(?:png|jpg)";
		
		while(urlFileReader.hasNextLine()){
			String htmlLine = urlFileReader.nextLine();
			imgRgxSearch(htmlLine, regexStr);
		}
		
		String imgURL = imgList.getItem(1);
		String typeURL = GetURLInfo.getURLType(imgURL);
		String imgType = GetURLInfo.getURLName(typeURL);
		String fileName = "Game_Stop_Img.png";
		GetURLImage.initiageImage(imgURL, fileName);
	}
	
	public static ArrayList<String> searchItems(String uploadFile, int ID) throws Exception{
		searchData = new ArrayList<String>();
		
		try{
			urlFileReader = new Scanner(new File(uploadFile));
		}
		catch(Exception err){
			System.out.println("could not find file");
		}
		
		String gameTitleRgx = "<a\\s.*Class=\"ats-product-title-lnk\"[^>]*>(.*)(?=<\\/a>)";
		String consoleTypeRgx = "<h3\\s.*<strong>(.*)(?=</strong></h3>)";
		String gameName = "Game Title: ";
		String consoleName = "Console Type: ";
		
		while(urlFileReader.hasNextLine()){
			String htmlLine = urlFileReader.nextLine();	
			regexSearch(gameTitleRgx, htmlLine, gameName, ID);
			regexSearch(consoleTypeRgx, htmlLine, consoleName, ID);
		}
//		System.out.println(searchData.size());
		return searchData;
	}

	public static List imgRgxSearch(String url, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		boolean found = false;
		while (matcher.find()) {
//            System.out.println("I found the text " + matcher.group());
			imgList.add(matcher.group());
            found = true;
        }
        if(!found){ }
		return imgList;		
	}
	
	public static void regexSearch(String regex, String matcher, String game, int ID) {
		//System.out.println("Searching..");
		Pattern search = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher match = search.matcher(matcher);
		String temp = null;
		String listTemp = null;
		boolean found = false;
		
		while(match.find()) {
			found = true;
			if(match.group().length() != 0) {				
				try {
					updateUserSearch(game + match.group(1) + "\n");
					if(game.equals("Game Title: ")){
						temp = match.group(1);
						temp = temp.replaceAll(" ", "_");
						listTemp = game+temp;
//						System.out.println("YERRR: " + game + " " + temp);
						InitDB.setInventoryRecords(ID, "gameTitle", temp);
						searchData.add(listTemp);
					}else{
						temp = match.group(1);
						temp = temp.replaceAll(" ", "_");
						listTemp = game+temp;
						InitDB.setInventoryRecords(ID, "consoleType", temp);
						searchData.add(listTemp);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(!found){}
	}
}


// get a tags for title: <a\s.*Class="ats-product-title-lnk".*<\/a> || <a\\s.*Class=\"ats-product-title-lnk\".*</a>
// get the type of console <h3\s.*<strong>(.*)(?=<\/strong><\/h3>) || <h3\\s.*<strong>(.*)(?=</strong></h3>)
//img tag: <img([\w\W]+?)/> |  <img([[\\w][\\W]]+?)/>
//src[\\s]*=[\\s]*\"([^\"]+)\ | https?://.*.(?:png|jpg)