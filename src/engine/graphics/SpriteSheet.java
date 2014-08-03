package engine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage image;
	private final int gridWidth, gridHeight;
	
	private SpriteSheet(BufferedImage img, int gridWidth, int gridHeight) {
		this.image = img;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
	}
	
	public Sprite getSprite(int xPos, int yPos, int width, int height) {
		
		int x = xPos * gridWidth;
		int y = yPos * gridHeight;
		
		Sprite result = new Sprite(image.getSubimage(x, y, width, height));
		return result;
	} 
	
	public Sprite getSprite(int xPos, int yPos) {
		return getSprite(xPos, yPos, gridWidth, gridHeight);
	}
	
	public int getGridWidth() {
		return gridWidth;
	}
	
	public int getGridHeight() {
		return gridHeight;
	}
	
	public static SpriteSheet create(URL spriteSheetUrl, int gridWidth, int gridHeight) {
		
		try {
			
			BufferedImage img = ImageIO.read(spriteSheetUrl);
			return new SpriteSheet(img, gridWidth, gridHeight);
			
		} catch (Exception e) {
			System.err.println("Could not load Sprite Sheet!");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static SpriteSheet create(File spriteSheetFile, int gridWidth, int gridHeight) {
		
		try {
			
			BufferedImage img = ImageIO.read(spriteSheetFile);
			return new SpriteSheet(img, gridWidth, gridHeight);
			
		} catch (Exception e) {
			System.err.println("Could not load Sprite Sheet!");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static SpriteSheet create(String spriteSheetDir, int gridWidth, int gridHeight) {
		return create(new File(spriteSheetDir), gridWidth, gridHeight);
	}
	
}
