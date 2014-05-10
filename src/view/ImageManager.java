package view;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import model.board.BlueTile;
import model.board.BrownTile;
import model.board.EmptyTile;
import model.board.GrayTile;
import model.board.GreenTile;
import model.board.OrangeTile;
import model.board.PinkTile;
import model.board.RedTile;
import model.board.Tile;
import model.board.VioletTile;
import model.board.YellowTile;

public class ImageManager {
	
	private Map<String, Image> images = new HashMap<String, Image>();
	
	public ImageManager() {
		initImages();
	}

	public void initImages() {
		try{
			images.put(RedTile.class.getName(), ImageUtils.loadImage("resources/redTile.png"));
			images.put(BlueTile.class.getName(), ImageUtils.loadImage("resources/blueTile.png"));
			images.put(GreenTile.class.getName(), ImageUtils.loadImage("resources/greenTile.png"));
			images.put(YellowTile.class.getName(), ImageUtils.loadImage("resources/yellowTile.png"));
			images.put(VioletTile.class.getName(), ImageUtils.loadImage("resources/violetTile.png"));
			images.put(PinkTile.class.getName(), ImageUtils.loadImage("resources/pinkTile.png"));
			images.put(OrangeTile.class.getName(), ImageUtils.loadImage("resources/orangeTile.png"));
			images.put(GrayTile.class.getName(), ImageUtils.loadImage("resources/grayTile.png"));
			images.put(BrownTile.class.getName(), ImageUtils.loadImage("resources/brownTile.png"));
			images.put(EmptyTile.class.getName(), ImageUtils.loadImage("resources/emptyTile.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Image get(Tile tile) {
		return images.get(tile.getClass().getName());		
	}
	
	public Image get(String key) {
		return images.get(key);
	}
}