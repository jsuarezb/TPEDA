package model.board;
import java.awt.Point;

public class Board {
	private Tile[][] board;
	private int lastCol;
	
	public Board( Tile[][] board ) {
		this.board = board;
	}
	
	public boolean isOver() {
		for( int y = board.length - 1; y >= 0; y-- )
			for( int x = 0; x <= lastCol; x++ )
				if( hasAdyacents(new Point(x,y)) )
					return false;
		return true;
	}
	
	private boolean hasAdyacents(Point pos) {
		Tile tile = getTile(pos);
		
		if( tile.equals(getRight(pos)) )
			return true;
		if( tile.equals(getTop(pos)) )
			return true;
		return false;
	}
	
	private Tile getTile(Point pos) {
		return board[pos.y][pos.x];
	}
	
	private Tile getRight(Point pos) {
		if( pos.x + 1 >= board[0].length )
			return null;
		return board[pos.y][pos.x + 1];
	}
	
	private Tile getTop(Point pos) {
		if( pos.y - 1 < 0 )
			return null;
		return board[pos.y - 1][pos.x];
	}
	
	private void gravity() {
		for( int x = 0; x <= lastCol; x++ ){
			int spaces = 0;
			
			for( int y = board.length - 1; y >= 0; y-- ){
				Point pos = new Point(x,y);
				Tile tile = getTile(pos);
				
				if( tile.isEmpty() )
					spaces++;
				else
					drop(pos, spaces);
			}
		}
	}
	
	private void drop(Point pos, int spaces) {
		board[pos.y + spaces][pos.x] = getTile(pos);
		board[pos.y][pos.x] = new Tile(' ');
	}
	
	private void alignLeft() {
		for( int x = 0; x <= lastCol; x++ )
			
	}
}
