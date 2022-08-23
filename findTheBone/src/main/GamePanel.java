package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Dog;
import entities.SuperObject;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// screen settings
	final int originalTileSize = 16; // 16x16 pixel size
	final int scale = 3; // to scale the tile size
	
	public final int tileSize = originalTileSize * scale; // 48x48 pixel tile
	public final int maxScreenCol = 16; 
	public final int maxScreenRow = 12; 
	public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels 
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// world settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	int FPS = 60;
	
	// system setup 
	public TileManager tileM = new TileManager(this);
	KeyboardHandler keyH = new KeyboardHandler(); 
	Sound music = new Sound(); 
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this); 
	public EntitySetter aSetter = new EntitySetter(this); 
	public UserInterface ui = new UserInterface(this);  
	Thread gameThread; 
	
	// ENTITY & OBJECTS 
	public Dog dog = new Dog(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		// to improve game rendering performance
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		// "focused" to receive key input 
		this.setFocusable(true);
	}
	
	public void setUpGame() {
		aSetter.setObject(); 
		playMusic(0);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this); 
		gameThread.start();
	}

	@Override
	/*
	 * making our Game Loop
	 */
	public void run() {

		// divide 1 second with FPS (1 million nanosecond)
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval; 
		 
		while (gameThread != null) {
			
			// UPDATE info such as the character positions
			update(); 
			
			// DRAW the screen with the updated info (calls the paintComponent method)
			repaint();
						
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				// to convert into milliseconds for sleep method
				remainingTime = remainingTime/1000000;
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval; 
				
			} catch (InterruptedException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public void update() {
		
		dog.update(); 
		
	}
	
	
	public void paintComponent(Graphics g) {
		// uses JPanel's inherited method
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g; 
		
		tileM.draw(g2);
		
		// draw OBJECTS
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		dog.draw(g2); 
		
		ui.draw(g2);
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play(); 
	}
	
}
