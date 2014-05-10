package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import model.Game;
import view.panel.GamePanel;
import view.panel.InfoPanel;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 40;
	private static final int INFO_WIDTH = 300;
	
	private Game game;
	private GamePanel gPanel;
	private InfoPanel infoPanel;
	private ImageManager imgManager;
	
	private int height;
	
	public MainPanel(Game game){
		this.game = game;
		this.imgManager = new ImageManager();
		this.height = CELL_SIZE * game.getHeightSize();
		setLayout(null);
		add(infoPanel = new InfoPanel(game, imgManager, CELL_SIZE * game.getWidthSize(), INFO_WIDTH, height));
		add(gPanel = new GamePanel(game, imgManager, CELL_SIZE, Color.BLACK));
		setSize(CELL_SIZE * game.getWidthSize() + INFO_WIDTH, height);
		System.out.println(CELL_SIZE * game.getWidthSize() + INFO_WIDTH + " " + height);
		refresh();
		
		addMouseListener(new MouseAdapter() {
	    	
	    	@Override
			public void mouseClicked(MouseEvent e) {
				Point tilePos = MainPanel.this.getTilePosition(e.getX(), e.getY());
				if( tilePos != null ){
					MainPanel.this.game.play(tilePos);
					MainPanel.this.refresh();
				}
			}
		});
	}	
	
	public Point getTilePosition(int x, int y) {
		return gPanel.getTilePosition(x,y);
	}
	
	public void refresh() {
		gPanel.refresh();
		infoPanel.refresh();
	}
}
