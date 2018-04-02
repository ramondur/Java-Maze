package Tiles;

import MazeGame.Images;

/**
 * A class of key tiles. It inherits from the class Tile.
 * A key tile is not solid. 
 * A key tile contains a key.
 */

public class KeyTile extends Tile {

	//CONSTRUCTOR
	
	public KeyTile(int id) {
		super(Images.floor, id); //has image of floor
	}
	
	//METHODS
	
	/**
	 * Checks if the tile contains a key. By default a tile doesn't contain a key.
	 */
	@Override
	public boolean hasObjectKey(){
		return true;
	}
	
}