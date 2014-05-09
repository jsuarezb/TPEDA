package model;
import java.awt.Point;
import model.board.Board;

public class Game {
	private Board board;
	
	public <T> Game(Class<T> boardClass) throws InstantiationException, IllegalAccessException {
		this.board = (Board)boardClass.newInstance();
	}

	public boolean isOver() {
		return board.gameOver();
	}
	
	public boolean playerWon() {
		return board.playerWon();
	}
}