package Tiles;

import MazeGame.Images;

/**
 * A class of start tiles. It inherits from the class Tile.
 * An start tile is not solid.
 */

public class StartTile extends Tile {

	//CONSTRUCTOR
	
	public StartTile(int id) {
		super(Images.start, id);  //has image of start
	}
	
}
