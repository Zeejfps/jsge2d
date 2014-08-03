package examples;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import engine.Game;
import engine.graphics.Sprite;
import engine.graphics.SpriteSheet;

public class TestGame extends Game {

	final static int WIDTH = 800, HEIGHT = 600, SCALE = 2;
	final static SpriteSheet SPRITE_SHEET = SpriteSheet.create(TestGame.class.getResource("ghostSheet.png"), 175, 175);
	
	private Sprite sprite;
	
	public TestGame() {
		super(WIDTH, HEIGHT, SCALE, 60);
	}

	@Override
	protected void init() {

		sprite = SPRITE_SHEET.getSprite(0, 0, 175, 175);
		sprite.translate(150, 0);
		
	}

	long time = System.currentTimeMillis();
	
	@Override
	protected void update() {
		
		if (System.currentTimeMillis() - time >= 33) {
			
			sprite.rotate(10);
			time = System.currentTimeMillis();
			
		}
	}

	@Override
	protected void render(Graphics g) {
		canvas.clear();
		sprite.render(g);
		canvas.refresh();
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				Game testGame = new TestGame();
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);
				frame.add(testGame.getCanvas());
				frame.pack();
				frame.setVisible(true);
				testGame.run();
			}
		});
	}
	
}
