package MazeGame;

import java.awt.image.BufferedImage;
import Tiles.Tile;
import Utilities.ImageLoader;

/**
 *This class manages the images for each element used in the game.
 */

public class Images {

	//VARIABLES

	private BufferedImage image; //BufferedImage object
	public static BufferedImage player; //BufferedImage object
	public static BufferedImage enemy; //BufferedImage object
	public static BufferedImage wall; //BufferedImage object
	public static BufferedImage breackableWall; //BufferedImage object
	public static BufferedImage door; //BufferedImage object
	public static BufferedImage floor; //BufferedImage object
	public static BufferedImage start; //BufferedImage object
	public static BufferedImage end; //BufferedImage object

	//CONSTRUCTOR
	
	/**
	 * It takes one variables: a BufferedImage object. 
	 */
	public Images(BufferedImage i) {
		image = i;
	}
	
	//METHODS
	
	/**
	 * Method to assaign each image used in the maze to a variable. The image is loaded usiong the loadImage methid
	 * from the ImageLoader class. 
	 */
	public static void init() {
		Images startImage = new Images(ImageLoader.loadImage("/textures/start.png"));
		Images witheredTree = new Images(ImageLoader.loadImage("/textures/abetoseco.png"));
		Images gift = new Images(ImageLoader.loadImage("/textures/present.png"));
		Images santa = new Images(ImageLoader.loadImage("/textures/santa.png"));
		Images house = new Images(ImageLoader.loadImage("/textures/casa.png"));
		Images snow = new Images(ImageLoader.loadImage("/textures/nieve.png"));
		Images tree = new Images(ImageLoader.loadImage("/textures/aveto.png"));
		Images grinch = new Images(ImageLoader.loadImage("/textures/grinch.png"));

		enemy = grinch.crop(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		player = santa.crop(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		wall = tree.crop(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		breackableWall = witheredTree.crop(0, 0 , Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		door = house.crop(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		floor = snow.crop(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		start = startImage.crop(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		end = gift.crop(0, 0, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
	}
	
	/**
	 * Returns a subimage defined by a specified rectangular region. 
	 */
	public BufferedImage crop(int x, int y, int w, int h) {
		return image.getSubimage(x, y, w, h);
	}
	
}
