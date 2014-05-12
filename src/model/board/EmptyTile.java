package model.board;

import model.board.Tile.Color;

public class EmptyTile {
	
	private static final Tile instance = new Tile(Color.EMPTY);
	
	public static Tile getInstance() {
		return instance;
	}
	
}
