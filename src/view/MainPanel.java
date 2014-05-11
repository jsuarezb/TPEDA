package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import model.Game;
import model.minimax.Minimax;
import view.panel.GamePanel;
import view.panel.InfoPanel;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 40;
	private static final int INFO_WIDTH = 300;
	
	private Game game;
	private Minimax minimax;
	private GamePanel gPanel;
	private InfoPanel infoPanel;
	private ImageManager imgManager;
	
	private int height;
	
	public MainPanel(Game game, Minimax minimax){
		this.game = game;
		this.minimax = minimax;
		this.imgManager = new ImageManager();
		this.height = CELL_SIZE * game.getHeightSize();
		setLayout(null);
		add(infoPanel = new InfoPanel(game, imgManager, CELL_SIZE * game.getWidthSize(), INFO_WIDTH, height));
		add(gPanel = new GamePanel(game, imgManager, CELL_SIZE, Color.BLACK));
		setSize(CELL_SIZE * game.getWidthSize() + INFO_WIDTH, height);
		refresh();
		
		addMouseListener(new MouseAdapter() {    	
	    	@Override
			public void mouseClicked(MouseEvent e) {
	    		MainPanel mPanel = MainPanel.this;
				if(!mPanel.game.isOver()){
					Point tilePos = mPanel.getTilePosition(e.getX(), e.getY());
					if( tilePos != null && MainPanel.this.game.isPlayer1Turn() )
						mPanel.game.play(tilePos);
					else if( tilePos != null && MainPanel.this.game.isPlayer2Turn() ){
						Point pos =  MainPanel.this.minimax.minimax(MainPanel.this.game);
						MainPanel.this.game.play(pos);			
					}		
				}
				mPanel.refresh();
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
