package model.utils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import model.Game;
import model.board.Board;
import model.board.Board.Tile;

public class GameFactory {

	private static Map<Character, Tile> colors = new HashMap<Character, Tile>();
	private String filename;
	
	public GameFactory() {
		this((new FileFactory()).getRandomFile());
	}
	
	public GameFactory(String filename) {
		initializeColors();
		this.filename = filename;
	}
	
	private static void initializeColors() {
		colors.put(' ', Tile.EMPTY);
		colors.put('1', Tile.RED);
		colors.put('2', Tile.BLUE);
		colors.put('3', Tile.GREEN);
		colors.put('4', Tile.YELLOW);
		colors.put('5', Tile.VIOLET);
		colors.put('6', Tile.PINK);
		colors.put('7', Tile.CYAN);
		colors.put('8', Tile.LIME);
		colors.put('9', Tile.ORANGE);
	}
	
	public Game getGame() throws FileNotFoundException {
		GameReader gameReader = new GameReader(filename);
		Board board = makeBoard(gameReader);
		return new Game(board, gameReader.getP1Score(), gameReader.getP2Score());
	}
	
	private Board makeBoard(GameReader gameReader) {
		char[][] charBoard = gameReader.getBoard();
		int height = gameReader.getHeight();
		int width = gameReader.getWidth();
		Tile[][] board = new Tile[height][width];
		
		for( int i = 0; i < height; i++ )
			for( int j = 0; j < width; j++ )
				board[i][j] = colors.get(charBoard[i][j]);
		
		return new Board(board);
	}
}
