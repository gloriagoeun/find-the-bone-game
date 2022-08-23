package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import entities.Bone;

public class UserInterface {

	GamePanel gp;
	Font arial30, arial60_B; 
	BufferedImage boneImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	double playTime; 
	DecimalFormat dFormat = new DecimalFormat("#0.00"); 
	
	public UserInterface(GamePanel gp) {
		this.gp = gp; 
		
		arial30 = new Font("Arial", Font.PLAIN, 30);
		arial60_B = new Font("Arial", Font.BOLD, 60);
		Bone bone = new Bone();
		boneImage = bone.image; 
	}
	
	public void showMessage(String text) {
		
		messageOn = true;
		message = text;
	}
	
	public void draw(Graphics2D g2) {
		
		if (gameFinished) {
			
			g2.setFont(arial30);
			g2.setColor(Color.white);
			
			String text = "You returned home with the bones!";
			int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			int x = gp.screenWidth/2 - textLength/2;
			int y = gp.screenHeight/2 - (gp.tileSize*4);
			
			g2.drawString(text, x, y);
			
			text = "Time: " + dFormat.format(playTime) + " sec";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			
			g2.drawString(text, x, y);
			
			g2.setFont(arial60_B);
			g2.setColor(Color.yellow);		
			text = "Woof Woof, Congrats!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*3) - 10;
			
			g2.drawString(text, x, y);
			
			gp.gameThread = null; 
			
			
		} else {
			g2.setFont(arial30);
			g2.setColor(Color.white);
			g2.drawImage(boneImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.dog.hasBone, 74, 65);
			
			playTime += (double) 1/60;
			g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11 + 50, 65); 
			
			if (messageOn) {
				// change font size
				g2.setFont(g2.getFont().deriveFont(20F));
				g2.drawString(message, gp.tileSize/2 + 4, 100);
				
				messageCounter++; 
				
				if (messageCounter > 120) {
					messageOn = false;
					messageCounter = 0;
				}
				
			}
		}
	}
}
