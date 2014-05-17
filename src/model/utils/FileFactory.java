package model.utils;

import java.io.PrintWriter;

import model.board.Board.Tile;

public class FileFactory {

	private static final Tile[] COLORS = {Tile.RED, Tile.BLUE, Tile.GREEN, Tile.YELLOW, Tile.VIOLET,
		 Tile.PINK, Tile.CYAN, Tile.LIME, Tile.ORANGE}; 
	private static final int MINROWS = 5;
	private static final int MAXROWS = 15;
	private static final int MINCOLS = 3;
	private static final int MAXCOLS = 25;
	private static final int MINCOLORS = 3;
	private static final int MAXCOLORS = 9;
	private static final String FILENAME = "randomGame.txt";
	
	private int rows;
	private int cols;
	private int colorsAmount;
	private int P1Score;
	private int P2Score;
	
	public FileFactory(int rows, int cols, int colorsAmount, int P1Score, int P2Score ) {
		this.rows = rows;
		this.cols = cols;
		this.colorsAmount = colorsAmount;
		this.P1Score = P1Score;
		this.P2Score = P2Score;
	}
	
	public FileFactory() {
		this(randomRows(), randomCols(), randomColorsAmount(), 0, 0 );
	}
	
	private static int randomRows() {
		return (int)((MAXROWS - MINROWS + 1)*Math.random()) + MINROWS;
	}
	
	private static int randomCols() {
		return (int)((MAXCOLS - MINCOLS + 1)*Math.random()) + MINCOLS;
	}
	
	private static int randomColorsAmount() {
		return (int)((MAXCOLORS - MINCOLORS + 1)*Math.random()) + MINCOLORS;
	}
	
	private void writeRandomBoard(int rows, int cols, int colorsAmount, PrintWriter writer) {
		for( int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++)
				writer.print(COLORS[(int)(Math.random()*colorsAmount)].getChar());
			if( i < rows - 1)
				writer.println();
		}
	}
	
	public String getRandomFile() {
			try {
			PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
			
			writer.println(rows);
			writer.println(cols);
			writer.println(P1Score);
			writer.println(P2Score);
			writeRandomBoard(rows, cols, colorsAmount, writer);
			writer.close();
			
			return FILENAME;
			
		} catch (Exception e) {
			throw new IllegalMakeFileException();
		}
	}
}
