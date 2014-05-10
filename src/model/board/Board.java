package model.board;
import java.awt.Point;

import model.board.Tile.Color;

public class Board {
	private Tile[][] board;
	private int lastCol;
	private static final int[][] moves = new int[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

	public Board( Tile[][] board ) {
		this.board = board;
		this.lastCol = board[0].length - 1;
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

	public Tile getTile(Point pos) {
		return getTile(pos.x, pos.y);
	}
	
	private Tile getTile(int x, int y) {
		if (y < 0 || y >= board.length
				|| x < 0 || x >= board[0].length)
			return null;

		return board[y][x];
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

	private Tile getLeft(Point pos) {
		if( pos.x - 1 < 0 )
			return null;
		return board[pos.y][pos.x - 1];
	}
	
	public void play(Point pos){
		if( getTile(pos).isEmpty() )
				return;
		delete(pos);
		gravity();
		alignLeft();
	}
	
	private int delete(Point pos) {
		boolean hasAdj = false;
		Tile tile = getTile(pos);

		for (int i = 0; i < moves.length && !hasAdj; i++) {
			Point point = new Point(pos.x + moves[i][0], pos.y + moves[i][1]);
			if (tile.equals(getTile(point)))
				hasAdj = true;
		}

		if (!hasAdj)
			return 0;

		return deleteTile(pos);
	}

	private int deleteTile(Point pos) {
		int tilesDeleted = 0;
		Point point;
		Tile tile = getTile(pos), adjTile;
		board[pos.y][pos.x] = new Tile(Color.EMPTY);
		
		for (int i = 0; i < moves.length; i++) {
			point = new Point(pos.x + moves[i][0], pos.y + moves[i][1]);
			adjTile = getTile(point);

			if (tile.equals(adjTile))
				tilesDeleted += deleteTile(point);
		}
		
		return tilesDeleted + 1;
	}
	
	private void gravity() {
		for( int x = 0; x <= lastCol; x++ ){
			int spaces = 0;

			for( int y = board.length - 1; y >= 0; y-- ){
				Point pos = new Point(x,y);
				Tile tile = getTile(pos);

				if( tile.isEmpty() )
					spaces++;
				else if( spaces > 0 )
					drop(pos, spaces);
			}
		}
	}

	private void drop(Point pos, int spaces) {
		board[pos.y + spaces][pos.x] = getTile(pos);
		board[pos.y][pos.x] = new Tile(Color.EMPTY);
	}

	private void alignLeft() {
		int auxCol = lastCol;
		lastCol = -1;
		
		for( int x = 0; x <= auxCol; x++ ) {
			Point tilePos = new Point(x, board.length - 1);
			Tile bottomTile = getTile(tilePos);
			
			if (!bottomTile.isEmpty()){	
				Tile leftTile = getLeft(tilePos);
				
				if (leftTile != null && leftTile.isEmpty())
					for (int y = board.length - 1; y >= 0; y--) {
						board[y][lastCol + 1] = board[y][x];
						board[y][x] = new Tile(Color.EMPTY);
					}
				lastCol++;
			}
		}
	}

	public int getWidthSize() {
		return board[0].length;
	}

	public int getHeightSize() {
		return board.length;
	}

	public boolean playerWon() {
		// TODO Auto-generated method stub
		return false;
	}
}