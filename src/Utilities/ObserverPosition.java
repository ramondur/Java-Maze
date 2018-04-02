package Utilities;

import java.util.Observable;
import java.util.Observer;

import MazeGame.Display;

/**
 *This class implements the Observer class which is an interface to be informed of changes in observable objects.
 *In this case the change in the position of the player is observed.
 */

public class ObserverPosition implements Observer {
	
	//VARIABLES
	
	@SuppressWarnings("unused")
	private String position; //position of the player

	//CONSTRUCTOR
	
	public ObserverPosition() {
		position = null;
	}

	//METHODS
	
	/**
	 *This method is called whenever the observed object is changed. An application calls an Observable object's 
     *notifyObservers method to have all the object's observers notified of the change.	
     */
	public void update(Observable obj, Object arg) {
		if (arg instanceof String) {
			position = (String) arg;
			Display.setSteps(Display.getSteps()+1); //adds one to the setps variable in the Display class
		} 
	}
	
}
