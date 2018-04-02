package Tiles;

import MazeGame.Display;
import MazeGame.Images;

/**
 * A class of breakable wall tiles. It inherits from the class Tile.
 * A breakable wall tile is solid unless the player gets the hammer. When the player gets the hammer
 * the wall breaks and is not longer solid.
 */

public class BreakableWallTile extends Tile {

	//CONSTRUCTOR
	
	public 	BreakableWallTile(int id) {
		super(Images.breackableWall, id); //has image of breakable wall
	}
	
	//METHODS
	
	/**
	 * Checks if the breakable wall is solid. By default a breakable wall is solid unless the player gets the hammer.
	 */
	@Override
	public boolean isSolid() {
		if (Display.getHammer()=="Yes") {
			return false;
		}
		return true;
	}
	
}