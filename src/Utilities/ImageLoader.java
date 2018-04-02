package Utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *This class loads the different images of the game.
 */

public class ImageLoader {
	
	/**
	 *Method to load an image. It takes as a paramenter the image file path and returns a BufferedImage as 
	 *the result of decoding the supplied URL.
	 */
	public static BufferedImage loadImage(String path) 
	{
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
