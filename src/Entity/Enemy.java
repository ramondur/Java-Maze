package Entity;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import MazeGame.Game;
import MazeGame.Images;
import Tiles.Tile;

/**
 * This class contains all methods related to the player.
 */

public class Enemy extends Entity {
	
	private int rn; //random number integer
	private int currentPositionX; //current x position of the player in grid units
	private int currentPositionY; //current y position of the player in grid units
	
	/**
	 * It takes three variables: the game object, the x coordinate and the y coordinate. 
	 */
	public Enemy(Game game, float x, float y) {
		super(game,x,y);
		updateMove (); //calls the updateMove method
	}
	
	/**
	 * Move method for the enemy. This method is outside the game loop so its not constantly running 
	 * (which will generate a new random number every 0.01 seconds). Instead the random number is generated using
	 * a Timer (fires one or more ActionEvents at specified intervals) every 1.5 seconds.
	 */
	public void updateMove () {
		Random  random = new Random();
		ActionListener updateMove = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rn = random.nextInt(2);	 //generates a random number (0 or 1)
			}
		};
		Timer timerMovement = new Timer(1500 ,updateMove);
		timerMovement.start();
	}

	/**
	 * Tick method for the enemy. Moves the enemy up (rn==1) or down (rn==0) depending on the random number.
	 */
	@Override
	public void tick() {
		if (rn == 0) {
			moveDown();
		}
		else {
			moveUp();
		}
	}

	/**
	 * Renders the enemy image to the screen.
	 */
	@Override
	public void render(Graphics g) {	
		g.drawImage(Images.enemy, (int)x, (int)y, null);
	}
	
	//Getters and setters
	
	public int getCurrentPositionX() {
		currentPositionX = (int) ((x)/Tile.TILE_WIDTH); 
		return currentPositionX;
	}

	public int getCurrentPositionY(){
		currentPositionY = (int) ((y)/Tile.TILE_WIDTH);
		return currentPositionY;
	}
	
}
