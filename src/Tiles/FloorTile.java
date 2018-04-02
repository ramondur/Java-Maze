package Tiles;

import MazeGame.Images;

/**
 * A class of floor tiles. It inherits from the class Tile.
 * A floor tile is not solid. 
 */

public class FloorTile extends Tile {

	//CONSTRUCTOR
	
	public FloorTile(int id) {
		super(Images.floor, id); //has image of floor
	}

}