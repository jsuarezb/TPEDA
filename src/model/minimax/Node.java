package model.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Game;

public class Node {
	private Game game;
	private Point play;
	protected double value;
	private List<Node> sons;
	private int height;
	
	public Node(Game game, int height) {
		this(game, null, height);
	}
	
	public Node(Game game, Point play, int height) {
		this.game = game;
		this.play = play;
		this.height = height;
		sons = new ArrayList<Node>();
	}
	
	public void calculateValue() {
		if( game.isOver() ) {
			gameOverValue();
			return;
		}
		
		if( height == 0 ) {
			leafValue();
			return;
		}
		
		minMaxValue();
	}
	
	private void leafValue() {
		value = game.getP2Score() - game.getP1Score();
	}
	
	public void addSon(Node node) {
		sons.add(node);
	}
	
	public Game getGame() {
		return game;
	}
	
	public Point getPlay() {
		return play;
	}
	
	public List<Node> getSons() {
		return sons;
	}
	
	public int getHeight() {
		return height;
	}
}
