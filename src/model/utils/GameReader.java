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
	
	public GameReader(String fileName) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		try {
			height = Integer.valueOf(reader.readLine());
			width = Integer.valueOf(reader.readLine());
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
				throw new InvalidBoardException("Less rows than expected.");
			
			// String[] strRow = line.split(""); leaves an empty first element
			char[] strRow = line.toCharArray();
			if (strRow.length != width)
				throw new InvalidBoardException("Invalid amount of columns.");
			
			for (int x = 0; x < width; x++) {
				if (isValidCharacter(strRow[x]))
					throw new InvalidBoardException("Invalid character");
				
				newBoard[y][x] = strRow[x];
			}
		}
	
		// There are more rows than indicated
		if (reader.readLine() != null) 
			throw new InvalidBoardException("More rows than expected.");
		
		return newBoard;
	}
	
	private boolean isValidCharacter(char c) {
		return Character.isDigit(c) || c == ' ';
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getP1Score() {
		return score1;
	}
	
	public int getP2Score() {
		return score2;
	}
	
	public char[][] getBoard() {
		return board;
	}
}
