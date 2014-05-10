package view.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.Game;

public class ScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	public ScorePanel(Game game){
		this.game = game;
		setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(200, 150));
		setLayout(null);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		final int fontSize = 13;
		Font plain = new Font("Arial", Font.PLAIN, fontSize);
		g.setFont(plain);
		g.drawString("Player1's score: " + game.getPlayer1Score(), 10, 60);
		g.drawString("Player2's score: " + game.getPlayer2Score(), 10, 80);
		
	}
}
