package model.board;
import java.awt.Point;
import java.util.Arrays;

import model.board.Tile.Color;

public class Board {
	private Tile[][] board;
	private int lastCol;
	private int lastRow;
	private static final int[][] moves = new int[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

	public Board( Tile[][] board ) {
		this(board, 0, board[0].length - 1);
	}
	
	public Board(Tile[][] board, int lastRow, int lastCol){
		this.board = board;
		this.lastRow = lastRow;
		this.lastCol = lastCol;
	}

	public boolean isOver() {
		for( int y = board.length - 1; y >= lastRow; y-- )
			for( int x = 0; x <= lastCol; x++ )
				if( hasAdjacents(new Point(x,y)) )
					return false;
		return true;
	}

	private boolean hasAdjacents(Point pos) {
		Tile tile = getTile(pos);
		if( tile.isEmpty() )
			return false;
		if( tile.equals(getRight(pos)) )
			return true;
		if( tile.equals(getTop(pos)) )
			return true;
		return false;
	}

	public boolean isEmpty(){
		return lastCol == -1 && lastRow == board.length;
	}
	
	public Tile getTile(Point pos) {
		return getTile(pos.x, pos.y);
	}
	
	private Tile getTile(int x, int y) {
		if (y < 0 || y >= board.length || x < 0 || x >= board[0].length)
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
	
	public int play(Point pos){
		Board b = this.clone(); //TODO: Remove.
		
		int tilesDeleted = delete(pos);
		if( tilesDeleted == 0 )
			return 0;
		
		long iGTime = System.nanoTime(); //TODO: Remove timer. Keep control of it.
		gravity();		
		long fGTime = System.nanoTime();
		long gTime = fGTime - iGTime; 
		System.out.println("GravityTime: " + gTime); 
		
		if( gTime > 1000000 )
			System.out.println("Initial Board: \n" + b + "\n Final Board: \n" + this);
		
		alignLeft();
		return tilesDeleted;
	}
	
	public int delete(Point pos) {
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
		long iGTime = System.nanoTime();
		int auxRow = lastRow;
		lastRow = board.length;
		for( int x = 0; x <= lastCol; x++ ){
			int spaces = 0;
			for( int y = board.length - 1; y >= auxRow; y-- ){
				Point p = new Point(x,y);
				Tile tile = getTile(p);

				if( tile.isEmpty() )
					spaces++;
				else if( spaces > 0 ){
					drop(p, spaces);
				}
			}
			lastRow = Math.min(lastRow, spaces + auxRow);
		}
		long fGTime = System.nanoTime();
		long gTime = fGTime - iGTime; 
		System.out.println("InsideGravityTime " + gTime);
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
					for (int y = board.length - 1; y >= lastRow; y--) {
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
	
	public int getLastCol() {
		return lastCol;
	}
	
	public int getLastRow() {
		return lastRow;
	}
	
	public Board clone() {
		Tile[][] boardCopy = new Tile[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			boardCopy[i] = board[i].clone();
		}
		return new Board(boardCopy, lastRow, lastCol);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}
	
	public String toString() {
		String ans = "";
		for( int y = 0; y < board.length; y++ ){
			for( int x = 0; x < board[0].length; x++ )
				ans += board[y][x].getColor().getChar() + " ";
			ans += "\n";
		}
		return ans;
	}
}