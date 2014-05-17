package model.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import model.Game;
import model.board.Board.Group;

public class Minimax { // TODO: Todo's.

	private static final int HEIGHT = 10; //TODO REMOVE.

	public Point minimax(Game game, boolean prune, long time) {       
		Node root = new Node(game, HEIGHT);
		double value;
		if(prune)
			value = alphaBeta(root, HEIGHT, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		else
			value = minimax(root, HEIGHT);

		for( Node son: root.sons ){
			System.out.println("son: " + son.value + " value: " + value);
			if( son.value == value )
				return son.play;
		}

		throw new NonValidPlayException();
	}

	private static double minimax(Node node, int height) {
		Game game = node.getGame();    

		if( height == 0 || game.isOver() ) {
			node.calculateValue();
			System.out.println("Leaf");
			return node.value;
		}

		List<Group> groups = game.getGroups();

		for(Group group: groups) {
			Game gameCopy = game.clone();
			gameCopy.play(group);
			Node newNode = new Node(gameCopy, group.getPoint(), height - 1);
			node.addSon(newNode);
		}

		node.calculateValue(); 
		return node.value;
	}

	private double alphaBeta(Node node, int height, double alpha, double beta){
		Game game = node.getGame();    

		if(height == 0 || game.isOver()){
			node.calculateValue();
			return node.value;
		}

		List<Group> groups = game.getGroups();

		for(Group group: groups) {
			Game gameCopy = game.clone();
			gameCopy.play(group);
			Node newNode = new Node(gameCopy, group.getPoint(), height - 1);
			node.addSon(newNode);
		}

		if(game.isP2Turn()){
			for(Node son: node.sons){
				alpha = Math.max(alpha, alphaBeta(son, height - 1, alpha, beta));
				if(beta <= alpha){
					node.setValue(alpha); 
					return alpha;
				}
			}
			node.setValue(alpha); 
			return alpha;
		}else{
			for(Node son: node.sons){
				beta = Math.min(beta, alphaBeta(son, height - 1, alpha, beta));
				if(beta <= alpha) {
					node.setValue(beta);
					return beta;
				}
			}
			node.setValue(beta); 
			return beta;
		}
	}

	private static class Node {
		Game game;
		Point play;
		double value;
		List<Node> sons;
		int height;

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

			if( game.isP1Turn() )
				minValue();
			else
				maxValue();
		}

		private void maxValue() {
			value = Double.NEGATIVE_INFINITY;

			for( Node son: sons )
				value = Math.max(value, minimax(son, height - 1));
		}

		private void minValue() {
			value = Double.POSITIVE_INFINITY;

			for( Node son: sons )
				value = Math.min(value, minimax(son, height - 1));
		}




		private void gameOverValue() {
			if( game.isP1Turn() )
				if( game.getP2Score() - game.getP1Score() > 0 )
					leafValue();
				else
					value = Double.NEGATIVE_INFINITY;
			else
				if( game.getP2Score() - game.getP1Score() > 0 )
					value = Double.POSITIVE_INFINITY;
				else
					leafValue();

			return;
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
		
		public void setValue(double value) {
			this.value = value;
		}
	}
}