package model;
import java.awt.Point;

import model.board.Board;
import model.board.Tile;
import model.board.Tile.Color;

public class Game {
	private Board board;
	private int player2Score;
	private int player1Score;
	private int turn;

	public Game() {
		Tile[][] tiles = {{new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.GREEN), new Tile(Color.GREEN)},  
						  {new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.GREEN)},
						  {new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.GREEN), new Tile(Color.GREEN)},
						  {new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.YELLOW), new Tile(Color.GREEN)},
						  {new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.YELLOW), new Tile(Color.GREEN)},
						  {new Tile(Color.BLUE), new Tile(Color.BLUE), new Tile(Color.YELLOW), new Tile(Color.GREEN)}};
		board = new Board(tiles);
	}

	public Board getBoard() {
		return board;
	}
	
	public Tile getTile(Point p){
		return board.getTile(p);
	}
	
	public void play(Point tilePos) {
		if( getTile(tilePos).isEmpty() )
			return;
		int tilesDeleted = board.play(tilePos);
		score(tilesDeleted);
		turn++;
	}
	
	private void score(int tiles){
		int score;
		if( tiles <= 5 )
			score = (int)Math.round(Math.pow(2, tiles - 2));
		else
			score = tiles*2;
		if( turn % 2 == 0 ){
			player1Score += score;
			if( board.isEmpty() )
				player1Score *= 1.3;
		}else{
			player2Score += score;
			if( board.isEmpty() )
				player2Score *= 1.3;
		}
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
		return player1Score > player2Score;
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}
}