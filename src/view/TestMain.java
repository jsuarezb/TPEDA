package view;

import java.io.FileNotFoundException;
import model.Game;
import model.minimax.Minimax;
import model.minimax.NonValidPlayException;

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

		/*TestWriter writer = new TestWriter("extremeTest5.csv");
		
		try {
			for( int size = 5; size <= 5; size++ ) {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 9; depth <= 20; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 5 depth UL");
		}
		
		writer.close();
		/*writer = new TestWriter("extremeTest1.csv");
		
		try {
			for( int size = 6; size <= 6; size++ )  {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 7; depth <= 7; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 6 depth 7");
		}
			
		writer.close();
		writer = new TestWriter("extremeTest2.csv");
		
		try {
			for( int size = 7; size <= 7; size++ )  {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 6; depth <= 6; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 7 depth 6");
		}
			
		writer.close();
		writer = new TestWriter("extremeTest3.csv");
		
		try {
			for( int size = 8; size <= 9; size++ )  {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 5; depth <= 5; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 8,9 depth 5");
		}
		
		writer.close();
		writer = new TestWriter("extremeTest4.csv");
		
		try {
			for( int size = 10; size <= 12; size++ )        {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 4; depth <= 4; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 10-12 depth 4");
		}
		
		writer.close();
		writer = new TestWriter("extremeTest6.csv");
		
		try {
			for( int size = 6; size <= 6; size++ ) {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 9; depth <= 20; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 6 depth UL");
		}
		
		writer.close();
		writer = new TestWriter("extremeTest7.csv");
		
		try {
			for( int size = 7; size <= 7; size++ ) {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 8; depth <= 8; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 7 depth 8");
		}
		
		writer.close();
		writer = new TestWriter("extremeTest8.csv");
		
		try {
			for( int size = 8; size <= 9; size++ ) {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 7; depth <= 7; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 8-9 depth 7");
		}
		
		writer.close();
		writer = new TestWriter("extremeTest9.csv");
		
		try {
			for( int size = 10; size <= 12; size++ ) {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 6; depth <= 6; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 10-12 depth 6");
		}
		
		writer.close();
		writer = new TestWriter("extremeTest10.csv");
		
		try {
			for( int size = 13; size <= 15; size++ ) {
				for( int colors = 2; colors <= 9; colors++ ) { 
					for( int depth = 5; depth <= 5; depth++ ) {
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
						writer.write(size, colors, depth, timeAvg);
					}
				}
			}
		} catch( OutOfMemoryError e ) {
			System.out.println("size 13-15 depth 5");
		}
		
		writer.close();
		
		
		
		int[] maxDepth = new int[16];
		for( int k = 0; k < maxDepth.length; k++ )
			maxDepth[k] = 100;
		
		writer = new TestWriter("full.csv");
		
		for( int depth = 1; depth <= 20; depth++ ) {
			for( int size = 4; size <= 15 && depth < maxDepth[size - 1] && depth < maxDepth[size]; size++ )  {
				try {
					for( int colors = 2; colors <= 9; colors++ ) { 					
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
						writer.write(size, colors, depth, timeAvg);
					}
				} catch( OutOfMemoryError e ) {
					maxDepth[size] = depth;
				}
			}
		}
		
		writer.close();	*/		
	}
}