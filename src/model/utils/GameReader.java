package model.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameReader {
	
	private int width;
	private int height;
	private int score1;
	private int score2;
	private char[][] board;
	
	/**
	 * Reads a file containing serialized game
	 * @param fileName path to the board file
	 */
	public GameReader(String fileName) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		try {
			width = Integer.valueOf(reader.readLine());
			height = Integer.valueOf(reader.readLine());
			score1 = Integer.valueOf(reader.readLine());
			score2 = Integer.valueOf(reader.readLine());
			board = readBoard(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private char[][] readBoard(BufferedReader reader) throws IOException {
		char[][] newBoard = new char[height][width];
				
		for (int y = 0; y < height; y++) {
			String line = reader.readLine();
			if (line == null)
				throw new MalformedBoardException("Missing rows");
			
			// String[] strRow = line.split(""); leaves an empty first element
			char[] strRow = line.toCharArray();
			if (strRow.length != width)
				throw new MalformedBoardException("Wrong row width");
			
			for (int x = 0; x < width; x++) {
				newBoard[y][x] = strRow[x];
			}
		}
	
		// There are more rows than indicated
		if (reader.readLine() != null) 
			throw new MalformedBoardException("Excessive rows");
		
		return newBoard;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	/**
	 * Get game scores
	 * @return a binary array with current player's score
	 * and next player's score
	 */
	public int[] getScores() {
		return new int[]{score1, score2};
	}
	
	public char[][] getBoard() {
		return board;
	}

	private static String print(char[][] board) {
		String str = "";

		for (char[] y : board) {
			for (char x : y)
				str += (x + " ");
			str += "\n";
		}
		
		return str;
	}
	
	public String toString() {
		String str = "";
		
		str += "\nWidth: " + width 
				+ ", Height: " + height
				+ ";\nScore 1: " + score1
				+ ", Score 2: " + score2
				+ ";\nBoard: \n" + print(board);
		
		return str;
	}

}
