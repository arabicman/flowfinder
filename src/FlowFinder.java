import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class FlowFinder
{
	public static void main (String[] args)
	{
		File file;
		ArrayList<String> fileContents = new ArrayList<>();
		Scanner scanThatFile;
		Gameboard stupidSolution,  startingBoard = null;
		
		//let's find a file to load!
		if (args.length < 1)
		{
			System.out.println("Type the file name to load:");
			Scanner in = new Scanner(System.in);
			file = new File(in.nextLine());
			in.close();
		}
		else
			file = new File(args[0]);
		
		//now try to actually load it
		try
		{
			scanThatFile = new Scanner(file);
			
			while (scanThatFile.hasNextLine())
				fileContents.add(scanThatFile.nextLine());
			scanThatFile.close();
		}
		catch (FileNotFoundException eh)
		{
			System.out.println("That file could not be found!");
			exit(1);
		}
		
		try
		{
			//making the board will validate that every color in the input file is used exactly twice
			startingBoard = new Gameboard(fileContents);

		}
		catch (Exception badInput)
		{
			System.out.println(badInput);
			exit(1);
		}
		System.out.println("Gameboard loaded!");
		for (int a = 0; a < startingBoard.getHeight(); a++)
		{
			for (int b = 0; b < startingBoard.getWidth(); b++)
			{
				System.out.print(startingBoard.board[a][b].getColor());
			}
			System.out.println();
		}
		
		StupidSearch dfs = new StupidSearch(startingBoard);
		long startTime = System.currentTimeMillis();
		stupidSolution = dfs.solve();
		long stopTime = System.currentTimeMillis();
		System.out.println("Finished BT_Search in " + (stopTime-startTime) + "ms!");
//		System.out.println("It took " + dfs.getAttemptedSolutions() + " tries to find the solution.");
//		System.out.println(dfs.getAssignmentsMade() + " color assignments were made to get here.");
//		System.out.println("We detected " + dfs.getDeadEnds() + " dead end branches along the way.");

		for (int a = 0; a < stupidSolution.getHeight(); a++)
		{
			for (int b = 0; b < stupidSolution.getWidth(); b++)
			{
				System.out.print(stupidSolution.board[a][b].getColor());
			}
			System.out.println();
		}
		

	}
}
