import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * This class was obtained from 
 * our lectures to send and receive
 * web content, this is without user agent
 * @author Carlos
 *
 */
public class WebpageReaderWithoutAgent {
	
	public static String webpage = null;
	
	//our own read method
	public static BufferedReader read(String url) throws Exception {
		return new BufferedReader(new InputStreamReader(new URL(url).openStream())); //creating a new url
	} // read
	
	//give credit to author
	public static void initiateReader(String args) throws Exception{
		String url = "https://www.gamestop.com/browse?nav=16k-3-" + args;
		if (url.length() == 0) {
			System.out.println("No URL inputted.");
			System.exit(1);
		} // any inputs?

		webpage = url;
		int numOfLines = 0;
		BufferedReader reader = read(webpage);
		String line = reader.readLine();
		
		File fout = new File("URLResult.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write("URL CONTENT: \n");
		
		while (line != null) {
			bw.write(line + "\n");
			line = reader.readLine(); 
			numOfLines++;
		} // while
		bw.write("NUMBER OF LINES : " + numOfLines);
		bw.close();
	}
}
