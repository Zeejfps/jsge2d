package engine.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Canvas extends Component {

	private static final long serialVersionUID = 1L;
	
	private int width, height, scale;
	private BufferedImage buffer;
	private Color fillColor = Color.BLACK;
	
	public Canvas(int width, int height, int scale) {
		
		this.width = width;
		this.height = height;
		this.scale = scale < 1 ? 1 : scale;
		
		buffer = new BufferedImage(width/scale, height/scale, BufferedImage.TYPE_INT_RGB);
		setBackground(fillColor);
		setFocusable(true);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height); 
	}
	
	public int getScale() {
		return scale;
	}
	
	public void clear() {
		Graphics g = buffer.getGraphics();
		g.setColor(fillColor);
		g.fillRect(0, 0, width, height);
		g.dispose();
	}
	
	public void refresh() {
		Graphics g = getGraphics();
		if (g != null) {
			g.drawImage(buffer, 0, 0, getWidth(), getHeight(), null);
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
		
	}
	
	public Graphics getBufferedGraphics() {
		return buffer.getGraphics();
	}
	
}
