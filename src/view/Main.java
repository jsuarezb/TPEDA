package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import model.Game;
import model.minimax.Minimax;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;
	
	public Main(Game game, Minimax minimax) {
		super("Azulejos");
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(true);
	    Toolkit toolkit = getToolkit();
	    Dimension size = toolkit.getScreenSize();
	    this.setContentPane(mainPanel = new MainPanel(game, minimax));
	    this.setSize(mainPanel.getWidth(), mainPanel.getHeight() + 40);
	    this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Main mainWindow = new Main(new Game(), new Minimax());
		mainWindow.setVisible(true);
	}

}
