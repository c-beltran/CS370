import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * This class was provided
 * in class as part of our
 * lecture.
 * @author Carlos
 *
 */
public class GetURLImage {
	
	URL url = null;
	File outputImageFile = null;
	public static BufferedImage image = null;

	public static void fetchImageFromURL (URL url) {
		try {
		// Read from a URL
			image = ImageIO.read(url);
		} catch (IOException e) {
		} // catch

	} // fetchImageFromURL

	public static void initiageImage(String args, String fileName) throws MalformedURLException, IOException{
		URL url = new URL(args);
		File outputImageFile = new File(fileName);
		fetchImageFromURL(url);
		ImageIO.write(image, "png", outputImageFile);
	}

}
