// Board.java
package edu.stanford.cs108.tetris;

import java.util.Arrays;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	public boolean[][] grid;
	public boolean[][] backupGrid;
	private boolean DEBUG = true;
	boolean committed;
	public int[] widths;
	public int[] backupWidths;
	public int[] backupHeights;
	public int[] heights;
	
	// Here a few trivial methods are provided:
	
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		backupGrid = new boolean[width][height];
		for (int r = 0; r < grid.length; r++) {
			Arrays.fill(grid[r], false);
		}
		committed = true;
		widths = new int[height];
		backupWidths = new int[height];
		Arrays.fill(widths, 0);
		heights = new int[width];
		backupHeights = new int[width];
		Arrays.fill(heights, 0);
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {	 
		int max = 0;
		for (int x : heights) {
			if (x > max) max = x;
		} 
		return max; 
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		boolean sane = true;
		if (DEBUG) {
			int maxHeight = 0;
			//checks accuracy of heights
			for (int x = 0; x < width; x++) {
				if (sane) {
					int storedValue = heights[x];
					int actualValue = 0;
					for (int y = 0; y < height; y++) {
						if (grid[x][y] && y >= actualValue) {
							actualValue = y + 1;
						}
					}
					if (actualValue > maxHeight) maxHeight = actualValue;
					if (actualValue != storedValue) sane = false;
				}
				else throw new RuntimeException("description");
			}
			//checks accuracy of getMaxHeight() before checking accuracy of widths
			if (sane && getMaxHeight() == maxHeight) {
				for (int y = 0; y < height; y++) {
					if (sane) {
						int storedValue = widths[y];
						int actualValue = 0;
						for (int x = 0; x < width; x++) {
							if (grid[x][y]) actualValue++;
						}
						if (actualValue != storedValue) sane = false;
					}
					else throw new RuntimeException("description");
				}
			}
		}
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		int[] skirt = piece.getSkirt();
		
		int root = skirt[0];
		
		int min = heights[x];
		
		
		for (int curr = 1; curr < piece.getWidth(); curr++) {
			int skirtDiff = root - skirt[curr];
			int heightsDiff = min - heights[x+curr];
			if (skirtDiff > heightsDiff) {
				min = heights[x+curr] + skirtDiff;
			}
		}
		
		if (root > 0) min = min - root;
		return min;
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		if (x < width) {
			return heights[x];
		}
		return 0; 
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		if (y < height) {
			return widths[y];
		}
		return 0; 
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		if ((x < width) && (y < height)) {
			return grid[x][y];
		}
		else return true;
	}
	
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
		TPoint[] points = piece.getBody();
//		System.out.println("Points:");
//		for (TPoint currPoint : points) {
//			System.out.println(currPoint.x + ", " + currPoint.y);
//		}
		piece.getBody();
		int result = PLACE_OK;
		//System.out.println("y " + y);
		
		committed = false;
//		System.out.println("width " + grid.length);
//		System.out.println("height " + grid[0].length);
		for (TPoint currPoint : points) {
			if (result == PLACE_OK || result == PLACE_ROW_FILLED) {
				//checks that coordinate is not out of bounds
				int xIndex = currPoint.x + x;
				int yIndex = currPoint.y + y;
//				System.out.println("xIndex " + xIndex);
//				System.out.println("x " + x);
//				System.out.println("currPoint.x " + currPoint.x);
//				System.out.println("yIndex " + yIndex);
//				System.out.println("y " + y);
//				System.out.println("currPoint.y " + currPoint.y);
				if ((xIndex < grid.length && xIndex >= 0) && (yIndex < grid[0].length && yIndex >= 0)) {
					//checks that coordinate is not already filled
					if (grid[currPoint.x + x][currPoint.y + y]) {
						result = PLACE_BAD;
					}
					else {
						grid[currPoint.x + x][currPoint.y + y] = true;
						widths[currPoint.y + y] += 1; 
						if (heights[currPoint.x + x] < currPoint.y + y + 1) heights[currPoint.x + x] = currPoint.y + y + 1;
						if (widths[currPoint.y + y] == width) result = PLACE_ROW_FILLED;
					}
				}
				else result = PLACE_OUT_BOUNDS;
			} else return result;
			System.out.println();
		}
		sanityCheck();
		return result;
	}
	
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
		int rowsCleared = 0;
		int maxHeight = getMaxHeight();
		for (int y = 0; y < maxHeight - rowsCleared; y++) {
			//checks if the row is filled
			if (widths[y] == width) {
				//sets every row equal to the row above it
				for (int yin = y; yin < maxHeight - rowsCleared; yin++) {
					//sets every piece equal to the piece above it
					for (int xin = 0; xin < width; xin++) {
						if (yin == height - 1) {
							grid[xin][yin] = false;
						} else {
							grid[xin][yin] = grid[xin][yin+1];
						}
					}
					//sets the width of every row equal to the width of the row above it
					if (yin == height - 1) {
						widths[yin] = 0;
					} else {
						widths[yin] = widths[yin+1];
					}
				}
				y--;
				rowsCleared++;
			}
		}
		//decreases the height of each column based on the number of rows that were cleared
		for (int xin = 0; xin < width; xin++) {
			int yin = maxHeight - rowsCleared - 1;
			//counts down from top of grid to find first filled piece in column
			while (yin >= 0 && !grid[xin][yin]) {
				yin--;
			}
			if (yin >= 0) heights[xin] = yin + 1;
			else heights[xin] = 0;
		}
		committed = false;
		sanityCheck();
		return rowsCleared;
	}



	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		if (!committed) {
			for (int r = 0; r < width; r++) {
				System.arraycopy(backupGrid[r], 0, grid[r], 0, grid[0].length);
			}
			System.arraycopy(backupWidths, 0, widths, 0, widths.length);
			System.arraycopy(backupHeights, 0, heights, 0, heights.length);
			committed = true;
		}
	}
	
	
	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		if (!committed) {
			for (int r = 0; r < width; r++) {
				System.arraycopy(grid[r], 0, backupGrid[r], 0, grid[0].length);
			}
			System.arraycopy(widths, 0, backupWidths, 0, widths.length);
			System.arraycopy(heights, 0, backupHeights, 0, heights.length);
			committed = true;
		}
	}


	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString2() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
}


