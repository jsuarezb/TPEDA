package model;
import java.awt.Point;

import model.board.Board;
import model.board.Tile;
import model.board.Tile.Color;

public class Game {
	private Board board;
	private int player2Score;
	private int player1Score;

	public Game() {
		Tile[][] tiles = {{new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.GREEN), new Tile(Color.GREEN)},  
						  {new Tile(Color.GREEN), new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.GREEN)},
						  {new Tile(Color.RED), new Tile(Color.BLUE), new Tile(Color.GREEN), new Tile(Color.GREEN)},
						  {new Tile(Color.RED), new Tile(Color.BLUE), new Tile(Color.YELLOW), new Tile(Color.GREEN)},
						  {new Tile(Color.RED), new Tile(Color.BLUE), new Tile(Color.YELLOW), new Tile(Color.GREEN)},
						  {new Tile(Color.RED), new Tile(Color.BLUE), new Tile(Color.YELLOW), new Tile(Color.GREEN)}};
		board = new Board(tiles);
	}

	public Tile getTile(Point p){
		return board.getTile(p);
	}
	
	public void play(Point tilePos) {
		board.play(tilePos);
	}
	
	public int getWidthSize(){
		return board.getWidthSize();
	}
	
	public int getHeightSize(){
		return board.getHeightSize();
	}
	
	public boolean isOver() {
		return board.isOver();
	}
	
	public boolean playerWon() {
		return board.playerWon();
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}
}