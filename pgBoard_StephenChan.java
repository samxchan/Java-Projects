import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Stephen Chan
 * Period 7
 * 11/9/12
 * Patriot Games- we make a game that sees if blue wins, by checking to see if blue goes from one side to the other
 */
public class pgBoard_StephenChan {

	private char grid[][];
	private int length;

	//creates empty board
	public pgBoard_StephenChan(int l) {

		length=l;
		grid = new char[l][l];
	}

	//creates board, inputs all values from file
	public pgBoard_StephenChan(String name, int l) {

		length = l;
		grid = new char[length][length];
		Scanner file = null;

		try {

			file = new Scanner(new File(name));
		} catch (FileNotFoundException e) {

			System.out.println("File not found.");
		}

		// put the value into the grid
		for (int i = 0; i < length; i++) {

			String line = file.nextLine();

			for (int p = 0; p < line.length(); p++) {

				grid[i][p] = line.charAt(p);
			}

		}

	}

	// sets the spot in the matrix to blue
	public void setBlue(int row, int col) {

		if (!isInBounds(row, col))
			throw new IndexOutOfBoundsException();

		else
			grid[row][col] = 'b';
	}

	// sets the spot in the matrix to white
	public void setWhite(int row, int col) {

		if (!isInBounds(row, col))
			throw new IndexOutOfBoundsException();

		else
			grid[row][col] = 'w';
	}

	// checks the spot to see if it is blue
	public boolean isBlue(int row, int col) {

		if (!isInBounds(row, col) || grid[row][col] != 'b') {

			return false;
		}

		return true;
	}

	// checks the spot to see if it is white
	public boolean isWhite(int row, int col) {

		if (!isInBounds(row, col) || grid[row][col] != 'w') {

			return false;
		}

		return true;
	}

	// finds neighbors and changes all blue to white, then calls fillBlob using the new location. returns when all 6 possible places aren't valid
	public void fillBlob(int row, int col) {

		int x[] = { 0, -1, -1, 0, 1, 1 };
		int y[] = { -1, -1, 0, 1, 1, 0 };

		if (isBlue(row, col))
			setWhite(row, col);

		for (int i = 0; i < 6; i++) {

			int r = row + y[i];
			int c = col + x[i];

			if (isBlue(r, c)) {

				fillBlob(r, c);
			}
		}

		return;
	}

	// creates a small board (a part of the board) to return the blue blob at that location
	public pgBoard_StephenChan getBoard(int row, int col) {

		pgBoard_StephenChan toReturn = new pgBoard_StephenChan(length);
		
		fillBlob(row, col);

		// if the first piece is red, return empty
		if (!isBlue(row, col) && !isWhite(row, col))
			return toReturn;

		for (int i = 0; i < length; i++) {

			for (int k = 0; k < length; k++) {

				if (isWhite(i, k)) {
				
					setWhite(i, k);
					toReturn.setBlue(i, k);
				}
			}
		}

		return toReturn;
	}

	//checks the two ends to see if the blue is in column 0 and column length
	public boolean hasBlueWon(){
		
		pgBoard_StephenChan check;
		
		for(int i = 0; i < length; i++){
			
			check = getBoard(i, 0);
				
			if(check.isBlue(i, 0)){
				
				if(check.isBlue(i, length-1)){
					
					return true;
				}
			}
		}
	
		return false;
	}

	// returns the whole board
	public String toString() {

		String toReturn = "";

		for (int i = 0; i < grid.length; i++) {

			for (int k = 0; k < grid[i].length; k++) {

				toReturn += grid[i][k];
			}

			toReturn += "\n";
		}

		return toReturn;
	}

	// helper method to check if the location is in bounds
	private boolean isInBounds(int row, int col) {
	
		if (row >= length || col >= length || row < 0 || col < 0)
			return false;
	
		return true;
	}

	public static void main(String args[]) {

		pgBoard_StephenChan test = new pgBoard_StephenChan("pgBoard.txt", 5);
		System.out.println(test);
		System.out.println(test.hasBlueWon());
	}
}
