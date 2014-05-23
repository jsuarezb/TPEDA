package model.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import model.Game;
import model.board.Board.Group;
import view.Main;

public abstract class Node {

	public static boolean prune;
	
	private Game game;
	private Point play;
	private List<Node> sons = new ArrayList<Node>();

	protected double value;
	protected boolean isPruned;

	protected Node(Game game, Point play) {
		this.game = game;
		this.play = play;
	}
	
	public Node(Game game, Point play, List<Node> sons, double value, boolean isPruned) {
		this.game = game;
		this.play = play;
		this.sons = sons;
		this.value = value;
		this.isPruned = isPruned;
	}

	protected double leafValue() {
		if( Main.P2ISMAX )
			value = game.getP2Score() - game.getP1Score();
		else 
			value = game.getP1Score() - game.getP2Score();
		return value;
	}
	
	protected List<Node> sonsClone() {
		List<Node> sonsCopy = new ArrayList<Node>();

		for( Node son: sons )
			sonsCopy.add(son.clone());

		return sonsCopy;
	}

	protected int height() {
		if( sons == null || sons.isEmpty() )
			return 1;
		else {
			int maxH = 0;
			for( Node son: sons )
				maxH = Math.max(maxH, son.height());
			return maxH + 1;
		}
	}
	
	public void addSon(Node node) {
		sons.add(node);
	}

	public Game getGame() {
		return game;
	}

	public boolean isPruned() {
		return isPruned;
	}

	public List<Node> getSons() {
		return sons;
	}

	public Point getPlay() {
		return play;
	}

	public double getValue() {
		return value;
	}

	protected abstract double minimax(int height, double alpha, double beta, long finalTime);
	
	protected abstract double gameOverValue();
	
	protected abstract Node clone();
	
	protected abstract Node createSon(Game game, Group group);
}
