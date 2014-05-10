package view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JPanel;
import model.Game;
import model.board.Tile;
import view.ImageManager;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 2L;

	private Game game;
	private ImageManager imgManager;
	private int rows, columns;
	private int cellSize;
	private Color color;
	private Image[][] images;


	public GamePanel(Game game, ImageManager imgManager, final int cellSize, Color color) {
		this.game = game;
		this.imgManager = imgManager;
		this.rows = game.getWidthSize();
		this.columns = game.getHeightSize();
		this.images = new Image[rows][columns];
		this.cellSize = cellSize;
		this.color = color;
		setSize(columns * cellSize, rows * cellSize);
	}

	public void put(Image image, int row, int column) {
		images[row][column] = image;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(color);
		g.fillRect(0, 0, columns * cellSize, rows * cellSize);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (images[i][j] != null) {
					g.drawImage(images[i][j], j * cellSize, i * cellSize, null);
				}
			}
		}
	}
	
	public void refresh() {
		for(int i = 0; i < game.getWidthSize(); i++){
			for(int j = 0; j < game.getHeightSize(); j++) {
				Tile tile = game.getTile(new Point(i,j));
				put(imgManager.get(tile), i, j);
			}
		}
		repaint();
	}

	public Point getTilePosition(int x, int y) {
		int tileX = (int) Math.ceil(x/cellSize);
		int tileY =(int) Math.ceil(y/cellSize);
		return new Point(tileX, tileY);
	}
}
