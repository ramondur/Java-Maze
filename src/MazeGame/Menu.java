package MazeGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.sound.sampled.*;

/**
 * This class contains the main method that starts a new game as well as the commands to generate the first GUI. 
 * @author Ramon Duran
 */

public class Menu {
	
	//VARIABLES
	
	private String path = "res/Worlds/BaseMaze.txt"; //path of the maze file. By default is equal to the path of the BaseMaze
	private String title; //name of the current maze
	private JFrame frame; //frame of the menu
	private String musicFile ="res/music/bells.wav"; //path of the file song
	
	//MAIN METHOD GAME
	/**
	 * The main method starts by creating a new menu and then calls the initialize method.
	 */
	public static void main(String[] args) {
		Menu menu = new Menu(); 
		menu.initialize();
		menu.frame.setVisible(true);
	}

	//METHODS
	
	/**
	 * Inicialize the menu of the game.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 150, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//An amount of JLabel with the instructions of the game
		JLabel lblWelcomeToMy = new JLabel("Welcome to my maze!");
		lblWelcomeToMy.setBounds(190, 17, 143, 16);
		frame.getContentPane().add(lblWelcomeToMy);
		JLabel lblTheInstructionsOf = new JLabel("The instructions of the game are very simple:");
		lblTheInstructionsOf.setBounds(15, 55, 480, 16);
		frame.getContentPane().add(lblTheInstructionsOf);
		JLabel lblTheObjectiveIs = new JLabel("   1- Help Santa get his presents back in the smallest number of steps.");
		lblTheObjectiveIs.setBounds(6, 85, 480, 16);
		frame.getContentPane().add(lblTheObjectiveIs);
		JLabel lblUseTheArrow = new JLabel("   2- Use the arrow keys to move trough the maze.");
		lblUseTheArrow.setBounds(6, 105, 480, 16);
		frame.getContentPane().add(lblUseTheArrow);
		JLabel lblWhileMoving = new JLabel("   3- A key and an ax are hidden in the maze.");
		lblWhileMoving.setBounds(6, 125, 480, 16);
		frame.getContentPane().add(lblWhileMoving);
		JLabel lblUseThis = new JLabel("   4- Use this objects to interact with houses and withered trees.");
		lblUseThis.setBounds(6, 145, 480, 16);
		frame.getContentPane().add(lblUseThis);
		JLabel lblWatchOut = new JLabel("   5- Watch out for the Grinch!");
		lblWatchOut.setBounds(6, 165, 480, 16);
		frame.getContentPane().add(lblWatchOut);
		JLabel lblNowPick = new JLabel("Pick the maze that you want to play or introduce a file path:");
		lblNowPick.setBounds(15, 195, 480, 16);
		frame.getContentPane().add(lblNowPick);

		//JRadioButton to select the BaseMaze. By default this radio button is selected
		JRadioButton rdbtnBaseMaze = new JRadioButton("BaseMaze");
		rdbtnBaseMaze.setBounds(15, 225, 480, 16);
		rdbtnBaseMaze.setSelected(true);
		frame.getContentPane().add(rdbtnBaseMaze);

		//JRadioButton to select the MidiMaze.
		JRadioButton rdbtnMidiMaze = new JRadioButton("MidiMaze");
		rdbtnMidiMaze.setBounds(15, 255, 480, 16);
		frame.getContentPane().add(rdbtnMidiMaze);

		//JRadioButton to select the TessiMaze.
		JRadioButton rdbtnTessiMaze = new JRadioButton("TessiMaze");
		rdbtnTessiMaze.setBounds(15, 285, 480, 16);
		frame.getContentPane().add(rdbtnTessiMaze);

		//JRadioButton to select the option to write a path of another file.
		JRadioButton rdbtnSelectOther = new JRadioButton("To write path of another file");
		rdbtnSelectOther.setBounds(15, 315, 480, 16);
		frame.getContentPane().add(rdbtnSelectOther);

		//The ButtonGroup component manages the selected/unselected state for a set of radio buttons.
		//For the group, the ButtonGroup instance guarantees that only one button can be selected at a time.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBaseMaze);
		group.add(rdbtnMidiMaze);
		group.add(rdbtnTessiMaze);
		group.add(rdbtnSelectOther);

		//This ActionListener states that once the rdbtnBaseMaze is pressed, the path of the game is going to
		//be the one for the BaseMaze.
		rdbtnBaseMaze.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				path = "res/Worlds/BaseMaze.txt";
			}
		});
		
		//This ActionListener states that once the rdbtnMidiMaze is pressed, the path of the game is going to
		//be the one for the MidiMaze.
		rdbtnMidiMaze.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				path = "res/Worlds/MidiMaze.txt";
			}
		});
		
		//This ActionListener states that once the rdbtnTessiMaze is pressed, the path of the game is going to
		//be the one for the TessiMaze.
		rdbtnTessiMaze.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				path = "res/Worlds/TessiMaze.txt";
			}
		});
		
		//This ActionListener states that once the rdbtnSelectOther is pressed, a new JOptionPane is created allowing the
		//player to enter a path for the file of the game and then saves this path.
		rdbtnSelectOther.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				path =  JOptionPane.showInputDialog(frame,"Please enter file path:"
						,"Message",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		//JButton to start the game.
		JButton btnStartGame = new JButton("START GAME");
		btnStartGame.setBounds(190, 340, 117, 29);
		frame.getContentPane().add(btnStartGame);

		//This ActionListener states that once the btnStartGame is pressed, the actual menu frame is dispose and then
		//a game is created and started and the method start from the game class is called. It also starts playing
		//the song in the musicFile.
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();  //Remove JFrame frame
				Pattern p = Pattern.compile(".*/(.*).txt"); //pattern to get the name of the maze from the file name. 
				Matcher m = p.matcher(path); //matches the pattern above in the path file
				if (!m.find()) {
					JOptionPane.showMessageDialog(frame,"WRONG PATH!" //if there is no path or is it a wrong file path it
							,"Warning",JOptionPane.WARNING_MESSAGE); //generates a JOptionPane with a warning message.
					System.exit(1);
				}
				title = m.group(1); //the name of the maze is saved as the title
				Game game = new 	Game(title,700,700,path);
				game.start();
				try
			    {
			        Clip clip = AudioSystem.getClip();
			        clip.open(AudioSystem.getAudioInputStream(new File(musicFile)));
			        clip.start();
			    }
			    catch (Exception exc)
			    {
			        exc.printStackTrace(System.out);
			    }
			}
		});
	}
	
}