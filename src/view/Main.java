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
import model.utils.ParametersValidator;

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
	private boolean tree;
	private boolean PCMode;

	public Main(Game game, Minimax minimax, int height, int time, boolean prune, boolean tree, boolean visual, boolean PCMode) {
		super("Azulejos");

		this.game = game;
		this.minimax = minimax;
		this.height = height;
		this.time = time;
		this.prune = prune;
		this.tree = tree;

		if( !visual )
			return;
			
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

	private void playPlayer1(int x, int y) {
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

	private void playPC() {
		Point mov = minimax.minimax(game, height, time, prune, tree);
		game.play(mov.x, mov.y);	
		mainPanel.refresh();	
	}

	private void playComputer() {
		playPC();
		try {
			Thread.sleep(TIMER);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void consolePlay() {
		Point mov = minimax.minimax(game, height, time, prune, tree);
		if( mov == null )
			System.out.println("No valid movements.");
		else
			System.out.println("(" + mov.y + ", " + mov.x + ")");
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, FileNotFoundException {
		ParametersValidator params = new ParametersValidator(args);
		Game game = (new GameFactory(params.getFilename())).getGame();		
		Main mainWindow = new Main(game, new Minimax(), params.getDepth(), params.getTime(), 
				params.hasPrune(), params.hasTree(), params.isVisual(), false);
		
		if( params.isVisual() )
			mainWindow.setVisible(true);
		else
			mainWindow.consolePlay();
	}

}
