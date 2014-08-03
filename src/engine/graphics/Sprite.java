package engine.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Sprite implements Renderable {
	
	private BufferedImage image;
	private AffineTransform transform;
	private double centerX, centerY;
	
	public Sprite(BufferedImage image) {
		setImage(image);
		this.transform = new AffineTransform();
	}

	public void rotate(double angle) {
		transform.rotate(Math.toRadians(angle), centerX, centerY);
	}
	
	public void translate(double dx, double dy) {
		transform.translate(dx, dy);
	}
	
	public void scale(double sx, double sy) {
		transform.scale(sx, sy);
	}
	
	public void setImage(BufferedImage img) {
		this.image = img;
		centerX = image.getWidth()*0.5;
		centerY = image.getHeight()*0.5;
	}
	
	public double getX() {
		return transform.getTranslateX();
	}
	
	public double getY() {
		return transform.getTranslateY();
	}
	
	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setTransform(transform);
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
	}
	
}
