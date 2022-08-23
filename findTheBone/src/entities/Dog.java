package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyboardHandler;

public class Dog {
	
	GamePanel gp; 
	KeyboardHandler keyH; 
	
	public int worldX, worldY;
	public int speed;
	public final int screenX, screenY; 
	public int hasBone = 0;
	int standCounter = 0; 
	 	
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; 
	public String direction; 
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public int solidAreaDefaultX, solidAreaDefaultY; 
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
	
	public Dog(GamePanel gp, KeyboardHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		// collision area 
		solidArea = new Rectangle(10, 16, 28, 32); 
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues(); 
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23; 
		worldY = gp.tileSize * 21; 
		speed = 4; 
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/dog/dog_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/dog/dog_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/dog/dog_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/dog/dog_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/dog/dog_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/dog/dog_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/dog/dog_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/dog/dog_right_2.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			
			if (keyH.upPressed == true) {
				direction = "up";
			}
			else if (keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			// check if tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// check if object collision
			int objIndex = gp.cChecker.checkObject(this); 
			pickUpObject(objIndex); 
			
			
			if (!collisionOn) {
				switch (direction) {
				case "up":
					worldY -= speed; 
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break; 
				}
			}
			
			spriteCounter++; 
			
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				
				spriteCounter = 0;
			}
			
		} else {
			standCounter++;
			
			if (standCounter > 15) {
				spriteNum = 1;
				standCounter = 0;
			}			
		}
	}
	
	public void pickUpObject(int i) {
		if (i != -1) {
			String objName = gp.obj[i].name;
			
			switch (objName) {
			case "Bone": 
				gp.playSE(1);
				hasBone++;
				gp.obj[i] = null; 
				gp.ui.showMessage("You got a bone!");
				break;
			case "Up Arrow": 
				gp.playSE(2);
				speed += 2;
				gp.obj[i] = null;
				gp.ui.showMessage("Speed UP!");
				break;
			case "Down Arrow":
				gp.playSE(3);
				speed -= 2;
				gp.obj[i] = null;
				gp.ui.showMessage("Speed DOWN!");
				break;
			case "Home":
				if (hasBone == 5) {
					gp.ui.gameFinished = true;
					gp.stopMusic();
					gp.playSE(4);
				} else {
					gp.playSE(5);
					gp.ui.showMessage("You need "+(5-hasBone)+ " more bones before returning home!");
				}
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			else if (spriteNum == 2) {
				image = up2; 
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			else if (spriteNum == 2) {
				image = down2; 
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			else if (spriteNum == 2) {
				image = left2; 
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			else if (spriteNum == 2) {
				image = right2; 
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
