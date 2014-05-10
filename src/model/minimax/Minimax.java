package model.minimax;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import model.Game;
import model.board.Board;

public class Minimax {

	private Game game;
	
	public Minimax(Game game){
		this.game = game;
	}
	
	public Point minimax(){
		Board board = game.getBoard();
		
		return null;
	}
	
	public int minimax(Board board, Point tilePos){
		Set<Board> boards = new HashSet<Board>();
		for(int x = 0; x < board.getLastCol(); x++)
			for(int y = board.getLastRow(); y >= 0; y--){
				Board boardCopy = board.clone();
				board.play(new Point(x,y));
				boards.add(board);
			}
		Board boardCopy = board.clone();
		return 0;
	}
}
