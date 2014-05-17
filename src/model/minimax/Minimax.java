package model.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.board.Board.Group;
import model.utils.FileUtils;

public class Minimax { // TODO: Todo's.

	public Point minimax(Game game, int height, int time, boolean prune) {       				
		Node curr = new Node(game);
		double value;
		
		if(prune) {			
			value = alphaBeta(curr, height, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Long.MAX_VALUE);
			
			if( time != 0 ){
				long finalTime = System.currentTimeMillis()  + time*1000;
				double prevValue = value;
				Node prev = curr;
				
				while( System.currentTimeMillis() < finalTime ) {
					prev = curr;
					prevValue = value;
					value = alphaBeta(curr, ++height, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, finalTime);
					System.out.println(height);
				}	
				
				value = prevValue;
				curr = prev;
			}
		}
		else {
			value = minimax(curr, height, Long.MAX_VALUE);
			
			if( time != 0 ){
				long finalTime = System.currentTimeMillis()  + time*1000;
				double prevValue = value;
				Node prev = curr;
				
				while( System.currentTimeMillis() < finalTime ) {
					prev = curr;
					prevValue = value;
					value = minimax(curr, ++height, finalTime);
					System.out.println(height);
				}	
				
				value = prevValue;
				curr = prev;
			}
		}

		FileUtils fileManager = new FileUtils();
		fileManager.makeDotFile(curr);
		
		for( Node son: curr.sons ){
			if( son.value == value )
				return son.play;
		}

		throw new NonValidPlayException();
	}

	private static double minimax(Node node, int height, long finalTime) {
		if( System.currentTimeMillis() > finalTime ) // Breaks the recursive call.
			return 0;
		
		Game game = node.getGame();    

		if( game.isOver() ) {
			node.gameOverValue();
			return node.value;
		}

		if( height == 0 ) {
			node.leafValue();
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
			if( game.isP1Turn() )
				value = Math.min(value, minimax(newNode, height - 1, finalTime));
			else
				value = Math.max(value, minimax(newNode, height - 1, finalTime));
		}

		node.setValue(value); 
		return value;
	}

	private double alphaBeta(Node node, int height, double alpha, double beta, long finalTime){
		if( System.currentTimeMillis() > finalTime ) // Breaks the recursive call.
			return 0;
		
		Game game = node.getGame();    

		if( game.isOver() ) {
			node.gameOverValue();
			return node.value;
		}

		if( height == 0 ) {
			node.leafValue();
			return node.value;
		}

		List<Group> groups = game.getGroups();

		for(Group group: groups) {
			Game gameCopy = game.clone();
			gameCopy.play(group);
			Node newNode = new Node(gameCopy, group.getPoint());
			node.addSon(newNode);
			
			if(game.isP1Turn()){
				beta = Math.min(beta, alphaBeta(newNode, height - 1, alpha, beta, finalTime));
				if(beta <= alpha) {
					node.setValue(beta);
					node.isPruned = true;
					return beta;
				}
			} else {
				alpha = Math.max(alpha, alphaBeta(newNode, height - 1, alpha, beta, finalTime));
				if(beta <= alpha){
					node.setValue(alpha); 
					node.isPruned = true;
					return alpha;
				}
			}
		}
		
		if( game.isP1Turn() ) {
			node.setValue(beta); 
			return beta;
		} else {
			node.setValue(alpha); 
			return alpha;
		}
	}
	
	public static class Node {
		Game game;
		Point play;
		double value;
		List<Node> sons;
		boolean isPruned;

		public Node(Game game) {
			this(game, null);
		}

		public Node(Game game, Point play) {
			this.game = game;
			this.play = play;
			sons = new ArrayList<Node>();
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
		
		public void setValue(double value) {
			this.value = value;
		}
	}
}