package model.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import model.Game;
import model.board.Board.Group;

public class Minimax { // TODO: Todo's.
	
	public Point minimax(Game game) {	
		Node root = new Node(game);
		double value = minimax(root, 5);
		
		for( Node son: root.sons )
			if( son.value == value )
				return son.play;
		
		return null;
	}
	
	private double minimax(Node node, int height) {
		Game game = node.getGame();
		
		if( height == 0 || game.isOver() ) { // TODO: MOVE INTO NODE.
			node.setValue(heuristic(game));
			return node.value;
		}
		
		double value;
		
		if( game.isP1Turn() )
			value = Double.POSITIVE_INFINITY;
		else
			value = Double.NEGATIVE_INFINITY;
		
		List<Group> groups = game.getGroups();
		
		for(Group group: groups) {
			Game gameCopy = game.clone();
			gameCopy.play(group);
			Node newNode = new Node(gameCopy, group.getPoint());
			node.addSon(newNode);
			// TODO: GAME SET HAS BEEN REMOVED. 
			if( game.isP1Turn() ) //TODO: MOVE INTO NODE.
				value = Math.min(value, minimax(newNode, height - 1));
			else
				value = Math.max(value, minimax(newNode, height - 1));
		}
		
		node.setValue(value); //TODO: MOVE INTO NODE.
		
		return value;
	}
	
	private double heuristic(Game game) {
		return game.getP2Score() - game.getP1Score();
	}
	
	private static class Node {
		Game game;
		Point play;
		double value;
		List<Node> sons;
		
		public Node(Game game) {
			this(game, null);
		}
		
		public Node(Game game, Point play) {
			this.game = game;
			this.play = play;
			sons = new ArrayList<Node>();
		}
		
		public void addSon(Node node) {
			sons.add(node);
		}
		
		public void setValue(double value) {
			this.value = value;
		}
		
		public Game getGame() {
			return game;
		}
	}
}
