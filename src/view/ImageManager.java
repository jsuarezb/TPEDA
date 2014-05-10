package view;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import model.board.Tile;
import model.board.Tile.Color;

public class ImageManager {
	
	private Map<Color, Image> images = new HashMap<Color, Image>();
	
	public ImageManager() {
		initImages();
	}

	public void initImages() {
		try{
			images.put(Color.RED, ImageUtils.loadImage("resources/redTile.png"));
			images.put(Color.BLUE, ImageUtils.loadImage("resources/blueTile.png"));
			images.put(Color.GREEN, ImageUtils.loadImage("resources/greenTile.png"));
			images.put(Color.YELLOW, ImageUtils.loadImage("resources/yellowTile.png"));
			images.put(Color.VIOLET, ImageUtils.loadImage("resources/violetTile.png"));
			images.put(Color.PINK, ImageUtils.loadImage("resources/pinkTile.png"));
			images.put(Color.ORANGE, ImageUtils.loadImage("resources/orangeTile.png"));
			images.put(Color.GRAY, ImageUtils.loadImage("resources/grayTile.png"));
			images.put(Color.BROWN, ImageUtils.loadImage("resources/brownTile.png"));
			images.put(Color.EMPTY, ImageUtils.loadImage("resources/emptyTile.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Image get(Tile tile) {
		return images.get(tile.getColor());		
	}
	
	public Image get(String key) {
		return images.get(key);
	}
}