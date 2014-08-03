package engine;

import java.awt.Graphics;

import engine.graphics.Canvas;

public abstract class Game {

	protected final Canvas canvas;
	
	private int fps = 60;
	private Thread gameThread;
	private GameLoop gameLoop;
	private volatile boolean running = false;

	public Game(int width, int height, int scale, int fps) {
		
		this.fps = fps;
		canvas = new Canvas(width, height, scale);
		gameLoop = new GameLoop();
	}
	
	public void run() {
		
		if (gameThread == null || !running) {
			
			gameThread = new Thread(gameLoop);
			gameThread.start();
			running = true;
		}
		
	}
	
	public void exit() {
		
		if (running) {
			
			running = false;
			
		}
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	protected abstract void init();
	
	protected abstract void update();
	
	protected abstract void render(Graphics g);
	
	private class GameLoop implements Runnable {

		private static final double NS_PER_SEC = 1000000000.0;
		
		@Override
		public void run() {
			
			double nextTime, maxTimeDiff, currTime, delta;
			int skippedFrames = 0, maxSkippedFrames = 5;
			
			nextTime = (double)System.nanoTime() / NS_PER_SEC;
			maxTimeDiff = 0.5;
			delta = 1.0 / (double)fps;

			init();
			while (running) {
				
				currTime = (double)System.nanoTime() / NS_PER_SEC;
				if ((currTime - nextTime) > maxTimeDiff)
					nextTime = currTime;
				if (currTime >= nextTime) {
					
					nextTime += delta;
					update();
					if ((currTime < nextTime) || (skippedFrames > maxSkippedFrames)) {
						
						render(canvas.getBufferedGraphics());
						skippedFrames = 0;
						
					} else {
						
						skippedFrames++;
						
					}
					
				} else {
					
					int sleepTime = (int)(1000.0 * (nextTime - currTime));
					if (sleepTime > 0) {
						
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) { }
						
					}
					
				}
				
			}
			
		}
		
	}
	
}
