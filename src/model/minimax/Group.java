package model.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.board.Tile;
import model.board.Tile.Color;

public class Group {

	private static final List<Point> canonicPoints = new ArrayList<Point>();
	private List<Point> group = new ArrayList<Point>();
	private Color color;
	
	public Group(Tile tile, Point p){
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
		return group.get(0);
	}
	
	public String toString(){
		return "Group: " + getPoint() + " Color: " + color;
	}
}
