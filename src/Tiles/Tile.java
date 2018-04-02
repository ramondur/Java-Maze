package Tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import MazeGame.World;

/**
 * A class of tiles. A tile corresponds to each square that forms the grid of the maze.
 */

public abstract class Tile {
		
	public static Tile[] tiles = new Tile[256]; //Array of tiles, the position of the tile object in the array is the same as the id of the tile object
	public static Tile wallTile = new WallTile(0); //WallTile object which has id 0
	public static Tile floorTile = new FloorTile(1); //FloorTile object which has id 1
	public static Tile startTile = new StartTile(2); //StartTile object which has id 2
	public static Tile exitTile = new ExitTile(3); //ExitTile object which has id 3
	public static Tile doorTile = new DoorTile(4); //DoorTile object which has id 4
	public static Tile breakableWallTile = new BreakableWallTile(5); //BreakableWallTile object which has id 5
	public static Tile fakeWall = new FakeWallTile(6); //FakeWallTile object which has id 6
	public static Tile keyTile = new KeyTile(7); //KeyTile object which has id 7
	public static Tile hammerTile = new HammerTile(8); //HammerTile object which has id 8


	//Each tile has a width and a height of 32 pixels. These are constants.
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;

	//VARIABLES
	
	protected BufferedImage texture;

	//CONSTRUCTOR
	/**
	 * The variables that are passed in the constructor are a image of the tile and an ID.
	 * The tile object is stored in the tiles array at the same position as the ID.
	 */
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		tiles[id] = this;
	}
	
	//METHODS
	
	/**
	 * Render method to draw each tile to the screen
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, null);
	}
	
	/**
	 * Checks if the tile is solid (an entity can not go through). By default a tile is not solid.
	 */
	public boolean isSolid() {
		return false;
	}

	/**
	 * Checks if the tile contains a key. By default a tile doesn't contain a key.
	 */
	public boolean hasObjectKey() {
		return false;
	}
	
	/**
	 * Checks if the tile contains a hammer. By default a tile doesn't contain a hammer.
	 */
	public boolean hasObjectHammer() {
		return false;
	}
	
	/**
	 * Checks if the tile is the exit tile. By default a tile is not the exit tile.
	 */
	public boolean end() {
		return false;
	}
	
	/**
	 * Returns the tile object in a specific tile of the grid. It needs as variables the x and y coordinate of the
	 * tile and then it will get the id which will indicate the kind of tile it is.
	 */
	public static Tile getTile(int x, int y) {
		Tile t = Tile.tiles[World.getId(x, y)]; 
		return t;
	}
	
}