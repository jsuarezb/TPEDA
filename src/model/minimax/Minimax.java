package model.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Game;

public class Minimax {
	
	public Point minimax(Game game){	
		Node<Game> root = new Node<Game>(game);
		double value = minimax(root, 3);
		System.out.println("Termine :)");
		for( Node<Game> son: root.sons )
			if( son.value == value ){
				System.out.println(son.play);
				return son.play;
			}
		return null;
	}
	
	public double minimax(Node<Game> node, int height){
		Game game = node.getElem();
		double value;
		if( height == 0 || game.isOver() )
			return heuristic(game);
		
		if( game.isPlayer1Turn() )
			value = Double.POSITIVE_INFINITY;
		else
			value = Double.NEGATIVE_INFINITY;
		
		Set<Game> games = new HashSet<Game>();
		for(int x = 0; x <= game.getLastCol(); x++)
			for(int y = game.getHeightSize() - 1; y >= game.getLastRow(); y--){
				Game gameCopy = game.clone();
				Point play = new Point(x,y);
				gameCopy.play(play);
				if( !games.contains(gameCopy) && !gameCopy.equals(game)){
					games.add(gameCopy);
					Node<Game> newNode = new Node<Game>(gameCopy, play);
					node.addSon(newNode);
					if( game.isPlayer1Turn() ){
						value = Math.min(value, minimax(newNode, height - 1));
						System.out.println("value: " + value + "mov: " + play + "turn: p1" + "height: " + height);
					}
					else{
						value = Math.max(value, minimax(newNode, height - 1));
						System.out.println("value: " + value + "mov: " + play + "turn: p2" + "height: " + height);
					}
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
