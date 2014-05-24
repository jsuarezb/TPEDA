package view.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import view.Main;
import view.button.Player2Button;
import model.Game;

public class ScorePanel extends JPanel { 
	private static final long serialVersionUID = 1L;
	
	private Game game;
	
	public ScorePanel(Main main, Game game) {
		this.game = game;
		setBackground(Color.WHITE);
		setLayout(null);
		this.setPreferredSize(new Dimension(200, 150));
		Player2Button button = new Player2Button(main);
		add(button);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		final int fontSize = 13;
		Font plain = new Font("Arial", Font.PLAIN, fontSize);
		g.setFont(plain);
		g.drawString("Player1's score: " + game.getP1Score(), 45, 40);
		g.drawString("Player2's score: " + game.getP2Score(), 45, 60);	
		
		if (game.isOver()) {
			Font font2 = new Font("Arial", Font.BOLD, 12);
			g.setFont(font2);
			
			if ( game.P1Won() )
				g.drawString("Game Over - Player1 WON !!!", 20, 80);
			else
				g.drawString("Game Over - Player1 LOSE !!!", 30, 80);
		}
	}
}
