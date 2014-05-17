package model;

import java.util.List;
import model.board.Board;
import model.board.Board.Group;
import model.board.Board.Tile;

public class Game { // TODO: Todo's.
	
	// TODO: REMOVE STATIC VARS (ALL?)
	private static final Tile[] colors = {Tile.RED, Tile.BLUE, Tile.GREEN, Tile.YELLOW, Tile.VIOLET,
		 Tile.PINK, Tile.CYAN, Tile.LIME, Tile.ORANGE}; 
	private static int COLORS = 4;
	private static int ROWS = 6;
	private static int COLS = 6;
	
	private static final double BONUS = 1.3;
	private Board board;
	private int P1Score;
	private int P2Score;
	private boolean P1Turn = true;
	
	public Game() {
		Tile[][] tiles = randomGame(); // TODO: CHANGE FOR FILE READER.
		board = new Board(tiles);
	}
	
	private Game(Board board, int P1Score, int P2Score, boolean turn) {
		this.board = board;
		this.P1Score = P1Score;
		this.P2Score = P2Score;
		this.P1Turn = turn;
	}
	
	private Tile[][] randomGame() {  //TODO: REMOVE METHOD.
		Tile[][] tiles = new Tile[ROWS][COLS];
		
		for( int i = 0; i < ROWS; i++)
			for(int j = 0; j < COLS; j++)
				tiles[i][j] = colors[(int)(Math.random()*COLORS)];
		
		return tiles;
	}
	
	public void play(int x, int y) {
		if( !board.hasAdjacents(x, y) )
			return;
		
		play(board.getGroup(x, y));
	}
	
	public void play(Group group) {
		score(board.play(group));
		changeTurn();
	}
	
	private void score(int tiles){
		int score;
		
		if( tiles <= 5 )
			score = (int)Math.round(Math.pow(2, tiles - 2));
		else
			score = tiles*2;
		
		if( isP1Turn() ){
			P1Score += score;
			if( board.isEmpty() )
				P1Score *= BONUS;
		}else{
			P2Score += score;
			if( board.isEmpty() )
				P2Score *= BONUS;
		}
	}
	
	private void changeTurn(){
		this.P1Turn = !P1Turn;
	}
	
	public boolean isOver() {
		return board.isOver();
	}
	
	public boolean P1Won() {
		return P1Score > P2Score;
	}
	
	public boolean isP1Turn(){
		return P1Turn;
	}
	
	public boolean isP2Turn(){
		return !P1Turn;
	}
	
	public Game clone(){
		return new Game(board.clone(), P1Score, P2Score, P1Turn);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public int getP1Score() {
		return P1Score;
	}

	public int getP2Score() {
		return P2Score;
	}
	
	public Tile getTile(int x, int y) {
		return board.getTile(x, y);
	}
	
	public List<Group> getGroups() {
		return board.getGroups();
	}
	
	public int getLastCol() {
		return board.getLastCol();
	}
	
	public int getLastRow() {
		return board.getLastRow();
	}
	
	public int height() {
		return board.height();
	}
	
	public int width() {
		return board.width();
	}
}