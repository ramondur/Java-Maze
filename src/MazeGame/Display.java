package MazeGame;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 *The class that generates the main GUI that allows the visualization of an ongoing game and manages 
 *player input and update output. 
 */

public class Display {

	//VARIABLES
	
	private int width; //width of the display in pixels
	private int height; //height of the display in pixels
	private String title; //name of the display which is equal to the name of the current maze
	private  String highScore; //highest score store in the scores file for the current maze
	private static int steps; //number of steps taken by the player
	private static String message; //message shown in certain situations
	private static String key; //indicates whether a player has a key (Yes) or not (No)
	private static String hammer; //indicates whether a player has a hammer (Yes) or not (No)
	private String name; //name of the player who has the highest score
	private String mazeType; //name of the current maze
	private JFrame frame; //frame of the display
	private Canvas canvas; //canvas of the display
	private JLabel lblHighScore; //label of the highest score
	private JLabel lblSteps; //label of the number of steps
	private JLabel lblKey; //label of the key
	private JLabel lblHammer; //label of the hammer
	private JLabel lblMessage; //label of the message
	private FileInputStream fis; //file input stream object
	private BufferedReader br; //buffered reader object
	
	List<String> highScores = new ArrayList<String>(); //list that stores all the scores for the current maze

	//CONSTRUCTOR
	
	/**
	 * It takes three variables: the title, the width and the height. It also call some methods to generate and
	 * set the display.
	 */
	public Display (String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height =height;

		setMessage("");
		setSteps(0);
		setKey("No");
		setHammer("No");
		setHighScore("0");
		createDisplay();
	}
	
	//METHODS
	
	/**
	 * Creates the display of the game.
	 */
	private void createDisplay() {
		//initialize the frame of the display
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		frame.setTitle(title);
		frame.setBounds(0, 0, width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize the canvas of the display
		canvas = new Canvas();
		canvas.setBounds(0, 0, width, height-100);
		canvas.setFocusable(false);
		frame.add(canvas);
		
		//initialize the highest score label
		readScoreFile();
		lblHighScore = new JLabel("High score: "+getHighScore());
		lblHighScore.setBounds(31, 656, 250, 16);
		frame.add(lblHighScore);
		
		//initialize the number of steps label and set the label with the current number of steps.
		//the action to update the number of steps is accomplished by the timerStep every 0.1 seconds.
		lblSteps = new JLabel("Number of steps = "+getSteps());
		lblSteps.setBounds(31, 628, 200, 16);
		frame.add(lblSteps);
		ActionListener uptdateSteps = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lblSteps.setText("Number of steps = " + getSteps()); 
			}
		};
		Timer timerStep = new Timer(100 ,uptdateSteps);
		timerStep.start();
		
		//initialize the key label and set the label wether the player has the key or not.
		//the action to update the key state is accomplished by the timerKey every 0.5 seconds.
		lblKey = new JLabel("Key = "+getKey());
		lblKey.setBounds(401, 628, 110, 16);
		frame.add(lblKey);
		ActionListener updateKey = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lblKey.setText("Key = " + getKey());
			}
		};
		Timer timerKey = new Timer(500 ,updateKey);
		timerKey.start();

		//initialize the hammer label and set the label wether the player has the hammer or not.
		//the action to update the hammer state is accomplished by the timerHammer every 0.5 seconds.
		lblHammer = new JLabel("Ax = "+getHammer());
		lblHammer.setBounds(401, 656, 110, 16);
		frame.add(lblHammer);
		ActionListener updateHammer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lblHammer.setText("Ax = " + getHammer());
			}
		};
		Timer timerHammer = new Timer(500 ,updateHammer);
		timerHammer.start();

		//initialize the message label and set the label when some events happen.
		//the action to update the message state is accomplished by the timerHammer every 0.5 seconds.
		lblMessage = new JLabel(getMessage());
		lblMessage.setBounds(31, 600, 633, 16);
		frame.add(lblMessage);
		ActionListener updateMessage = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				lblMessage.setText(getMessage());
			}
		};
		Timer timerMessage = new Timer(500 ,updateMessage);
		timerMessage.start();
		
		frame.setVisible(true);
	}
	
	/**
	 * Reads the score file and set the highest score for the current maze. If the file does not exist
	 * or there is no score for the currrent maze, the highest score remains 0.
	 */
	public void readScoreFile() {
		Path file = Paths.get("Scores.csv");
		if (Files.exists(file)) {
			try {
				fis = new FileInputStream("Scores.csv");
				br = new BufferedReader(new InputStreamReader(fis));
				String line = br.readLine(); //skips the header of the file
				while ((line = br.readLine()) != null) { //reads each line of the file
					Pattern p = Pattern.compile("(.+)?,(.+),(.+)"); 
					Matcher m = p.matcher(line);
					m.find(); //searches for the pattern in the current line
					name = m.group(1); //the name of the player is equal to first group of the pattern 
					mazeType = m.group(2); //the name of the current maze is equal to second group of the pattern 
					if (mazeType.equals(title)) { //checks if the line contains a score of the current maze
						highScores.add(m.group(3)+" by "+name); //adds the score and the name to the array list 
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
					fis.close();
					System.gc();
				}catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			String[] highScoresArray = new String[ highScores.size() ]; //initialize new array with the size of the array list highScores
			highScores.toArray( highScoresArray ); //converts the array list highScores into the new array
			int maxValue = Integer.MAX_VALUE;
			int count = 0;
			for (int i=0; i < highScoresArray.length; i++) { //loop through each element of the array 
				Pattern p = Pattern.compile("(.+?) .*");
				Matcher m = p.matcher( highScoresArray[i]);
				m.find(); //searches for the pattern in the current element
				highScore = m.group(1); //the highScore is equal to first group of the pattern 
				
				//checks if the highScore is smaller than the maxValue. If it is then the maxValue is equal to the highScore
				//and count is equal to i. This way the position in the array (i) of the lowest highScore (highest score) is
				//stored in count and the used to set the highest score of the current maze in the display.
				if (Integer.parseInt(highScore) < maxValue) { 
					maxValue = Integer.parseInt(highScore); 
					count = i;
				}
				setHighScore(highScoresArray[count]);
			}
		}
	}
	
	//Getters and setters

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}

	public String getMessage() {
		return message;
	}
	
	public static void setMessage(String message) {
		Display.message = message;
	}

	public static void setSteps(int steps) {
		Display.steps = steps;
	}
	
	public static int getSteps() {
		return steps;
	}

	public static void setKey(String key) {
		Display.key = key;
	}
	
	public static String getKey() {
		return key;
	}

	public static void setHammer(String hammer) {
		Display.hammer = hammer;
	}
	
	public static String getHammer() {
		return hammer;
	}
	
	public void setHighScore(String hs) {
		highScore = hs;
	}
	
	public String getHighScore() {
		return highScore;
	}
	
}
