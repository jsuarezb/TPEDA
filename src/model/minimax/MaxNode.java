package model.minimax;

import java.awt.Point;

import model.Game;

public class MaxNode extends Node {

	public MaxNode(Game game, int height) {
		super(game, height);
	}
	
	public MaxNode(Game game, Point play, int height) {
		super(game, play, height);
	}

	private void minMaxValue() {
		value = Double.NEGATIVE_INFINITY;
		
		for( Node son: getSons() )
			value = Math.max(value, minimax(son, getHeight() - 1));
	}
}
