package Tiles;

import MazeGame.Images;

/**
 * A class of fake wall tiles. It inherits from the class Tile.
 * A fake wall tile is not solid. 
 */

public class FakeWallTile extends Tile {

	//CONSTRUCTOR
	
	public 	FakeWallTile(int id) {
		super(Images.wall, id); //has image of wall
	}

}