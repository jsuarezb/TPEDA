package view.panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Game;
import view.ImageManager;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Game game;
	private ScorePanel scorePanel;
	
	public InfoPanel(Game game, ImageManager imgManager, int xPos, int width, int height) {
		this.game = game;
		
		setBackground(Color.GRAY);
		setBounds(xPos, 0, width, height);
		scorePanel = new ScorePanel(game);
		scorePanel.setVisible(true);
		add(scorePanel);
		refresh();
	}
	
	public void refresh(){
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (game.isOver()) {
			Font plain = new Font("Arial", Font.BOLD, 12);
			g.setFont(plain);
			
			if (game.playerWon()) {
				g.drawString("Game Over - Player WON !!!", 10, 140);
			} else {
				g.drawString("Game Over - Player DIED !!!", 10, 140);
			}
		}
	}	
}

