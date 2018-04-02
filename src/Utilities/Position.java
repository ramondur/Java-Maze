package Utilities;

import java.util.Observable;

/**
 * The Position class implements the Observable class. 
 */

public class Position extends Observable {
	
	//VARIABLES
	
	private String position; //position of the player

	//CONSTRUCTOR
	
	/**
	 * It takes one variable: the current position of the player in the grid 
	 */
	public Position(String p) {
		this.position = p; 
	}

	//Getters and setters
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String p) {
		this.position = p;
		setChanged();
		notifyObservers(p); //if this object has changed, as indicated by the hasChanged method, then notify the observer
	}

}