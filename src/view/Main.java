package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import model.Game;
import model.minimax.Minimax;
import model.utils.GameFactory;

public class Main extends JFrame { // TODO: Todo's.

	private static final long serialVersionUID = 1L;
	private static final int mPanelRelX = 8;
	private static final int mPanelRelY = 30;
	private static final long TIMER = 1000;

	private Game game;
	private MainPanel mainPanel;
	private Minimax minimax;
	private int height;
	private int time;
	private boolean prune;
	private boolean PCMode;

	public Main(Game game, Minimax minimax, int height, int time, boolean prune, boolean PCMode) {
		super("Azulejos");

		this.game = game;
		this.minimax = minimax;
		this.height = height;
		this.time = time;
		this.prune = prune;
		this.PCMode = PCMode;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		this.setContentPane(mainPanel = new MainPanel(this, game, minimax));
		this.setSize(mainPanel.getWidth(), mainPanel.getHeight() + 40);
		this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

		addMouseListener(new MouseAdapter() {  //TODO: CHANGE AND ADD BUTTON.  	

			public void mouseClicked(MouseEvent e) {
				Main main = Main.this;
				if(!main.PCMode)
					main.playPlayer1(e.getX(), e.getY());
				else
					while( !main.game.isOver() )
						main.playComputer();				
			}
		});
	}

	public void playPlayer1(int x, int y) {
		if(!game.isOver() && game.isP1Turn() && !game.isBeingPlayed() ) {
			Point p = mainPanel.getTilePosition(x - mPanelRelX, y - mPanelRelY);
			if( p != null ) {
				game.play(p.x, p.y);	
				mainPanel.refresh();
			}
		}
	}

	public void playPlayer2() {
		if( !game.isOver() && game.isP2Turn() && !game.isBeingPlayed() )
			playPC();
	}

	public void playPC() {
		Point mov = minimax.minimax(game, height, time, prune);
		game.play(mov.x, mov.y);	
		mainPanel.refresh();	
	}

	public void playComputer() {
		playPC();
		try {
			Thread.sleep(TIMER);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, FileNotFoundException {
		Game game = (new GameFactory()).getGame();		
		Main mainWindow = new Main(game, new Minimax(), 1, 10, true, false);
		mainWindow.setVisible(true);
	}

}
