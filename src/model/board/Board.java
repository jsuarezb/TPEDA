package model.board;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board { // TODO: Todo's.
	
	private static final int[][] moves = new int[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	private Tile[][] board;
	private int lastCol;
	private int lastRow;
	
	public static enum Tile {
		RED('1'), BLUE('2'), GREEN('3'), YELLOW('4'), VIOLET('5'),
		PINK('6'), CYAN('7'), LIME('8'), ORANGE('9'), EMPTY(' '); 

		private final char c;

		private Tile(char c) {
			this.c = c; 
		}

		public char getChar() {
			return c;
		}
		
		public boolean isEmpty() {
			return this == EMPTY;
		}
	}

	
	public Board( Tile[][] board ) {
		this(board, 0, board[0].length - 1);
	}
	
	private Board(Tile[][] board, int lastRow, int lastCol) {
		this.board = board;
		this.lastRow = lastRow;
		this.lastCol = lastCol;
	}
	
	public List<Group> getGroups() {
		List<Group> groups = new ArrayList<Group>();
		Set<Point> pointSet = new HashSet<Point>();
		
		for( int y = height() - 1; y >= lastRow; y-- )
			for( int x = 0; x <= lastCol; x++ )		
				if( !pointSet.contains(new Point(x,y)) )
					if( hasInOrderAdjacents(x, y) ) {
						Group group = new Group(x, y);
						pointSet.addAll(group.points);
						groups.add(group);
					}
		
		return groups;
	}
	
	public int play(Group group){
		int tilesDeleted = delete(group);
		gravity(group.cols);
		alignLeft();
		
		return tilesDeleted;
	}
	
	private int delete(Group group) {
		for(Point p: group.points)
			board[p.y][p.x] = Tile.EMPTY;
		
		return group.points.size();
	}
	
	private void gravity(Set<Integer> cols) {
		for(Integer col: cols)
			gravity(col);
		updateLastRow();
	}
	
	private void gravity(int col) {
		int spaces = 0;
		
		for( int y = height() - 1; y >= lastRow; y-- ) {
			Tile tile = getTile(col, y);

			if( tile.isEmpty() )
				spaces++;
			else if( spaces > 0 )
				drop(col, y, spaces);
		}
	}
	
	private void updateLastRow(){
		for( int y = lastRow; y < height(); y++ )
			for( int x = 0; x <= lastCol; x++ )
				if( !getTile(x, y).isEmpty() ) {
					lastRow = y;
					return;
				}
	}
	
	private void drop(int x, int y, int spaces) {
		board[y + spaces][x] = getTile(x, y);
		board[y][x] = Tile.EMPTY;
	}

	private void alignLeft() {
		int auxCol = lastCol;
		lastCol = -1;
		
		for( int x = 0; x <= auxCol; x++ ) {
			Tile bottomTile = getTile(x, height() - 1);
			
			if ( !bottomTile.isEmpty() ) {	
				Tile leftTile = getLeft(x, height() - 1);
				
				if ( leftTile != null && leftTile.isEmpty() )
					alignCol(x);
				lastCol++;
			}
		}
	}
	
	private void alignCol(int col){
		for ( int y = height() - 1; y >= lastRow; y-- ) {
			board[y][lastCol + 1] = board[y][col];
			board[y][col] = Tile.EMPTY;
		}
	}
	
	public boolean isOver() {
		for( int y = height() - 1; y >= lastRow; y-- )
			for( int x = 0; x <= lastCol; x++ )
				if( hasInOrderAdjacents(x, y) )
					return false;
		
		return true;
	}
	
	public boolean isEmpty() {
		return lastCol == -1 && lastRow == height();
	}
	
	public Board clone() {
		Tile[][] boardCopy = new Tile[height()][width()];
		
		for(int i = 0; i < height(); i++)
			boardCopy[i] = board[i].clone();
		
		return new Board(boardCopy, lastRow, lastCol);
	}
	
	private boolean hasInOrderAdjacents(int x, int y) {
		Tile tile = getTile(x, y);
		
		if( tile.isEmpty() )
			return false;
		if( tile.equals(getRight(x, y)) )
			return true;
		if( tile.equals(getTop(x, y)) )
			return true;
		
		return false;
	}

	public boolean hasAdjacents(int x, int y) {
		Tile tile = getTile(x, y);
		
		if( tile.isEmpty() )
			return false;
		if( hasInOrderAdjacents(x, y) )
			return true;
		if( tile.equals(getLeft(x, y)) )
			return true;
		if( tile.equals(getBottom(x, y)) )
			return true;
		
		return false;
	}
	
	public Tile getTile(int x, int y) {
		if ( y < 0 || y >= height() || x < 0 || x >= width() )
			return null;
		
		return board[y][x];
	}
	
	private Tile getRight(int x, int y) {
		if( x + 1 >= width() )
			return null;
		
		return board[y][x + 1];
	}

	private Tile getTop(int x, int y) {
		if( y - 1 < 0 )
			return null;
		
		return board[y - 1][x];
	}

	private Tile getLeft(int x, int y) {
		if( x - 1 < 0 )
			return null;
		
		return board[y][x - 1];
	}
	
	private Tile getBottom(int x, int y) {
		if( y + 1 >= height() )
			return null;
		
		return board[y + 1][x];
	}
	
	public Group getGroup(int x, int y) {
		return new Group(x, y);
	}
	
	public int getLastCol() {
		return lastCol;
	}
	
	public int getLastRow() {
		return lastRow;
	}
	
	public int width() {
		return board[0].length;
	}

	public int height() {
		return board.length;
	}
	
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

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}
	
	public String toString() { // TODO: REMOVE.
		String ans = "";
		for( int y = 0; y < board.length; y++ ) {
			for( int x = 0; x < board[0].length; x++ )
				ans += board[y][x].getChar() + " ";
			ans += "\n";
		}
		return ans;
	}
	
	public class Group {

		private Set<Point> points = new HashSet<Point>();
		private Set<Integer> cols = new HashSet<Integer>();
		private Point firstPoint;
		private Tile tile;
		
		public Group(int x, int y) {
			this.firstPoint = new Point(x, y);
			tile = getTile(x, y);
			addPoint(firstPoint);
			findAdj(firstPoint);
		}
		
		private void findAdj(Point p) {
			for( int[] move: moves ){
				Point adj = new Point(p.x + move[0], p.y + move[1]);
				Tile adjTile = getTile(adj.x, adj.y);
				
				if( adjTile != null && adjTile.equals(tile) && !points.contains(adj) ) {
					addPoint(adj);
					findAdj(adj);
				}
			}
		}
		
		private void addPoint(Point p) {
			points.add(p);
			cols.add(p.x);
		}
		
		public Point getPoint() {
			return firstPoint;
		}
		
		public String toString() { // TODO: Remove.
			return "Group: " + getPoint() + " Color: " + tile;
		}
	}

}