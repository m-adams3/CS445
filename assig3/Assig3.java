// Michael Adams
// TuTh 1pm

import java.io.*;
import java.util.*;

public class Assig3
{
	// DECLARE RELEVANT VARS
	public boolean answer;
	public int rCounter = 0;
	public int dCounter = 0;
	public int lCounter = 0;
	public int uCounter = 0;
	ArrayList<Integer> rowVals = new ArrayList<Integer>();
	ArrayList<Integer> colVals  = new ArrayList<Integer>();
	ArrayList<Integer> rowStart = new ArrayList<Integer>();
	ArrayList<Integer> colStart = new ArrayList<Integer>();
	ArrayList<Integer> rowEnd = new ArrayList<Integer>();
	ArrayList<Integer> colEnd = new ArrayList<Integer>();
	
	// MAIN METHOD
	public static void main(String [] args)
	{
		new Assig3();
	}
	
	// Constructor to set things up and make the initial search call - taken from FindWord
	public Assig3()
	{
        Scanner inScan = new Scanner(System.in);
		Scanner fReader;
		File fName;
        String fString = "", word = "";
		
		// Make sure the file name is valid - taken from FindWord
        while (true)
        {
           try
           {
			   System.out.println("Please enter grid filename:");
               fString = inScan.nextLine();
               fName = new File(fString);
               fReader = new Scanner(fName);
              
               break;
           }
           catch (IOException e)
           {
               System.out.println("Problem " + e);
           }
        }
		
		// Parse input file to create 2-d grid of characters - taken from FindWord
		String [] dims = (fReader.nextLine()).split(" ");
		int rows = Integer.parseInt(dims[0]);
		int cols = Integer.parseInt(dims[1]);
		
		char [][] theBoard = new char[rows][cols];

		for (int i = 0; i < rows; i++)
		{
			String rowString = fReader.nextLine();
			for (int j = 0; j < rowString.length(); j++)
			{
				theBoard[i][j] = Character.toLowerCase(rowString.charAt(j));
			}
		}

		// Show user the grid - taken from FindWord
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				System.out.print(theBoard[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		// Get word to search for - taken from FindWord
		System.out.println("Please enter a word or phrase:");
        word = (inScan.nextLine()).toLowerCase();
		
		// Search for word if a valid word is entered - adapted from FindWord
		while (!(word.equals("")))
		{
			// find number of words in user entered string and display info to user
			String[] phrase = word.split("\\s+");
			System.out.println("Looking for: "+ word);
			int numSpc = 0;
			for (int i = 0; i < word.length(); i++)
			{
				if (word.charAt(i) == ' ')
					numSpc++;
			}
			int wordNum = numSpc + 1;
			System.out.println("Containg "+ wordNum +" words");
			// Search for the word.  Note the nested for loops here.  This allows us to
			// start the search at any of the locations in the board.  The search itself
			// is recursive (see findWord method for details).  Note also the boolean
			// which allows us to exit the loop before all of the positions have been
			// tried -- as soon as one solution has been found we can stop looking.
			boolean found = false;
			for (int r = 0; (r < rows && !found); r++)
			{
				for (int c = 0; (c < cols && !found); c++)
				{
					found = findWord(r, c, word, 0, theBoard, rCounter, dCounter, lCounter, uCounter);
				}
			}
			
			if (found)
			{
				// keep track of coordinates
				rowStart.add(rowVals.get(0));
				colStart.add(colVals.get(0));
				
				for (int i = 0; i < rowVals.size() - 1; i++)
				{
					if (rowVals.get(i) == 100)
					{						
						rowStart.add(rowVals.get(i+1));
						rowEnd.add(rowVals.get(i-1));		
					}
					if(colVals.get(i) == 100)
					{
						colStart.add(colVals.get(i+1));
						colEnd.add(colVals.get(i-1));
					}					
				}
				
				rowEnd.add(rowVals.get(rowVals.size()-1));
				colEnd.add(colVals.get(colVals.size()-1));
				
				// display output to user
				System.out.println("The phrase: " + word);
				System.out.println("was found:");
				for (int i = 0; i < phrase.length; i++)
					System.out.println(phrase[i] + ": " + "(" + rowStart.get(i) + "," + colStart.get(i) + ") to (" + rowEnd.get(i) + "," + colEnd.get(i) + ")");
				
				for (int i = 0; i < rows; i++)
				{
					for (int j = 0; j < cols; j++)
					{
						System.out.print(theBoard[i][j] + " ");
						theBoard[i][j] = Character.toLowerCase(theBoard[i][j]);
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println("The word: " + word);
				System.out.println("was not found");
			}
			
			// allow user to enter another word or quit
			System.out.println();
			System.out.println("Please enter a word or phrase:");
        	word = (inScan.nextLine()).toLowerCase();
			rowVals.clear();
			colVals.clear();
			rowStart.clear();
			rowEnd.clear();
			colStart.clear();
			colEnd.clear();
		}
	}
	
	// Recursive method to search for the word.  Return true if found and false
	// otherwise.
	public boolean findWord(int r, int c, String word, int loc, char [][] bo, int rCounter, int dCounter, int lCounter, int uCounter)
	{
		// declare var for number of words
		int numWord = 0;
		
		// Check boundary conditions
		if (r >= bo.length || r < 0 || c >= bo[0].length || c < 0)
		{
			return false;
		}
		// Check if char does not match
		else if (bo[r][c] != word.charAt(loc))
		{			
			for (int i = 0; i < numWord; i++)
			{
				rowVals.remove(rowVals.size() - (i+1));
				colVals.remove(colVals.size() - (i+1));
			}
			return false;
		}
		// Check if space is next char
		else if ((loc+1) < word.length() && word.charAt(loc+1) == ' ')
		{
			// uppercase char if match, reset counters, keep track of coordinates
			bo[r][c] = Character.toUpperCase(bo[r][c]);
			rCounter = 0;
			dCounter = 0;
			lCounter = 0;
			uCounter = 0;
			numWord++;
			rowVals.add(r);
			colVals.add(c);
			rowVals.add(100);
			colVals.add(100);
			
			// make recursive call to begin searching for next word in phrase
			answer = false;
			if (!answer)
				answer = findWord(r, c+1, word, loc+2, bo, rCounter+1, dCounter, lCounter, uCounter);  // Right
			if (!answer)
				answer = findWord(r+1, c, word, loc+2, bo, rCounter, dCounter+1, lCounter, uCounter);  // Down
			if (!answer)
				answer = findWord(r, c-1, word, loc+2, bo, rCounter, dCounter, lCounter+1, uCounter);  // Left
			if (!answer)
				answer = findWord(r-1, c, word, loc+2, bo, rCounter, dCounter, lCounter, uCounter+1);  // Up
			if (!answer)
			{
				bo[r][c] = Character.toLowerCase(bo[r][c]);
				rowVals.remove(rowVals.size()-1);
				colVals.remove(colVals.size()-1);
				numWord = 0;
				rowVals.remove(rowVals.size()-1);
				colVals.remove(colVals.size()-1);
			}
			return answer;
		}
		// current char matches
		else
		{
			bo[r][c] = Character.toUpperCase(bo[r][c]);  // Change it to
				// upper case.  This serves two purposes:
				// 1) It will no longer match a lower case char, so it will
				//    prevent the same letter from being used twice
				// 2) It will show the word on the board when displayed
			rowVals.add(r);
			colVals.add(c);
			numWord++;
			
			if (loc == word.length()-1)		// base case - word found and we are done
				answer = true;				
			else	// Still have more letters to match, so recurse.
			{		// Try all four directions if necessary (but only if necessary)
				answer = false;
				if (!answer && dCounter < 1 && lCounter < 1 && uCounter < 1)
					answer = findWord(r, c+1, word, loc+1, bo, rCounter+1, dCounter, lCounter, uCounter);  // Right
				if (!answer && rCounter < 1 && lCounter < 1 && uCounter < 1)
					answer = findWord(r+1, c, word, loc+1, bo, rCounter, dCounter+1, lCounter, uCounter);  // Down
				if (!answer && rCounter < 1 && dCounter < 1 && uCounter < 1)
					answer = findWord(r, c-1, word, loc+1, bo, rCounter, dCounter, lCounter+1, uCounter);  // Left
				if (!answer && rCounter < 1 && dCounter < 1 && lCounter < 1)
					answer = findWord(r-1, c, word, loc+1, bo, rCounter, dCounter, lCounter, uCounter+1);  // Up
		
				// If answer was not found, backtrack.  Note that in order to
				// backtrack for this algorithm, we need to move back in the
				// board (r and c) and in the word index (loc) -- these are both 
				// handled via the activation records, since after the current AR 
				// is popped, we revert to the previous values of these variables.
				// However, we also need to explicitly change the character back
				// to lower case before backtracking.
				if (!answer)
				{
					bo[r][c] = Character.toLowerCase(bo[r][c]);
					if (rowVals.size()>0)
					{
						rowVals.remove(rowVals.size()-1);
						colVals.remove(colVals.size()-1);
						numWord = 0;
					} 
				}
			}
			return answer;
		}
	}			
}