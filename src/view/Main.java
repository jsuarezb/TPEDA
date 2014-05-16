package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import model.Game;
import model.minimax.Minimax;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	private MainPanel mainPanel;
	private Game game;
	private Minimax minimax;
	
	public Main(Game game, Minimax minimax) {
		super("Azulejos");
		
		this.game = game;
		this.minimax = minimax;
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(true);
	    Toolkit toolkit = getToolkit();
	    Dimension size = toolkit.getScreenSize();
	    this.setContentPane(mainPanel = new MainPanel(game, minimax));
	    this.setSize(mainPanel.getWidth(), mainPanel.getHeight() + 40);
	    this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	    
	    addMouseListener(new MouseAdapter() {  //TODO: CHANGE AND ADD BUTTON.  	

			public void mouseClicked(MouseEvent e) {
	    		Main main = Main.this;
	    		
				if(!main.game.isOver()) {
					Point mov = main.minimax.minimax(Main.this.game);
					main.game.play(mov.x, mov.y);	
					main.mainPanel.refresh();
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					if(!main.game.isOver()) {
						mov = main.minimax.minimax(Main.this.game);
						main.game.play(mov.x, mov.y);	
						main.mainPanel.refresh();
					}
				}		
	    	}
		});
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Main mainWindow = new Main(new Game(), new Minimax());
		mainWindow.setVisible(true);
	}

}
