package Entity;

import java.awt.Graphics;

import MazeGame.Display;
import MazeGame.Game;
import MazeGame.Images;
import Tiles.Tile;
import Utilities.ObserverPosition;
import Utilities.Position;

/**
 * This class contains all methods related to the player.
 */

public class Player extends Entity{

	//VARIABLES
	
	private int currentPositionX; //current x position of the player in grid units
	private int currentPositionY; //current y position of the player in grid units
	private int spawnX; //starting x position of the player in grid units
	private int spawnY; //starting y position of the player in grid units
	
	Position px = new Position ((String) getCurrentPositionX());
	ObserverPosition Obsx = new ObserverPosition();
	Position py = new Position ((String) getCurrentPositionY());
	ObserverPosition Obsy = new ObserverPosition();
	Enemy enemy; //enemy object

	//CONSTRUCTOR
	
	/**
	 * It takes four variables: the game object, the x coordinate, the y coordinate and the enemy object. 
	 */
	public Player(Game game, float x, float y, Enemy enemy) {
		super(game,x,y);
		this.enemy = enemy;
		this.spawnX = (int) x;
		this.spawnY = (int) y;
	}

	//METHODS
	
	/**
	 * Tick method for the player. Moves the player up, down, left or right depending on the input that comes 
	 * from the keyboard.
	 */
	@Override
	public void tick() {
		if(game.getKeyManager().up) {
			moveUp();
		}
		if(game.getKeyManager().down) {
			moveDown();
		}
		if(game.getKeyManager().right) {
			moveRight();
		}
		if(game.getKeyManager().left) {
			moveLeft();
		}
		score(); //calls score method
		hasKey(); //calls hasKey method
		hasHammer(); //calls hasHammer method
		hasEnded(Integer.parseInt(getCurrentPositionX()),Integer.parseInt(getCurrentPositionY())); //calls hasEnded method
		checkEnemy(); //calls checkEnemy method
	}
	
	/**
	 * Renders the player image to the screen.
	 */
	@Override
	public void render(Graphics g) {	
		g.drawImage(Images.player, (int)x, (int)y, null);
	}
	
	/**
	 * Checks if the current tile where the player is standing has a key.
	 */
	public boolean checkKey(int y, int x) {
		return Tile.getTile(x,y).hasObjectKey();
	}

	/**
	 * Checks if the current tile where the player is standing has a hammer.
	 */
	public boolean checkHammer(int y, int x) {
		return Tile.getTile(x,y).hasObjectHammer();
	}

	/**
	 * Checks if the current tile where the player is standing is the exit tile. If so, sets the finish variable
	 * in Game to true.
	 */
	public void hasEnded(int y, int x) {
		if (Tile.getTile(x,y).end()) {
			Game.setFinish(true);
		}
	}

	/**
	 * Method to keep track of the score. It checks if the current position of the player changes by comparing it
	 * to the Position object (which implements the Observable class). If so, it notifies the change to the Observer 
	 * position and sets the position of the Position object to the current position.
	 */
	public void score() {
		if (Integer.parseInt(px.getPosition())  != Integer.parseInt(getCurrentPositionX()))
		{
			px.addObserver(Obsx);
			px.setPosition(getCurrentPositionX());
		}
		if (Integer.parseInt(py.getPosition())  != Integer.parseInt(getCurrentPositionY()))
		{
			py.addObserver(Obsy);
			py.setPosition(getCurrentPositionY());
		}
	}

	/**
	 * If the player has a key (checkKey returns true), then it updates the JLabels in the Display class.
	 */
	public void hasKey() {
		if (checkKey(Integer.parseInt(getCurrentPositionX()),Integer.parseInt(getCurrentPositionY()))) {
			Display.setKey("Yes");
			Display.setMessage("You have found a key! Try to go through a house!");
		}
	}

	/**
	 * If the player has a hammer (checkHammer returns true), then it updates the JLabels in the Display class.
	 */
	public void hasHammer() {
		if (checkHammer(Integer.parseInt(getCurrentPositionX()),Integer.parseInt(getCurrentPositionY()))) {
			Display.setHammer("Yes");
			Display.setMessage("You have found an ax! Try to cut a withered tree!");
		}
	}
	
	/**
	 * Checks if there is an enemy in the maze. In case there is one, if the player collides with the enemy 
	 * (they are both in the same position) the position of the player now will be that of the start.  
	 */
	public void checkEnemy() {
		if (enemy != null)
		if (Integer.parseInt(getCurrentPositionX()) == enemy.getCurrentPositionX() && Integer.parseInt(getCurrentPositionY()) == enemy.getCurrentPositionY()) {
			x = spawnX;
			y = spawnY;
		}
	}
	
	//Getters and setters

	public String getCurrentPositionX() {
		currentPositionX = (int) ((x+16)/Tile.TILE_WIDTH);
		return Integer.toString(currentPositionX);
	}

	public String getCurrentPositionY(){
		currentPositionY = (int) ((y+16)/Tile.TILE_WIDTH); 
		return Integer.toString(currentPositionY);
	}
	
}
