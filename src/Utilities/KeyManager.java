package Utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *This class implements the KeyListener class which is an interface to receive keyboard events and process them.
 *It manages the keyboard inputs of the game.
 */

public class KeyManager implements KeyListener {

	//VARIABLES
	
	private boolean[] keys; //array with the keys used in the game
	public boolean up; //boolean variable for moving up
	public boolean down; //boolean variable for moving down
	public boolean left; //boolean variable for moving left
	public boolean right; //boolean variable for moving right

	//CONSTRUCTOR
	
	public KeyManager() {
		keys = new boolean[256];
	}

	//METHODS
	
	/**
	 *Checks each time if a KeyEvent is happening.
	 */
	public void tick() {
		up = keys[KeyEvent.VK_UP]; //when the up arrow key is pressed the up variable is set to true
		down = keys[KeyEvent.VK_DOWN]; //when the down arrow key is pressed the down variable is set to true
		left = keys[KeyEvent.VK_LEFT]; //when the left arrow key is pressed the left variable is set to true
		right = keys[KeyEvent.VK_RIGHT]; //when the right arrow key is pressed the right variable is set to true
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getExtendedKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getExtendedKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}
	
}
