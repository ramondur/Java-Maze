package MazeGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This class reads the input file with the coordinates and characteristics of the maze.
 */

public class MazeFile {

	//VARIABLES
	
	private int xcoordinate; //number at the x coordinate position in the maze file
	private int ycoordinate; // number at the y coordinate position in the maze file
	private String northwall; //element at the north wall position in the maze file
	private String southWall; // element at the south wall position in the maze file
	private String eastWall; //element at the east wall position in the maze file
	private String westWall; //element at the west wall position in the maze file
	private String object; //element at the object position in the maze file
	private static int width; //width of the maze
	private static int height; //height of the maze
	private static String[][] grid; //grid of the maze
	
	//METHODS
	
	/**
	 * Reads the input file with the characteristics of the maze and generates a grid (matrix) with all
	 * the elements. The coordinate system used in this game is explained in the report.
	 */
	public static void loadFile(String path) {

		//ArrayList which will contain all the information in the file
		ArrayList<MazeFile> elements = new ArrayList<MazeFile>();

		File file = new File(path); //creates file object passing as argument the path of the maze file
		Scanner s = null; //creates a scanner object

		try {
			s = new Scanner(file); //initialize the scanner object with a file source (the path of the maze file)
			s.nextLine(); //skips the line containig the header
			while (s.hasNextLine()){ //while there is a line in the file 
				String line = s.nextLine();	//stores the content of the line in a variable named line
				String [] cutString = line.split(","); //splits this variable line by commas and stores each element in an array	
				MazeFile element = new MazeFile(); //creates a MazeFile object

				element.setXcoordinate(Integer.parseInt(cutString[0])); //sets the xcoordinate of the element object
				element.setYcoordinate(Integer.parseInt(cutString[1])); //sets the ycoordinate of the element object
				element.setNorthwall(cutString[2]); //sets the northwall of the element object
				element.setSouthwall(cutString[3]); //sets the southwall of the element object
				element.setEastWall(cutString[4]); //sets the eastwall of the element object
				element.setWestWall(cutString[5]); //sets the westwall of the element object
				element.setObject(cutString[6]); //sets the object of the element object

				elements.add(element); //adds the element object to the ArrayList
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if (s != null)
					s.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		//this first iterator will iterate over the whole elements ArrayList  and the final x coordinate and y coordinate
		//will be used to define the width and height of the maze
		Iterator<MazeFile> itrElements1 = elements.iterator();
		while(itrElements1.hasNext()) {
			MazeFile element = itrElements1.next();
			//the coordinate system is explained in the report. The width and height are always the final x and y 
			//coordinate in the maze file times 2 plus 3
			width = ((element.getXcoordinate())*2+3); 
			height = ((element.getYcoordinate())*2+3); 
		}	

		grid = new String[width][height]; //creates grid array with the dimensions specified by the width and height
		
		//this second iterator will iterate over the whole elements ArrayList and then store each element in the grid array 
		//in a specific position. The coordinate system is explained in the report.
		Iterator<MazeFile> itrElements2 = elements.iterator();
		while(itrElements2.hasNext()){
			MazeFile element = itrElements2.next();
			grid[width-(2*(element.getXcoordinate())+1)-1][2*(element.getYcoordinate())+1] = element.getObject();
			grid[width-(2*(element.getXcoordinate())+2)-1][(2*element.getYcoordinate())+1] = element.getNorthwall();
			grid[width-(2*(element.getXcoordinate()))-1][2*(element.getYcoordinate())+1] = element.getSouthwall();
			grid[width-(2*(element.getXcoordinate())+1)-1][2*(element.getYcoordinate())+2] = element.getEastWall();
			grid[width-(2*(element.getXcoordinate())+1)-1][2*(element.getYcoordinate())] = element.getWestWall();
		}
	}

	//Getters and setters

	public int getXcoordinate() {
		return xcoordinate;
	}

	public void setXcoordinate(int xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public int getYcoordinate() {
		return ycoordinate;
	}

	public void setYcoordinate(int ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	public String getNorthwall() {
		return northwall;
	}

	public void setNorthwall(String northwall) {
		this.northwall = northwall;
	}
	
	public String getSouthwall() {
		return southWall;
	}

	public void setSouthwall(String southWall) {
		this.southWall = southWall;
	}

	public String getEastWall() {
		return eastWall;
	}

	public void setEastWall(String eastWall) {
		this.eastWall = eastWall;
	}

	public String getWestWall() {
		return westWall;
	}

	public void setWestWall(String westWall) {
		this.westWall = westWall;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static String[][] getGrid() {
		return grid;	
	}
	
}

