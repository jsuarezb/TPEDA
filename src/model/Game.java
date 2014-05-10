package model;
import java.awt.Point;

import model.board.Board;
import model.board.Tile;

public class Game {
	private Board board;
	private int player2Score;
	private int player1Score;
	
	public <T> Game(Class<T> boardClass) throws InstantiationException, IllegalAccessException {
		this.board = (Board)boardClass.newInstance();
	}

	public Tile getTile(Point p){
		return board.getTile(p);
	}
	
	public void delete(Point tilePos) {
		board.delete(Point tilePos);
	}
	
	public int getWidthSize(){
		return board.getWidthSize();
	}
	
	public int getHeightSize(){
		return board.getWidthSize();
	}
	
	public boolean isOver() {
		return board.gameOver();
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