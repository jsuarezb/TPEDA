package model;
import java.awt.Point;
import java.util.List;

import model.board.Board;
import model.board.Tile;
import model.board.Tile.Color;
import model.minimax.Group;

public class Game {
	private Board board;
	private int player1Score;
	private int player2Score;
	private int turn;
	private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.VIOLET, Color.ORANGE,
							  Color.GRAY, Color.PINK, Color.BROWN};
	private static int COLORS = 4;
	private static int ROWS = 8;
	private static int COLS = 8;
	
	public Game() {
		Tile[][] tiles = randomGame();
		board = new Board(tiles);
	}
	
	private Tile[][] randomGame(){
		Tile[][] tiles = new Tile[ROWS][COLS];
		for( int i = 0; i < ROWS; i++)
			for(int j = 0; j < COLS; j++){
				int val = (int)(Math.random()*COLORS); 
				tiles[i][j] = new Tile(colors[val]);
			}
		return tiles;
	}

	public Game(Board board, int player1Score, int player2Score, int turn){
		this.board = board;
		this.player1Score = player1Score;
		this.player2Score = player2Score;
		this.turn = turn;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public List<Group> getGroups(){
		return board.getGroups();
	}
	
	public Tile getTile(Point p){
		return board.getTile(p);
	}
	
	public void play(Point tilePos) {
		if( getTile(tilePos).isEmpty() )
			return;
		int tilesDeleted = board.play(tilePos);
		if( tilesDeleted == 0 )
			return;
		score(tilesDeleted);
		turn++;
	}
	
	private void score(int tiles){
		int score;
		if( tiles <= 5 )
			score = (int)Math.round(Math.pow(2, tiles - 2));
		else
			score = tiles*2;
		if( turn % 2 == 0 ){
			player1Score += score;
			if( board.isEmpty() )
				player1Score *= 1.3;
		}else{
			player2Score += score;
			if( board.isEmpty() )
				player2Score *= 1.3;
		}
	}
	
	public boolean isPlayer1Turn(){
		return turn % 2 == 0;
	}
	
	public boolean isPlayer2Turn(){
		return !isPlayer1Turn();
	}
	
	public int getWidthSize(){
		return board.getWidthSize();
	}
	
	public int getHeightSize(){
		return board.getHeightSize();
	}
	
	public boolean isOver() {
		return board.isOver();
	}
	
	public boolean player1Won() {
		return player1Score > player2Score;
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}
	
	public int getLastCol() {
		return board.getLastCol();
	}
	
	public int getLastRow() {
		return board.getLastRow();
	}
	
	public Game clone(){
		return new Game(board.clone(), player1Score, player2Score, turn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result + player1Score;
		result = prime * result + player2Score;
		result = prime * result + turn;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
			return false;
		if (player1Score != other.player1Score)
			return false;
		if (player2Score != other.player2Score)
			return false;
		if (turn != other.turn)
			return false;
		return true;
	}
}