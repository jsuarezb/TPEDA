package model.minimax;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import model.board.Board;
import model.board.Tile;
import model.board.Tile.Color;

public class Group {

	private static final Set<Point> canonicPoints = new HashSet<Point>();
	private Set<Point> group = new HashSet<Point>();
	private Point firstPoint;
	private Color color;
	
	public Group(Tile tile, Point p){
		this.firstPoint = p;
		color = tile.getColor();
		initializeCanonicP();
		addPoint(p);
	}
	
	private void initializeCanonicP(){
		canonicPoints.add(new Point(0,1));
		canonicPoints.add(new Point(1,0));
		canonicPoints.add(new Point(-1,0));
		canonicPoints.add(new Point(0,-1));
	}
	
	public void addPoint(Point p){
		group.add(p);
	}
	
	public boolean shouldContain(Tile tile, Point p){
		if( !tile.getColor().equals(color) )
			return false;
		for(Point groupP: group )
			if( canonicPoints.contains(new Point(p.x-groupP.x, p.y-groupP.y)) )
				return true;
		return false;
	}
	
	public Point getPoint(){
		return firstPoint;
	}
	
	public String toString(){
		return "Group: " + getPoint() + " Color: " + color;
	}

	public Set<Point> completeGroup(Board board) {
		findAdj(firstPoint, board);
		return group;
	}
	
	public void findAdj(Point p, Board board){
		for( int[] move: Board.moves ){
			Point movPoint = new Point(p.x + move[0], p.y + move[1]);
			Tile tile = board.getTile(movPoint);
			if( tile != null && tile.getColor().equals(color) && !group.contains(movPoint) ){
				group.add(movPoint);
				findAdj(movPoint, board);
			}
		}
	}
}
