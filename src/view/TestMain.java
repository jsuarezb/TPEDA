package view;

import java.io.FileNotFoundException;

import model.Game;
import model.minimax.Minimax;
import model.minimax.Node;
import model.minimax.NonValidPlayException;
import model.utils.FileFactory;
import model.utils.GameFactory;

public class TestMain {

	private static final boolean PRUNE = true;
	public static boolean P2ISMAX = false;
	
	private Game game;
	private Minimax minimax;

	public TestMain(Game game, Minimax minimax) {
		super();
		this.game = game;
		this.minimax = minimax;
	}
	
	public void consolePlay() {
		try {
			minimax.minimaxByConsole(game);
		}catch( NonValidPlayException e ) {
			if( game.isOver() )
				return;
		}
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, FileNotFoundException {
		
		for( int depth = 8; depth <= 10; depth++ ) {
			for( int size = 7; size <= 10; size++ )	{
				for( int colors = 3; colors <= 6; colors++ ) {			
					long timeAvg = 0;
					for( int i = 0; i < 100; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();		
						
						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));
						
						if( PRUNE )
							Node.prune = true;
						
						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/1000;
					System.out.println("size: " + size + " colors: " + colors + " depth: " + depth + "time: " + timeAvg);
				}
			}
		}
	}
}
