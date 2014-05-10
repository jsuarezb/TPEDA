package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import model.Game;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;
	
	public Main(Game game) {
		super("Azulejos");
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(true);
	    Toolkit toolkit = getToolkit();
	    Dimension size = toolkit.getScreenSize();
	    this.setContentPane(mainPanel = new MainPanel(game));
	    this.setSize(mainPanel.getWidth(), mainPanel.getHeight() + 40);
	    this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Main mainWindow = new Main(new Game());
		mainWindow.setVisible(true);
	}

}
