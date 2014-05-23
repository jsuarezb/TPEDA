package model.minimax;

import java.awt.Point;
import java.util.List;

import model.Game;
import model.board.Board.Group;

public class MinNode extends Node {

	protected MinNode(Game game, Point play) {
		super(game, play);
	}

	public MinNode(Game game, Point play, List<Node> sons, double value, boolean isPruned) {
		super(game, play, sons, value, isPruned);
	}

	@Override
	protected double minimax(int height, double alpha, double beta, long finalTime) {
		if( System.currentTimeMillis() > finalTime ) // Breaks the recursive call.
			return 0;
		
		if( getGame().isOver() )
			return gameOverValue();
		
		if( height == 0 )
			return leafValue();
		
		value = Double.POSITIVE_INFINITY;
		
		Game game = getGame();
		List<Group> groups = game.getGroups();

		for(Group group: groups) {
			Node son = createSon(game, group);
			value = Math.min(value, son.minimax(height - 1, alpha, beta, finalTime));
			
			if(prune){
				beta = value;
				if( beta <= alpha ) {
					isPruned = true;
					return beta;
				}
			}
		}
		
		return value;
	}
	
	@Override
	protected double gameOverValue() {
		double leafValue = leafValue();
		
		if( leafValue > 0 )
			value = leafValue;
		else
			value = Double.NEGATIVE_INFINITY;
		
		return value;
	}

	protected Node clone() {
		return new MinNode(getGame().clone(), getPlay(), sonsClone(), value, isPruned);
	}

	@Override
	protected Node createSon(Game game, Group group) {
		Game gameCopy = game.clone();
		gameCopy.play(group);
		Node son = new MaxNode(gameCopy, group.getPoint());
		addSon(son);
		return son;
	}
}
