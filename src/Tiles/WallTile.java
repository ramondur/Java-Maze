package Tiles;

import MazeGame.Images;

/**
 * A class of wall tiles. It inherits from the class Tile.
 * A wall tile is solid.
 */

public class WallTile extends Tile {

	//CONSTRUCTOR
	
	public WallTile(int id) {
		super(Images.wall, id);
	}

	//METHODS

	/**
	 * Checks if the tile is solid (an entity can not go through). By default a tile is not solid.
	 */
	@Override
	public boolean isSolid() {
		return true;
	}

}