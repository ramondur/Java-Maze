package Tiles;

import MazeGame.Images;

/**
 * A class of exit tiles. It inherits from the class Tile.
 * An exit tile is not solid.
 * This tile has the end method set to true.
 */

public class ExitTile extends Tile {
	
	//CONSTRUCTOR
	
	public ExitTile(int id) {
		super(Images.end, id); //has image of end
	}
	
	//METHODS
	
	/**
	 * Checks if the tile is the exit tile. By default a tile is not the exit tile.
	 */
	@Override
	public boolean end() {
		return true;
	}
	
}
