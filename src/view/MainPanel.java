package view;

import java.awt.Point;
import javax.swing.JPanel;
import model.Game;
import model.minimax.Minimax;
import view.panel.GamePanel;
import view.panel.InfoPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 40;
	private static final int INFO_WIDTH = 300;
	
	Game game;
	Minimax minimax;
	private GamePanel gamePanel;
	private InfoPanel infoPanel;
	private ImageManager imgManager;
	
	private int height;
	
	public MainPanel(Game game, Minimax minimax) {
		this.game = game;
		this.minimax = minimax;
		this.imgManager = new ImageManager();
		this.height = CELL_SIZE * game.height();
		setLayout(null);
		add(infoPanel = new InfoPanel(game, imgManager, CELL_SIZE * game.width(), INFO_WIDTH, height));
		add(gamePanel = new GamePanel(game, imgManager, CELL_SIZE));
		setSize(CELL_SIZE * game.width() + INFO_WIDTH, height);
		refresh();
	}	
	
	public Point getTilePosition(int x, int y) {
		return gamePanel.getTilePosition(x,y);
	}
	
	public void refresh() {
		gamePanel.refresh();
		infoPanel.refresh();
	}
}
