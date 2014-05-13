package model.board;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.board.Tile.Color;

public class Board {
	private Tile[][] board;
	private int lastCol;
	private int lastRow;
	public static final int[][] moves = new int[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	
	public Board( Tile[][] board ) {
		this(board, 0, board[0].length - 1);
	}
	
	public Board(Tile[][] board, int lastRow, int lastCol){
		this.board = board;
		this.lastRow = lastRow;
		this.lastCol = lastCol;
	}

	/*public List<Group> getGroups() {
		List<Group> groups = new ArrayList<Group>();
		for( int y = lastRow; y < board.length; y++ )
			for( int x = 0; x < lastCol; x++ ){
				Point pos = new Point(x,y);
				Tile tile = getTile(pos);
				if( !tile.isEmpty() && hasAnyAdjacents(pos) ){
					boolean added = false;
					for(Group group: groups )
						if( group.shouldContain(tile, pos) ){
							group.addPoint(pos);
							added = true;
						}
					if(!added)
						groups.add(new Group(tile, pos));
				}
			}
		return groups;
	}*/
	
	public List<Group> getGroups() {
		List<Group> groups = new ArrayList<Group>();
		Set<Point> pointSet = new HashSet<Point>();
		for( int y = board.length- 1; y >= lastRow; y-- )
			for( int x = 0; x <= lastCol; x++ ){
				Point pos = new Point(x,y);
				if( !pointSet.contains(pos) )
					if( hasAdjacents(pos) ){
						Group group = new Group(pos);;
						pointSet.addAll(group.points);
						groups.add(group);
					}
			}
		return groups;
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

	public boolean hasAnyAdjacents(Point pos) {
		Tile tile = getTile(pos);
		if( tile.isEmpty() )
			return false;
		if( tile.equals(getRight(pos)) )
			return true;
		if( tile.equals(getTop(pos)) )
			return true;
		if( tile.equals(getLeft(pos)) )
			return true;
		if( tile.equals(getBottom(pos)) )
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
	
	private Tile getBottom(Point pos) {
		if( pos.y + 1 >= board.length )
			return null;
		return board[pos.y + 1][pos.x];
	}
	
	public Group getGroup(Point p){
		return new Group(p);
	}
	
	public int play(Group group){
		long piT = System.nanoTime();
		int tilesDeleted = delete(group);
		gravity(group.cols);
		alignLeft();
		long pfT = System.nanoTime();
		long pT = pfT - piT;
		System.out.println("deltetime: " + pT);
		System.out.println("Last row: " + lastRow + " Last col: " + lastCol);
		return tilesDeleted;
	}
	
	private int delete(Group group){
		for(Point p: group.points)
			board[p.y][p.x] = EmptyTile.getInstance();
		return group.points.size();
	}
	
	private void gravity(Set<Integer> cols){
		for(Integer col: cols)
			gravity(col);
		updateLastRow();
	}
	
	private void gravity(int x){
		int spaces = 0;
		for( int y = board.length - 1; y >= lastRow; y-- ){
			Point p = new Point(x,y);
			Tile tile = getTile(p);

			if( tile.isEmpty() )
				spaces++;
			else if( spaces > 0 )
				drop(p, spaces);
		}
	}
	
	private void updateLastRow(){
		for( int y = lastRow; y < getHeightSize(); y++ )
			for( int x = 0; x <= lastCol; x++ )
				if( !getTile(x,y).isEmpty() ){
					lastRow = y;
					return;
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
	
	public class Group {

		private Set<Point> points = new HashSet<Point>();
		private Set<Integer> cols = new HashSet<Integer>();
		private Point firstPoint;
		private Color color;
		
		public Group(Point p){
			this.firstPoint = p;
			color = getTile(p).getColor();
			addPoint(p);
			findAdj(p);
		}
		
		public void addPoint(Point p){
			points.add(p);
			cols.add(p.x);
		}

		public Point getPoint(){
			return firstPoint;
		}
		
		public String toString(){
			return "Group: " + getPoint() + " Color: " + color;
		}
		
		public void findAdj(Point p){
			for( int[] move: moves ){
				Point movPoint = new Point(p.x + move[0], p.y + move[1]);
				Tile tile = getTile(movPoint);
				if( tile != null && tile.getColor().equals(color) && !points.contains(movPoint) ){
					addPoint(movPoint);
					findAdj(movPoint);
				}
			}
		}
	}

}