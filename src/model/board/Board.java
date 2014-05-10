package model.board;
import java.awt.Point;

import model.board.Tile.Color;

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

	public Tile getTile(Point pos) {
		if (pos.y < 0 || pos.y >= board.length
				|| pos.x < 0 || pos.x >= board[0].length)
			return null;

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
		board[pos.y][pos.x] = new Tile(Color.EMPTY);
	}

	private void alignLeft() {
		int lastCol = -1;
		for( int x = 0; x <= lastCol; x++ ) {
			Tile bottomTile = board[board.length - 1][x];
			if (!bottomTile.isEmpty()){				
				if (x - 1 >= 0 && board[board.length - 1][x - 1].isEmpty()) {
					for (int y = board.length - 1; y >= 0; y--) {
						board[y][lastCol + 1] = board[y][x];
						board[y][x] = new Tile(Color.EMPTY);
					}
				}

				lastCol++;
			}
		}
	}

	public int delete(Point pos) {
		boolean hasAdj = false;
		int[][] moves = new int[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
		Point point;
		Tile tile = getTile(pos);

		if (tile == null || tile.isEmpty())
			throw new IllegalArgumentException("Invalid tile");

		for (int i = 0; i < moves.length && !hasAdj; i++) {
			point = new Point(pos.x + moves[i][0], pos.y + moves[i][1]);
			if (tile.equals(getTile(point)))
				hasAdj = true;

		}

		if (!hasAdj)
			return 0;

		return deleteTile(pos);
	}

	private int deleteTile(Point pos) {
		int tilesModified = 0;
		int[][] moves = new int[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
		Point point;
		Tile tile = getTile(pos), adjTile;
		board[pos.y][pos.x] = new Tile(Color.EMPTY);
		
		for (int i = 0; i < moves.length; i++) {
			point = new Point(pos.x + moves[i][0], pos.y + moves[i][1]);
			adjTile = getTile(point);

			if (adjTile != null && tile.equals(adjTile))
				tilesModified += delete(point);
		}
		System.out.println("hola :)"); //TODO: Borrar!
		return tilesModified + 1;
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