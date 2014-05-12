package model.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Game;
import model.board.Board.Group;

public class Minimax {
	
	public Point minimax(Game game){	
		System.out.println("minimax");
		List<Group> groups = game.getGroups();
		for(Group group: groups)
			System.out.println(group);
		Node<Game> root = new Node<Game>(game);
		double value = minimax(root, 2);
		for( Node<Game> son: root.sons )
			if( son.value == value )
				return son.play;
		return null;
	}
	
	public double minimax(Node<Game> node, int height){
		long miniTime = System.nanoTime();
		Game game = node.getElem();
		double value;
		if( height == 0 || game.isOver() ){
			node.setValue(heuristic(game));
			long minFTime = System.nanoTime();
			long minTime = minFTime - miniTime;
			System.out.println("HeurTime: " + minTime);
			return node.value;
		}
		
		if( game.isPlayer1Turn() )
			value = Double.POSITIVE_INFINITY;
		else
			value = Double.NEGATIVE_INFINITY;
		
		Set<Game> games = new HashSet<Game>();
		long giT = System.nanoTime();
		List<Group> groups = game.getGroups();
		long gfT = System.nanoTime();
		long gT = gfT - giT;
		for(Group group: groups){
			Game gameCopy = game.clone();
			Point play = group.getPoint();
			long piT = System.nanoTime();
			gameCopy.play(play);
			long pfT = System.nanoTime();
			long pT = pfT - piT;
			if( !games.contains(gameCopy) && !gameCopy.equals(game)){
				games.add(gameCopy);
				Node<Game> newNode = new Node<Game>(gameCopy, play);
				node.addSon(newNode);
				long minFTime = System.nanoTime();
				long minTime = minFTime - miniTime;
				System.out.println("MinTime: " + minTime + " PlayTime: " + pT + " groupTime: " + gT);
				if( game.isPlayer1Turn() )
					value = Math.min(value, minimax(newNode, height - 1));
				else
					value = Math.max(value, minimax(newNode, height - 1));
			}
		}
		node.setValue(value);
		return value;
	}
	
	public double heuristic(Game game){
		return game.getPlayer2Score() - game.getPlayer1Score();
	}
	
	private static class Node<T>{
		T elem;
		Point play;
		double value;
		List<Node<T>> sons;
		
		public Node(T elem){
			this(elem, null);
		}
		
		public Node(T elem, Point play){
			this.elem = elem;
			this.play = play;
			sons = new ArrayList<Node<T>>();
		}
		
		public void addSon(Node<T> node){
			sons.add(node);
		}
		
		public T getElem(){
			return elem;
		}
		
		public void setValue(double value){
			this.value = value;
		}
	}
}
