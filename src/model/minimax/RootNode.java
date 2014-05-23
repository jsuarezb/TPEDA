package model.minimax;

import java.util.List;

import model.Game;
import model.board.Board.Group;

public class RootNode extends MaxNode {
	
	protected RootNode(Game game) {
		super(game, null);
	}

	public RootNode(Game game, List<Node> sons, double value) {
		super(game, null, sons, value, false);
	}

	protected Node clone() {
		return new RootNode(getGame().clone(), sonsClone(), value);
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
