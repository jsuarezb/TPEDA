package view;

import java.io.FileNotFoundException;
import model.Game;
import model.minimax.Minimax;
import model.minimax.Node;
import model.minimax.NonValidPlayException;
import model.utils.FileFactory;
import model.utils.GameFactory;

public class TestMain {

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

		for( int size = 4; size <= 5; size++ )  {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 15; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = false;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 6; size <= 6; size++ )  {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 7; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = false;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 7; size <= 7; size++ )  {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 6; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = false;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 8; size <= 9; size++ )  {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 5; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = false;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 10; size <= 12; size++ )        {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 4; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = false;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 13; size <= 15; size++ )        {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 3; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = false;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 4; size <= 5; size++ )  {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 15; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = true;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 6; size <= 6; size++ )  {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 20; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = true;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 7; size <= 7; size++ )  {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 8; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = true;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 8; size <= 9; size++ )  {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 7; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = true;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 10; size <= 12; size++ )        {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 6; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = true;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
		
		for( int size = 13; size <= 15; size++ )        {
			for( int colors = 2; colors <= 9; colors++ ) { 
				for( int depth = 1; depth <= 5; depth++ ) {
					long timeAvg = 0;
					for( int i = 0; i < 500; i++ ) {
						(new FileFactory(size, size, colors, 0, 0)).getRandomFile();
						Game game = (new GameFactory("randomGame.txt")).getGame();             

						TestMain mainWindow = new TestMain(game, new Minimax(depth, 0, false));

						Node.prune = true;

						long timeIni = System.nanoTime();
						mainWindow.consolePlay();
						long timeEnd = System.nanoTime();
						timeAvg += timeEnd - timeIni;
					}
					timeAvg = timeAvg/500;
					System.out.println(size + "," + colors + "," + depth + "," + timeAvg);
				}
			}
		}
	}
}