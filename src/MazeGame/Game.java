package MazeGame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Enemy;
import Entity.Player;
import Tiles.Tile;
import Utilities.KeyManager;

/**
 *Main class of the game which contains a thread that allows us to run the class separately 
 *from the rest of the program. It implements the interface Runnable and contains the run method 
 *which will execute the game loop.
 */

public class Game implements Runnable {
	
	//VARIABLES
	
	private String title; //name of the display which is equal to the name of the current maze
	private int width; //width of the display in pixels
	private int height; //height of the display in pixels
	private String path; //path of the maze file
	private boolean running = false; //boolean variable running set to false
	private static boolean finish = false; //boolean variable finish set to false
	private String playerName; //name of the player of the current game
	private String headerFile = "PLAYERNAME,MAZENAME,NUMBER_OF_STEPS_SOLVED"; //header of the Score file
	
	private BufferStrategy bs; //buffer strategy object
	private Graphics g; //graphics object
	private KeyManager keyManager; //keymanager object

	private Display display;	 //display object
	private Thread thread; //thread object
	private Player player; //player object
	private Enemy enemy; //enemy object
	private World world; //world object

	//CONSTRUCTOR
	
	/**
	 * It takes four variables: the title, the width, the height and the file path of the game. 
	 */
	public Game(String t, int w, int h, String m) {
		title = t;
		width = w;
		height = h;
		path = m;
	}
	
	//METHODS
	
	/**
	 * Start method of the game.
	 */
	public synchronized void start() {
		if (running)
			return;
		running =true; //set the boolean variable runnig to true
		keyManager = new KeyManager(); //creates a new KeyManager
		thread = new Thread(this); //creates a new Thread
		thread.start(); //Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
	}
	
	/**
	 * Run method of the game. This method contains the game loop and all its logic.
	 */
	public void run() {
		init(); //calls init method

		int fps = 60; //the amount of times the tick and render methods are called each second
		double timePerTick = 1000000000 / fps; //the maximum amount of time in nanoseconds to execute the tick and render methods
		double delta = 0; //the amount of time before calling the tick and render method 
		long now; 
		long lastTime = System.nanoTime();

		//runs the loop while the runnig variable is true. It checks if the delta variable is greater or equal to one,
		//which means that the time to perform a new tick and render has been achieved.
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick(); //calls tick method
				render(); //calls render method
				delta--; //subtracts 1 to the delta variable
				stop(); //calls stop method
			}
		}
	}

	/**
	 * Method to initialize all the game components. This method is outside the game loop.
	 */
	private void init() {
		display = new Display(title,width,height); //creates a new display and pass as arguments the title, width and height
		display.getFrame().addKeyListener(keyManager); //adds to the frame of the game the KeyManager object
		Images.init(); //calls init method in Images class
		world = new World(path); //creates a new world and pass as arguments the path of the file game
		//if the starting X coordiante of the enemy is different to 0 (meaning that there is an enemy in the file
		//game) then it will create a new enemy passing as arguments the game object and the starting X and Y coordinate 
		//in the GUI.
		if (world.getSpawnEX()!=0) {
			enemy = new Enemy(this, Tile.TILE_WIDTH*world.getSpawnEY(),Tile.TILE_WIDTH*world.getSpawnEX());
		}
		//creates a new player passing as arguments the game object, the starting X and Y coordinate of the 
		//player in the GUI and the enemy object.
		player = new Player(this, world.getSpawnPY()*Tile.TILE_WIDTH,world.getSpawnPX()*Tile.TILE_WIDTH, enemy);
	}
	
	/**
	 * Tick method of the game. This method is inside the game loop and will update the game logic (objects and variables)
	 * before rendering
	 */
	private void tick() {
		keyManager.tick(); //calls the tick method in KeyManager
		player.tick(); //calls the tick method in Player
		if (enemy!=null) //calls the tick method in Enemy if there is an object enemy
			enemy.tick(); 
	}
	
	/**
	 * Render method of the game. This method is inside the game loop and will control all of the graphics 
	 * processing and drawing in the screen.
	 */
	private void render() {
		bs = display.getCanvas().getBufferStrategy(); //creates a new Buffer ("hidden screen")
		if (bs==null)
		{
			display.getCanvas().createBufferStrategy(3);	
			return;
		}
		g= bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);

		world.render(g); //calls the render method in KeyManager
		player.render(g); //calls the render method in Player
		if (enemy!=null) { //calls the render method in Enemy if there is an object enemy
			enemy.render(g);
		}

		bs.show();
		g.dispose();
	}
	
	/**
	 * Method to stop the game loop. It also calls the method to write the score to a file and
	 * finally terminates the Java Virtual Machine. 
	 */
	public void stop() {
		if(!getFinish()) //it only goes through when the finish boolean variable is set to true
			return;
		running=false; //change boolean variable running to false (stops the game loop)
		//JOptionPane to ask the player for its name (input) and stores it into the variable playerName
		playerName = JOptionPane.showInputDialog(display.getFrame(),"YOU WON!!!\nPlease enter your name:"
				,"Message",JOptionPane.INFORMATION_MESSAGE);
		writeToFile(); //calls the writeToFile method
		System.exit(1); //terminates the currently running Java Virtual Machine
	}
	
	/**
	 * Method to write the score of the game in a Score file. If this Score file does not exist, it will
	 * create it.
	 */
	public void writeToFile() {
		Path file = Paths.get("Scores.csv"); //stores the path of the file in a Path object
		if (Files.exists(file)) { //checks if the file exist (true)
			try
			{
				String filename= "Scores.csv"; //name of the Score file
				//Constructs a FileWriter object given a file name with a boolean indicating whether or 
				//not to append the data written. In this case the boolean is set to true
				FileWriter fw = new FileWriter(filename,true); 
				//writes a string containg the player name, title and steps. These variables are separed by 
				//commas because are saved in a csv file.
				fw.write(playerName+","+title+","+Display.getSteps()+"\n"); 
				fw.close(); //closes the stream
			}
			catch(IOException ioe)
			{
				System.err.println("IOException: " + ioe.getMessage());
			}
		}

		if (Files.notExists(file)) { //checks if the file does not exist (false)
			try {
				String scoreLine = playerName+","+title+","+Display.getSteps(); //string containg the player name, title and steps.
				List<String> lines = Arrays.asList(headerFile,scoreLine); //list containg the headerFile and scoreLine
				//Creates a file and writes lines to it
				Files.write(file, lines,Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//Getters and setters

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public static void setFinish(boolean finish) {
		Game.finish = finish;
	}
	
	public static boolean getFinish() {
		return finish;
	}

}
