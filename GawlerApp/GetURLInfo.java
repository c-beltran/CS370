import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.*; 

/**
 * This class was provided in 
 * class. it has been modified to
 * accept the criteria of this
 * project.
 * @author Carlos
 *
 */
public class GetURLInfo {
	
	/**
	 * Gets the URl info 
	 * and returns all the info as a string
	 * @param uc
	 * @return
	 * @throws IOException
	 */
	public static String printURLinfo(URLConnection uc) throws IOException {      
      String info = uc.getURL().toExternalForm()+": \n"+
    		  		"  Content Type: " + uc.getContentType()+"\n"+
    		  		"  Content Length: " + uc.getContentLength()+"\n"+
    		  		"  Last Modified: " + new Date(uc.getLastModified())+"\n"+
    		  		"  Expiration: " + uc.getExpiration()+"\n"+
    		  		"  Content Encoding: " + uc.getContentEncoding();
      
      return info;
   } // printURLinfo
	
	/**
	 * accepts a URL as a string
	 * and returns the url info 
	 * from printURLinfo mehod
	 * @param args
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static String getURLInfo(String args) throws MalformedURLException, IOException{
	    //args is a string provided by command line
		URL url = new URL(args); //this is a new URL class, constructor takes a string URL.
		URLConnection connection = url.openConnection(); //create a connection with URL
		String info = printURLinfo(connection); //this is a special print - we have to use printURLinfo LOOK UP.
	
		return info;
	} // main
	
	public static String getURLType(String args) throws MalformedURLException, IOException{
		String type = null;
		int index = 0;
		URL url = new URL(args);
		URLConnection connection = url.openConnection();
		//splitting 
		type = connection.getContentType();
		index = type.indexOf(";");
		if(index != -1){
			type = type.substring(0, index);
		}
		return type;
	}
	
	/**
	 * Accepts a URL as a string and
	 * Returns the the name
	 * of the URL provided.
	 * @param args
	 * @return
	 * @throws URISyntaxException
	 */
	public static String getURLName(String args) throws URISyntaxException{		
		URI uri = new URI(args);
		String[] segments = uri.getPath().split("/");
		String idStr = segments[segments.length-1];
		
		return idStr;
	}
	
}
