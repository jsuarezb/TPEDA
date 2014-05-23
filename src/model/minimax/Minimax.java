package model.minimax;

import java.awt.Point;
import model.Game;
import model.minimax.NonValidPlayException;
import model.utils.GraphWriter;

public class Minimax {
	
	private int depth;
	private int time;
	protected boolean tree;
	
	public Minimax(int depth, int time, boolean tree) {
		this.depth = depth;
		this.time = time;
		this.tree = tree;
	}
	
	public Point minimax(Game game) { 
		Node root = createTree(game);

		for( Node son: root.getSons() )
			if( son.value == root.value )
				return son.getPlay();

		throw new NonValidPlayException();
	}
	
	public Point minimaxByConsole(Game game) {
		Node root = createTree(game);

		if(tree)
			(new GraphWriter()).makeDotFile(root);
		
		for( Node son: root.getSons() )
			if( son.value == root.value )
				return son.getPlay();

		throw new NonValidPlayException();
	}
	
	private Node createByTime(Game game) {
		long finalTime = System.currentTimeMillis()  + time*1000;		
		
		Node curr = new RootNode(game);		
		curr.minimax(depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Long.MAX_VALUE);
		Node prev = curr.clone();
		int height = 2;

		while( System.currentTimeMillis() < finalTime ) {
			prev = curr.clone();
			curr = new RootNode(game);
			curr.minimax(height++, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, finalTime);
			if( curr.height() == prev.height() )
				break;
		}	

		return prev;
	}
	
	private Node createTree(Game game) {
		if( time != 0 )
			return createByTime(game);
		
		Node root = new RootNode(game);		
		root.minimax(depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Long.MAX_VALUE);
	
		return root;
	}
}
