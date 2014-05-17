package model.utils;

import java.awt.Point;
import java.io.PrintWriter;
import model.Game;
import model.minimax.Minimax.Node;

public class GraphWriter {
	
	public void makeDotFile(Node root) {
		try {
			PrintWriter writer = new PrintWriter("tree.dot", "UTF-8");
			
			writer.println("digraph minimaxTree {");
			writer.println("node" + root.hashCode() + " [label=\"START " + root.getValue() + 
						   "\", shape=\"box\", style=\"filled\", color=\"red\"];");
			
			addNodeSonsLines(writer, root);
			
			writer.println("}");
			
			writer.close();
		} catch (Exception e) {
			throw new IllegalMakeFileException();
		}
	}
	
	private void addNodeSonsLines(PrintWriter writer, Node node) {
		Game game = node.getGame();
		double value = node.getValue();
		boolean turn = !game.isP2Turn();
		boolean noSelected = true;
		int hash = node.hashCode();
		
		for( Node son: node.getSons() ) {
			Point p = son.getPlay();
			String label = "(" + p.y + ", " + p.x + ") " + son.getValue();
			
			String color;
			if( son.isPruned() )
				color = "gray";
			else if( son.getValue() == value && noSelected ){
				color = "red";
				noSelected = false;
			}
			else
				color = "blue";
			
			String shape;
			if( turn )
				shape = "box";
			else
				shape = "oval";
			
			writer.println("node" + son.hashCode() + " [label=\"" + label + "\", " + 
							"shape=\"" + shape + "\", style=\"filled\", color=\"" + color + "\"];");
			writer.println("node" + hash + " -> node" + son.hashCode() + ";");
			
			if( !son.isPruned() )
				addNodeSonsLines(writer, son);
		}
	}
}
