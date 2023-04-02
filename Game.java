package MazeFinal3;
import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;


import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.Random;
public class Game extends JFrame implements ActionListener 
{
	
	Random random = new Random();
	MazePanel MazeDisplay;
	Maze maze = new Maze();//new instance of Maze class
	Mouse mouse;
	
	//Set up menu options
	JMenuBar Menubar = new JMenuBar();
	JMenuItem GenerateMaze = new JMenuItem("GENERATE MAZE");
	JMenuItem ReadFile = new JMenuItem("READ MAZE FROM FILE");
	JMenuItem ReturnToTitle = new JMenuItem("RETURN TO TITLE SCREEN");
	
	//Set up panel and input text fields for getting columns and rows of maze for generation
	String[] MazeTypes = {"Refined Maze", "Unrefined Maze"};
	String[] ExitTypes = {"Guaranteed Exit", " May Or May Not Have Exit"};
	JPanel MazeGenerationPanel = new JPanel();
	JPanel RowsPanel = new JPanel();
	JPanel ColumnsPanel = new JPanel();
	JPanel ExitTypePanel = new JPanel();
	JPanel MazeTypePanel = new JPanel();
	JComboBox MazeTypeComboBox = new JComboBox(MazeTypes);
	JComboBox ExitTypeComboBox = new JComboBox(ExitTypes);
	JTextField ColumnsField = new JTextField(20);
	JTextField RowsField = new JTextField(20);
	JLabel RowsPrompt = new JLabel("Please enter the number of rows you would like your maze to have:");
	JLabel ColumnsPrompt = new JLabel("Please enter the number of columns you would like your maze to have:");
	JLabel MazeGenerationInstructions = new JLabel("Please specify the amount of rows and columns you would like your maze to have. Additionally, you will have to choose whether");
	JLabel MazeGenerationInstructions2 = new JLabel("or not you want your maze to be refined and whether or not you would like a guaranteed exit to be generated. The minimum maze ");
	JLabel MazeGenerationInstructions3 = new JLabel("size is 5 x 5.");
	JLabel MazeTypePrompt = new JLabel("Please select what type of maze you would like:");
	JLabel ExitTypePrompt = new JLabel("Please select whether you want a guaranteed exit:");
	JLabel MazeGenerationTitle = new JLabel("MAZE GENERATION");
	JLabel InvalidInput = new JLabel();
	JLabel Divider = new JLabel("-----------------------------------------------------------------------------------------------------");
	JLabel InstructionsDisclaimer = new JLabel("INSTRUCTIONS:");
	BufferedImage SmallMazePicture = ImageIO.read(new File("SmallMaze.png"));
	JLabel SmallMazeLabel = new JLabel(new ImageIcon(SmallMazePicture));
	JButton GenerateButton = new JButton("Generate Maze!");
	
	//Set up panel and file finder for reading maze from file
	JPanel FileReaderPanel= new JPanel();
	JPanel FilePanel = new JPanel();
	JLabel FileLabel = new JLabel("Selected File Path: ");
	JTextField FileField= new JTextField(30);
	JLabel FileMazeTitle = new JLabel ("READ MAZE FROM FILE");
	JLabel ReadFileInstructions = new JLabel("Using the file finder, select the file you would like to read the maze from. Click \"Open\" button to select file.");
	JLabel ReadFileInstructions2 = new JLabel("Alternatively, you may also directly enter your file location into the file text field.");
	JFileChooser fileChooser = new JFileChooser();
	JButton GenerateFileMaze = new JButton("Generate Maze From File!");
	File MazeFile;
	//Set up title screen
	JPanel TitlePanel = new JPanel();
	JLabel Title = new JLabel("MAZE GAME");
	JLabel TitleMessage = new JLabel("Please use menu bar at top of the page to navigate GUI.");
	BufferedImage MazePicture = ImageIO.read(new File("Maze.png"));
	JLabel MazeLabel = new JLabel(new ImageIcon(MazePicture));

	public Game() throws Exception
	{
		setSize(1920,1080);
		setTitle("Menu");
		BoxLayout layout1= new BoxLayout(MazeGenerationPanel, BoxLayout.Y_AXIS);
		BoxLayout layout2= new BoxLayout(FileReaderPanel, BoxLayout.Y_AXIS);
		BoxLayout layout3 = new BoxLayout(TitlePanel,BoxLayout.Y_AXIS);
		
		//SET UP TITLE SCREEN GUI
		TitlePanel.setLayout(layout3);
		
		Title.setFont(new Font("Serif",Font.BOLD,90));
		TitleMessage.setFont(new Font("Serif", Font.BOLD, 14));
		
		TitlePanel.add(Title);
		TitlePanel.add(Divider);
		TitlePanel.add(TitleMessage);
		TitlePanel.add(MazeLabel);
		
		//SET UP GENERATE MAZE FROM FILE GUI
		FileReaderPanel.setLayout(layout2);//all panels are set to vertical boxlayout
		FilePanel.add(FileLabel);
		FilePanel.add(FileField);
		FileMazeTitle.setFont(new Font("Serif",Font.BOLD,36));
		InstructionsDisclaimer.setFont(new Font("Serif", Font.BOLD, 14));
		
		FileReaderPanel.add(FileMazeTitle);
		FileReaderPanel.add(InstructionsDisclaimer);
		FileReaderPanel.add(ReadFileInstructions);
		FileReaderPanel.add(ReadFileInstructions2);
		FileReaderPanel.add(Divider);
		FileReaderPanel.add(fileChooser);
		FileReaderPanel.add(FilePanel);
		FileReaderPanel.add(GenerateFileMaze);
		
		
		//SET UP MAZE GENRATION GUI
		MazeGenerationPanel.setLayout(layout1);//all panels are set to vertical boxlayout
		MazeGenerationTitle.setFont(new Font("Serif",Font.BOLD,36));
		InstructionsDisclaimer.setFont(new Font("Serif", Font.BOLD, 14));
		
		RowsPanel.add(RowsPrompt);
		RowsPanel.add(RowsField);
		ColumnsPanel.add(ColumnsPrompt);
		ColumnsPanel.add(ColumnsField);
		MazeTypePanel.add(MazeTypePrompt);
		MazeTypePanel.add(MazeTypeComboBox);
		ExitTypePanel.add(ExitTypePrompt);
		ExitTypePanel.add(ExitTypeComboBox);
		
		MazeGenerationPanel.add(MazeGenerationTitle);
		MazeGenerationPanel.add(Divider);
		MazeGenerationPanel.add(InstructionsDisclaimer);
		MazeGenerationPanel.add(MazeGenerationInstructions);
		MazeGenerationPanel.add(MazeGenerationInstructions2);
		MazeGenerationPanel.add(MazeGenerationInstructions3);
		MazeGenerationPanel.add(Divider);
		MazeGenerationPanel.add(ColumnsPanel);
		MazeGenerationPanel.add(RowsPanel);
		MazeGenerationPanel.add(MazeTypePanel);
		MazeGenerationPanel.add(ExitTypePanel);
		MazeGenerationPanel.add(GenerateButton);
		MazeGenerationPanel.add(InvalidInput);
		MazeGenerationPanel.add(SmallMazeLabel);
		//SET UP ACTION LISTNERS
		GenerateMaze.addActionListener(this);
		ReadFile.addActionListener(this);
		fileChooser.addActionListener(this);
		ReturnToTitle.addActionListener(this);
		GenerateButton.addActionListener(this);
		GenerateFileMaze.addActionListener(this);
		
		Menubar.add(GenerateMaze);
		Menubar.add(ReadFile);
		Menubar.add(ReturnToTitle);
		
		add(TitlePanel);
		
		setJMenuBar(Menubar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) throws Exception
	{
		Game frame = new Game();

	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String command = e.getActionCommand();
		System.out.println(command);
		
		if(command.equals("GENERATE MAZE"))
		{
			this.getContentPane().removeAll();
			this.repaint();
			this.add(MazeGenerationPanel);
			System.out.println(MazeTypeComboBox.getSelectedItem());
			this.revalidate();
		}
		else if(command.equals("RETURN TO TITLE SCREEN"))
 		{
 			this.getContentPane().removeAll();
 			this.repaint();
 			this.add(TitlePanel);
 			this.revalidate();
 		}
		else if(command.equals("READ MAZE FROM FILE"))
		{
			this.getContentPane().removeAll();
			this.repaint();
			this.add(FileReaderPanel);
			this.revalidate();
			

		}
		else if(command.equals(JFileChooser.APPROVE_SELECTION))
		{
			FileField.setText(fileChooser.getSelectedFile().getPath());
			
		}
		else if(command.equals("Generate Maze From File!"))
		{
			this.getContentPane().removeAll();
			this.repaint();
			
			MazeFile = new java.io.File(FileField.getText());
			try {
				maze.ReadFile(MazeFile);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			mouse = new Mouse();
        	mouse.SolveMaze();
        	
        	try {
				MazeDisplay = new MazePanel(maze.map,mouse.ShortestMazeSolution);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	this.add(MazeDisplay);
    		this.revalidate();
    		
		}
		else if(command.equals("Generate Maze!"))
		{
			
			this.getContentPane().removeAll();
			this.repaint();
			boolean ExitExists;
			int columns = Integer.parseInt(ColumnsField.getText());
			int rows = Integer.parseInt(RowsField.getText());
			
			if(MazeTypeComboBox.getSelectedItem().equals("Refined Maze"))
			{
				maze.GenerateMaze(rows, columns);
				if(ExitTypeComboBox.getSelectedItem().equals("Guaranteed Exit"))
				{
					maze.GenerateExit();
				}
				else
				{
					ExitExists = random.nextBoolean();
					if(ExitExists)maze.GenerateExit();
				}
				mouse = new Mouse();
        		mouse.SolveMaze();
        		
        		try {
					MazeDisplay = new MazePanel(maze.map,mouse.ShortestMazeSolution);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		this.add(MazeDisplay);
        		this.revalidate();
        		
			}
			else
			{
				
				if(ExitTypeComboBox.getSelectedItem().equals("Guaranteed Exit"))
				{
					do
					{
						maze.GenerateUnrefinedMaze(rows, columns);
						maze.GenerateExit();
						mouse = new Mouse();
		        		mouse.SolveMaze();
					}while(mouse.ShortestMazeSolution.size()==0);
				}
				else
				{
					ExitExists = random.nextBoolean();
					maze.GenerateUnrefinedMaze(rows, columns);
					if(ExitExists)maze.GenerateExit();
					mouse = new Mouse();
	        		mouse.SolveMaze();
				}
				
        		
        		try {
					MazeDisplay = new MazePanel(maze.map,mouse.ShortestMazeSolution);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		this.add(MazeDisplay);
        		this.revalidate();
        		
			}
		}
		
	}
}
class MazePanel extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	static Random random = new Random();
	static Scanner sc = new Scanner(System.in);//new global instance of scanner class
	ArrayList<Coords> PathOutOfMaze;
	static Mouse mouse;//initialize new instance of Mouse class
	static Coords StartCoords;
	JPanel MazePanel = new JPanel();
	JPanel[][] blocks;//each panel represents a block in the maze
	JButton StartGame = new JButton("Find Exit");
	JLabel MazeImpossibleDisclaimer = new JLabel();
	JLabel Legend = new JLabel("LEGEND: Black = barrier, Red = Mouse Start, Blue = Mouse Path, Green = Exit");
	BufferedImage myPicture = ImageIO.read(new File("Mouse.png"));
	ImageIcon MouseIcon = new ImageIcon(myPicture);
	JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	
	
	public MazePanel(char[][] maze, ArrayList<Coords> PathOutOfMaze)throws Exception
	{
		
		
		this.PathOutOfMaze = PathOutOfMaze;
		blocks = new JPanel[maze.length][maze[0].length];
		
		GridLayout layout = new GridLayout(maze.length,maze[0].length);//create a gridlayout instance with 3 columns and 3 rows and horizontal and vertical gaps of 50 pixels
		
		BoxLayout layout1 = new BoxLayout(this,BoxLayout.Y_AXIS);//create a new box layout instance where compnents are organized vertically
		BorderLayout layout2 = new BorderLayout();
		
		//Apply Layouts
		MazePanel.setLayout(layout);
		MazePanel.setPreferredSize(getPreferredSize());
		setLayout(layout1);
		
		//Set up action listener
		StartGame.addActionListener(this);
		//Set up maze
		for(int x = 0; x < maze.length; x++)
		{
			for(int y = 0; y < maze[0].length; y++)
			{
				blocks[x][y] = new JPanel();
				
				blocks[x][y].setLayout(layout2);//set to borderlayout
				
				//make barriers black
				if(maze[x][y] == Maze.barrier)
					blocks[x][y].setBackground(Color.BLACK);
				
				//make starting point red
				else if(maze[x][y]==Maze.start)
				{
					blocks[x][y].setBackground(Color.RED);
					blocks[x][y].add(picLabel);
					StartCoords = new Coords(x,y);//record starting coordinates
				}
				//make exit green
				else if(maze[x][y]==Maze.exit)
					blocks[x][y].setBackground(Color.GREEN);
				
				MazePanel.add(blocks[x][y]);
			}
		}
		
		
		add(StartGame);
		add(Legend);
		add(MazeImpossibleDisclaimer);
		add(MazePanel);
		
	
		setVisible(true);
		
	}
	
	// ACTION LISTENER - This method runs when an event occurs
	  // Code in here only runs when a user interacts with a component
	  // that has an action listener attached to it
	
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		
		
		if(command.equals("Find Exit"))//if the find button pressed, find the exit
		{
			
			if(PathOutOfMaze.size()>0)
			{
				
				
				
				
				for(int x =0; x < PathOutOfMaze.size(); x++)
				{
					if(x>1)
					{
						
						blocks[PathOutOfMaze.get(x-1).x][PathOutOfMaze.get(x-1).y].remove(picLabel);
						blocks[PathOutOfMaze.get(x-1).x][PathOutOfMaze.get(x-1).y].setBackground(Color.BLUE);
						blocks[PathOutOfMaze.get(x-1).x][PathOutOfMaze.get(x-1).y].repaint();//normally background colour updates automatically. However, when the maze becomes really big, java starts lagging and the it might take multiple button clicks to  ge the path to show. This repaint will fix that issue.
						
						
						System.out.println(PathOutOfMaze.get(x).x + " " +PathOutOfMaze.get(x).y );
						blocks[PathOutOfMaze.get(x).x][PathOutOfMaze.get(x).y].add(picLabel);
						blocks[PathOutOfMaze.get(x).x][PathOutOfMaze.get(x).y].setBackground(Color.GREEN);
						blocks[PathOutOfMaze.get(x).x][PathOutOfMaze.get(x).y].repaint();//normally background colour updates automatically. However, when the maze becomes really big, java starts lagging and the it might take multiple button clicks to  ge the path to show. This repaint will fix that issue.
						
						picLabel.revalidate();
						picLabel.repaint();
						
					}
					
					
				}
				
				this.revalidate();
				this.repaint();
				picLabel.revalidate();
				picLabel.repaint();
				
			}
			else
			{
				MazeImpossibleDisclaimer.setText("Maze Is Impossible!");
			}
			
		}
	}

}

class Maze 
{
	 
	
	 
	static char barrier = 'B';
	static char openPath = 'O';
	static char exit = 'X';
	static char start = 'S';
	static private Scanner sc = new Scanner(System.in);
	static private Coords StartCoords;
	static private int[][]MazeGenerationMap;
	static private ArrayList<Coords> MazeGenerationRecord = new ArrayList<Coords>();
	static private Coords[] Directions = {new Coords(1,0), new Coords(-1,0), new Coords(0,1), new Coords(0,-1)};
	static char[][] map;
	static Random random = new Random();//create new instance of random class
	
	static private boolean WithinBounds(int x, int y)//Function Method: Returns true if cell is within bounds of the array
	{
		if(x > 0 && x < map.length - 1 && y > 0 && y < map[0].length - 1)//if the coordinate is within the bounds of the 2d array, execute the block that follows
		{
			return true;//return true if the coordinate is within bounds of the maze map
		}
		else//if the prior condition is false, execute the block that follows
		{
			return false;//return false if the coordinate is unsafe
		}
	}
	 
	 static private boolean contains(ArrayList<Coords> record, int x, int y) //checks wether the given cell has already been visited in the maze generation
	 //ArrayList.contains doesn't work for my class Coords
	 //You must compare the Coords.x and Coords.y individually
	 {
	        for (Coords cell : record) //loop through cells in list
	        {
	            if (cell.x == x && cell.y == y) //check if cell is in list
	            {
	            	
	                return true;
	            }
	        }
	        return false;
	}
	private static void GenerateMazeUtil(int x, int y) //generates binary maze using depth first search
	{
        int NextX;
        int NextY;
        
      //shuffle the directions into random order
      	//the shuffling prevents bias in a certain direction during maze generation
        Collections.shuffle(Arrays.asList(Directions));
        
        for (Coords direction : Directions) //loop through all the possible directions: N, E, S, W
        {
        	//calculate coordinates of next cell
            NextX = x + direction.x * 2;
            NextY = y + direction.y * 2;
            
            
            if (WithinBounds(NextX,NextY)) //check if next cell is within bounds of 2D array
            {
                
                // Check if the next cell has not been visited
                if (!contains(MazeGenerationRecord, NextX, NextY)) 
                {
                    
                	//record that cell has been visited
                	MazeGenerationRecord.add(new Coords (NextX, NextY));
                    
                	//Remove barrier between current cell and the next cell
                    map[NextX][NextY] = openPath;
                    map[x + direction.x][y + direction.y] = openPath;
                    
                    // call method recursively
                    GenerateMazeUtil(NextX, NextY);
                }
            }
        }
    }
	void GenerateUnrefinedMaze( int rows, int cols)
	{
		map = new char[rows][cols];
		ArrayList<Character> PossibleBlocks = new ArrayList<Character>();
		
		PossibleBlocks.add(openPath);
		PossibleBlocks.add(openPath);
		PossibleBlocks.add(openPath);
		PossibleBlocks.add(barrier);
		
        Random rand = new Random();
        

        // Initialize the maze with random blocks
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                if(i == 0 || j == 0 || i == rows - 1 || j == cols - 1)
                {
                	map[i][j]=barrier;//border
                }
                else 
                {
                    map[i][j] = PossibleBlocks.get(rand.nextInt(PossibleBlocks.size())); // Random block or empty space
                }
            }
        }

       GenerateStart();
        
        
	  

        
    
	}
	static void GenerateMaze(int rows, int columns)//Procedure Method: fills the maze array with barriers and occasional open path; this acts as set up for the recursive maze generation algorithm
	{
		int StartX = 0, StartY = 0;
		
		
		
		
		map = new char[rows][columns];
		
		//These arrays/arraylists are for the generation process
		//they will never be accessed outside this class
		MazeGenerationMap = new int[map.length][map[0].length];
		MazeGenerationRecord.clear();//reset visted record
		
		//Initialize array filled with barriers
		for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = barrier;
            }
        }
		
		
		//Generate coordinates for start position
		StartX = 1;
		StartY = 1;
			
		
	
		MazeGenerationRecord.add(new Coords(StartX,StartY));
		
		
		GenerateMazeUtil(StartX, StartY);
			
		GenerateStart();
			
			
		
			
	}
	
	static boolean StartCheck(int x, int y)
	{
		if(map[x][y]!=openPath) return false;
		if(map[x-1][y]==barrier && map[x+1][y]==barrier && map[x][y-1] == barrier && map[x][y+1]==barrier) return false;
		else return true;
	}
	static void GenerateStart()
	{
		int StartX;
		int StartY;
		
		do
		{
			StartX = random.nextInt(map.length);
			StartY = random.nextInt(map[0].length);
		}while(!StartCheck(StartX,StartY));
		
		map[StartX][StartY] = start;
	}
	static ArrayList<String> CheckSide()//function method: checks if sides are two blocks thick and returns valid exit sides
	//sometimes maze algorithmn generates a side that 2 blocks thick if the user  given dimensions are awkward
	//if a maze side is two blocks thick it cannot serve as an exit side
	{
		ArrayList<String> Sides = new ArrayList<String>(Arrays.asList("North","South","East","West"));//arraylist  of sides of the maze
		
		ArrayList<Character> EastSide = new ArrayList<Character>();
		ArrayList<Character> WestSide = new ArrayList<Character>();
		ArrayList<Character> NorthSide = new ArrayList<Character>();
		ArrayList<Character> SouthSide = new ArrayList<Character>();
		
		//block fills east and west side arraylists
		for(int x = 0; x < map.length; x++)
		{
			WestSide.add(map[x][1]);
			EastSide.add(map[x][map[0].length-2]);
		}
		
		for(int y = 0; y < map[0].length; y++)
		{
			NorthSide.add(map[1][y]);
			SouthSide.add(map[map.length-2][y]);
		}
		
		System.out.println(WestSide);
		System.out.println(EastSide);
		System.out.println(Arrays.toString(map[1]));
		System.out.println(Arrays.asList(map[1]).contains(openPath));
		if(!NorthSide.contains(openPath))//check if north side is double thick
		{
			Sides.remove("North");
		}
		if(!SouthSide.contains(openPath))//check if south side is double thick
		{
			Sides.remove("South");
		}
		
		if(!EastSide.contains(openPath))//check if east side is double thick
		{
			Sides.remove("East");
		}
		if(!WestSide.contains(openPath))//check if west side is double thick
		{
			Sides.remove("West");
		}
		
		return Sides;
		
		
	}
	static void GenerateExit()//Procedural method: Generates Exit
	{
		int ExitX = 0;
		int ExitY = 0;
		int Shift;
		String side;
		ArrayList<String> ExitSides = CheckSide();//arraylist of valid exit sides
		System.out.println(Arrays.toString(ExitSides.toArray()));
		side = ExitSides.get(random.nextInt(ExitSides.size()));//choose random valid exit side
		System.out.println(Arrays.toString(ExitSides.toArray()));
		
		if(side.equals("North")  || side.equals("South"))//if the chose exit side is north or south, run the following code
		{
			if(side.equals("North"))
			{
				ExitX = 0;//if exit side is north, the exit should be on the top row
				Shift = 1;
			}
			else
			{
				ExitX = map.length-1;//if exit side is south, the exit should be on the bottom row
				Shift = -1;
			}
			do
			{
				
				
				ExitY = random.nextInt(map[0].length);//choose random column for exit to be on
				System.out.println(Arrays.toString(ExitSides.toArray()));
				System.out.println("Side " + side);
				System.out.println(ExitX + " " + ExitY);
				if(map[ExitX+Shift][ExitY] == 'O')//check that exit is possible to access
					map[ExitX][ExitY] = exit;
				
			}while(map[ExitX][ExitY] != exit);//loop code until a possible exit is generated 
			
		}
		else//if the exit is not on the north or south side, it is on the west or east side
		//execute the block that follows
		{

			if(side.equals("East"))
			{
				ExitY = map[0].length-1;//if exit side is east, the exit should be on the right column
				Shift = -1;
			}
			else
			{
				ExitY = 0;//if exit side is west, the exit should be on the left column
				Shift = 1;
			}
			do
			{
				
				ExitX = random.nextInt(map.length);//choose random row for exit to be on
				System.out.println(ExitX + " " + ExitY);
				if(map[ExitX][ExitY+Shift] == 'O')//check that exit is possible to access
					map[ExitX][ExitY] = exit;
				
			}while(map[ExitX][ExitY] != exit);//loop code until a possible exit is generated 
			
		}
	}
	
	static void ReadFile(File file) throws Exception//procedure method:extracts maze array data from txt files
	{
		
		Scanner input = new Scanner(file);//new instance of scanner class that reads file
		int rows;
		int columns;
		
		
		//Input Section
		//Read amount of rows and columns from file
		//Identify the characters that represent the parts of the maze and store them in class variables
		rows = input.nextInt();
		columns = input.nextInt();
		barrier = input.next().charAt(0);
		openPath = input.next().charAt(0);
		start = input.next().charAt(0);
		exit = input.next().charAt(0);
		
		map = new char[rows][columns];//create amount new array with rows and columns as specified in the file
		
		
		for(int x =0; x< map.length;x++)//loop through the rows of the array
		{
			map[x] = input.next().toCharArray();//read entire row of maze, convert it into a 1D array of chars, and store it as a row in the map
			
		}
		input.close();//close the scanner instance
		
	}
	
	
	
	
	

}
class Mouse extends Maze
{
	
	
	ArrayList<Coords> MazeSolution = new ArrayList<Coords>();//stores directions out of the maze
	ArrayList<Coords> ShortestMazeSolution = new ArrayList<Coords>();
	private int ShortestPathLength;
	int[][]MazeSolutionMap;
	private int[][] MemoizationArray;
	private Coords Exit;
	
	public Mouse()//constructor method
	{
		
		MazeSolutionMap = new int[map.length][map[0].length];//make solution map with same dimensions as map
		
		//Initialize Solution Array
		//0's represent unused cell
		//1's represent visted cells
		for(int x = 0; x< MazeSolutionMap.length; x++)//loop through rows of solution array
		{
			for(int y =0; y < MazeSolutionMap[0].length;y++)//loop through rows of solution array
			{
				MazeSolutionMap[x][y] = 0;
				
			}
		}
	}
	
	
	void FindExit()//Procedure Method: Locates coordinates of exit
	{
		
		
		for(int x = 0; x < map.length; x++ )//loop through rows of array
		{
			for(int y = 0; y < map[0].length; y++)//loop through columns of array
			{
				if(map[x][y] == exit)//if start cell, execute block that follows
				{
					Exit = new Coords(x,y);//get coordinates of exit cell
					break;
				}

			}
		}
		
	}
	Coords FindStart()//Function Method: Locates and returns coordinates of starting cell
	{
		Coords Start = new Coords(-1,-1);
		
		for(int x = 0; x < map.length; x++ )//loop through rows of array
		{
			for(int y = 0; y < map[0].length; y++)//loop through columns of array
			{
				if(map[x][y] == this.start)//if start cell, execute block that follows
				{
					Start = new Coords(x,y);//get coordinates of start cell
					return Start;
				}

			}
		}
		
		return Start;
		
	}
	
	boolean CellCheck(int x, int y)//Function Method: Returns true if cell is unsafe for mouse to travel to
	{
		
		return(x < 0 || x >= map.length || y < 0 || y >= map[0].length || map[x][y] == barrier || MazeSolutionMap[x][y] ==1);//return true if cell is unsafe
	}
	void SolveMaze()//Procedure Method: sets up the initial arguement of the recrusive method
	{
		this.FindExit();
		ShortestPathLength = Integer.MAX_VALUE;
		MemoizationArray = new int[map.length][map[0].length];
		for(int x =0; x < MemoizationArray.length;x++)
		{
			for(int y =0; y < MemoizationArray[0].length;y++)
			{
				MemoizationArray[x][y]=0;
			}
		}
		if(Exit==null)
		{
			
		}
		else
		{
			SolveMazeUtil(this.FindStart().x,this.FindStart().y, 0);
		}
		//System.out.println(ShortestMazeSolution.size());
		//System.out.println(distance);
		
	}
	void SolveMazeUtil(int x, int y, int PathLength) //
	{
        if (CellCheck(x,y))
        {
            return;
        }

        MazeSolution.add(new Coords(x,y));
        MazeSolutionMap[x][y] = 1;

        if (x == Exit.x && y == Exit.y) 
        {
            if (ShortestMazeSolution.isEmpty() || PathLength < ShortestMazeSolution.size()) 
            {
            	ShortestMazeSolution.clear();
            	ShortestMazeSolution.addAll(MazeSolution);
                ShortestPathLength= ShortestMazeSolution.size();
            }
        } 
        else if(PathLength < ShortestPathLength)
        {
        	if(MemoizationArray[x][y]==0 || PathLength < MemoizationArray [x][y])
        	{
        		MemoizationArray[x][y] = PathLength;
	            SolveMazeUtil(x + 1, y, PathLength + 1);
	            SolveMazeUtil(x - 1, y, PathLength + 1);
	            SolveMazeUtil(x, y + 1,PathLength + 1);
	            SolveMazeUtil(x, y - 1, PathLength + 1);
        	}
        }

        MazeSolution.remove(MazeSolution.size() - 1);
        MazeSolutionMap[x][y] = 0;
    }
}

class Coords//coords class helps keep track of coordinate pairs
{
  int x;
  int y;
  
  
  public Coords(int x, int y)//constructor method
  {
    setCoords(x, y);
  } 
  
  public void setCoords(int x, int y)//procedure method: function sets the value of the x and y coordinate pair
  {
    this.x = x;
    this.y = y;
   
  }
}