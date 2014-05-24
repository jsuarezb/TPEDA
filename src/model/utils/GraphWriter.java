package model.utils;

import java.io.PrintWriter;
import model.minimax.MaxNode;
import model.minimax.Node;

public class GraphWriter {
	
	private int nId = 0;
	
	public void makeDotFile(Node root) { //TODO: REMOVE GREEN FOR GAMEOVER.
		try {
			PrintWriter writer = new PrintWriter("tree.dot", "UTF-8");
			
			writer.println("digraph minimaxTree {");
			writer.println("node" + nId + " [label=\"START " + root.getValue() + 
						   "\", shape=\"" + getShape(root) + "\", style=\"filled\", color=\"red\"];");		
			addNodeSonsLines(writer, root, nId++ );		
			writer.println("}");	
			
			writer.close();
		} catch (Exception e) {
			throw new IllegalMakeFileException();
		}
	}
	
	private void addNodeSonsLines(PrintWriter writer, Node node, int parentId ) {
		double value = node.getValue();
		boolean noSelected = true;
		
		for( Node son: node.getSons() ) {			
			String color;
			
			if( son.isPruned() )
				color = "gray";
			else if( son.getValue() == value && noSelected ) {
				color = "red";
				noSelected = false;
			}
			else
				color = "blue";
			
			if( son.getGame().isOver() )
				color = "green";
			
			writeGraphNode(writer, son, color);
			writeGraphEdge(writer, parentId );			
			
			//if( !son.isPruned() )
				addNodeSonsLines(writer, son, nId - 1);
		}
	}

	private void writeGraphNode(PrintWriter writer, Node node, String color ) {
		String label = getLabel(node);
		String shape = getShape(node);
		
		writer.println("node" + nId + " [label=\"" + label + "\", " + 
				"shape=\"" + shape + "\", style=\"filled\", color=\"" + color + "\"];");		
	}
	
	private void writeGraphEdge(PrintWriter writer, int parentId ) {
		writer.println("node" + parentId + " -> node" + nId++ + ";");
	}
	
	private String getShape(Node node) {
		if( node instanceof MaxNode )
			return "box";
		else
			return "oval";
	}
	
	private String getLabel(Node node) {
		return "(" + node.getPlay().y + ", " + node.getPlay().x + ") " + node.getValue();
	}
}
