package Tiles;

import MazeGame.Display;
import MazeGame.Images;

/**
 * A class of door tiles. It inherits from the class Tile.
 * A door tile is solid unless the player gets the key. When the player gets the key
 * the door opens and is not longer solid.
 */

public class DoorTile extends Tile {
	
	//CONSTRUCTOR
	
	public DoorTile(int id) {
		super(Images.door, id); //has image of door
	}

	//METHODS
	
	/**
	 * Checks if the door is solid. By default a door is solid unless the player gets the key.
	 */
	@Override
	public boolean isSolid() {
		if (Display.getKey()=="Yes") {
			return false;
		}
		return true;
	}
	
}
