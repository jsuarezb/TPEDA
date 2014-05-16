package view.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Game;
import view.ImageManager;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Game game;
	private ScorePanel scorePanel;
	private int width;
	private int height;
	
	public InfoPanel(Game game, ImageManager imgManager, int xPosition, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		
		setBackground(Color.GRAY);
		setBounds(xPosition, 0, width, height);
		scorePanel = new ScorePanel(game);
		scorePanel.setVisible(true);
		add(scorePanel);
		refresh();
	}
	
	public void refresh(){
		paintImmediately(0, 0, width, height);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (game.isOver()) {
			Font plain = new Font("Arial", Font.BOLD, 12);
			g.setFont(plain);
			
			if ( game.P1Won() )
				g.drawString("Game Over - Player1 WON !!!", 60, 140);
			else
				g.drawString("Game Over - Player1 LOSE !!!", 60, 140);
		}
	}	
}

