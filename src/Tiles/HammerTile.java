package Tiles;

import MazeGame.Images;

/**
 * A class of hammer tiles. It inherits from the class Tile.
 * A hammer tile is not solid. 
 * A hammer tile contains a hammer
 */

public class HammerTile extends Tile {

	//CONSTRUCTOR
	
	public HammerTile(int id) {
		super(Images.floor, id); //has image of floor
	}
	
	//METHODS
	
	/**
	 * Checks if the tile contains a hammer. By default a tile doesn't contain a hammer.
	 */
	@Override
	public boolean hasObjectHammer() {
		return true;
	}
	
}
