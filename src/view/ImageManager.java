package view;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import model.board.Board.Tile;

public class ImageManager {
	
	private Map<Tile, Image> images = new HashMap<Tile, Image>();
	
	public ImageManager() {
		initImages();
	}

	private void initImages() {
		try{
			images.put(Tile.RED, ImageUtils.loadImage("resources/redTile.png"));
			images.put(Tile.BLUE, ImageUtils.loadImage("resources/blueTile.png"));
			images.put(Tile.GREEN, ImageUtils.loadImage("resources/greenTile.png"));
			images.put(Tile.YELLOW, ImageUtils.loadImage("resources/yellowTile.png"));
			images.put(Tile.VIOLET, ImageUtils.loadImage("resources/violetTile.png"));
			images.put(Tile.PINK, ImageUtils.loadImage("resources/pinkTile.png"));
			images.put(Tile.CYAN, ImageUtils.loadImage("resources/cyanTile.png"));
			images.put(Tile.LIME, ImageUtils.loadImage("resources/limeTile.png"));
			images.put(Tile.ORANGE, ImageUtils.loadImage("resources/orangeTile.png"));
			images.put(Tile.EMPTY, ImageUtils.loadImage("resources/emptyTile.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Image get(Tile tile) {
		return images.get(tile);		
	}
	
	public Image get(String key) {
		return images.get(key);
	}
}