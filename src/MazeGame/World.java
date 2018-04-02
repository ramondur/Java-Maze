package MazeGame;

import java.awt.Graphics;

import Tiles.Tile;

/**
 *This class generates the "world" of the game, in other words, it takes the grid created in the MazeFile 
 *class and gives a unique ID to each element and then renders the image of each tile.
 */

public class World {

	//VARIABLES
	
	private static int width; //width of the maze
	private static int height; //height of the maze
	private static int spawnPX; //starting x coordinate of the player
	private static int spawnPY; //starting y coordinate of the player
	private static int spawnEX; //starting x coordinate of the enemy
	private static int spawnEY; //starting y coordinate of the enemy
	private static String [][] grid; //matrix that represents the grid of the maze
	private static int id; //identification number for a specific type of tile

	//CONSTRUCTOR
	
	/**
	 * It takes one variable: the file path of the game. 
	 */
	public World(String path) {
		MazeFile.loadFile(path); //calls the loadFile method in the MazeFile class and pass as arguments the file path of the game
		width = MazeFile.getWidth(); //sets the width of the maze
		height = MazeFile.getHeight(); //sets the height of the maze
		grid = MazeFile.getGrid(); //sets the grid of the maze
		getSpawnEntity(); //calls getSpawnEntity method
	}

	//METHODS

	/**
	 * Gets the starting x and y coordinates for both entities (enemy and player). The reason to use this method
	 * is to initialize the player and enemy correctly before the game loop. 
	 */
	public void getSpawnEntity() {
		for(int x = 0;x < width;x++) {
			for (int y = 0;y < height;y++) {
				getId(x,y);
			}
		}
	}

	/**
	 * Renders all the images that make up the maze grid. Each tile is 32x32 pixels so is necessary to multiply
	 * the x and y coordinate by 32. 
	 * !! One very important thing to keep in mind is that the coordinate system in a GUI is different from a 
	 *    matrix notation. The columns and rows are flipped. !!
	 */
	public void render(Graphics g) {
		for(int x = 0;x < width;x++) {
			for (int y = 0;y < height;y++) {
				Tile.getTile(x, y).render(g, y* Tile.TILE_WIDTH, x* Tile.TILE_HEIGHT);
			}	
		}
	}

	/**
	 * Depending on the name that appears in each of the positions of the grid, it will assign a different id 
	 * to this position that will correspond to a tile object.
	 */
	public static int getId(int x, int y) {
		//if the position is null it sets the id to 0
		if (grid[x][y] == null)
		{
			id=0;
		}
		//if the position name is wall it sets the id to 0
		else if (grid[x][y].matches("wall|WALL|W"))
		{
			id=0;
		}
		//if the position name is no it sets the id to 1
		else if (grid[x][y].matches("no|NO|N"))
		{
			id=1;
		}
		//if the position name is start it sets the id to 2 and sets also the spawnPX and spawnPY
		else if (grid[x][y].matches("start|START|S"))
		{
			id=2;
			spawnPX = x;
			spawnPY = y;
		}
		//if the position name is end it sets the id to 3
		else if (grid[x][y].matches("end|END|E"))
		{
			id=3;
		}
		//if the position name is door it sets the id to 4
		else if (grid[x][y].matches("door|DOOR|D"))
		{
			id=4;
		}
		//if the position name is breakable it sets the id to 5
		else if (grid[x][y].matches("breakable|BREAKABLE|B"))
		{
			id=5;
		}
		//if the position name is fake it sets the id to 6
		else if (grid[x][y].matches("fake|FAKE|F"))
		{
			id=6;
		}
		//if the position name is key it sets the id to 7
		else if (grid[x][y].matches("key|KEY|K"))
		{
			id=7;
		}
		//if the position name is hammer it sets the id to 8
		else if (grid[x][y].matches("hammer|HAMMER|H"))
		{
			id=8;
		}
		//if the position name is enemy it sets the id to 1 and sets also the spawnEX and spawnEX 
		else if (grid[x][y].matches("enemy|ENEMY"))
		{
			id=1;
			spawnEX = x;
			spawnEY = y;
		}
		return id;
	}

	//Getters and setters

	public int getSpawnPX() {
		return spawnPX;
	}
	
	public int getSpawnPY() {
		return spawnPY;
	}
	
	public int getSpawnEX() {
		return spawnEX;
	}
	
	public int getSpawnEY() {
		return spawnEY;
	}
	
}


