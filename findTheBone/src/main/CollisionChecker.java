package main;

import entities.Dog;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Dog dog) {
		
		int dogLeftWorldX = dog.worldX + dog.solidArea.x;
		int dogRightWorldX = dog.worldX + dog.solidArea.x + dog.solidArea.width; 
		int dogTopWorldY = dog.worldY + dog.solidArea.y;
		int dogBottomWorldY = dog.worldY + dog.solidArea.y + dog.solidArea.height; 
		
		int dogLeftCol = dogLeftWorldX / gp.tileSize;
		int dogRightCol = dogRightWorldX / gp.tileSize;
		int dogTopRow = dogTopWorldY / gp.tileSize;
		int dogBottomRow = dogBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2; 
		
		switch(dog.direction) {
		case "up":
			dogTopRow = (dogTopWorldY - dog.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[dogLeftCol][dogTopRow];
			tileNum2 = gp.tileM.mapTileNum[dogRightCol][dogTopRow];
			
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				dog.collisionOn = true;
			}
			
			break;
		case "down":
			dogBottomRow = (dogBottomWorldY + dog.speed) / gp.tileSize; 
			tileNum1 = gp.tileM.mapTileNum[dogLeftCol][dogBottomRow];
			tileNum2 = gp.tileM.mapTileNum[dogRightCol][dogBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				dog.collisionOn = true;
			}
			
			break;
		case "left":
			dogLeftCol = (dogLeftWorldX - dog.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[dogLeftCol][dogTopRow];
			tileNum2 = gp.tileM.mapTileNum[dogLeftCol][dogBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				dog.collisionOn = true;
			}
			
			break;
		case "right":
			dogRightCol = (dogRightWorldX + dog.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[dogRightCol][dogTopRow];
			tileNum2 = gp.tileM.mapTileNum[dogRightCol][dogBottomRow];
			
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				dog.collisionOn = true;
			}
			
			break;
		}
	}
	
	public int checkObject(Dog dog) {
		int index = -1; 
		
		for(int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				
				dog.solidArea.x = dog.worldX + dog.solidArea.x;
				dog.solidArea.y = dog.worldY + dog.solidArea.y;
				
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(dog.direction) {
				case "up":
					dog.solidArea.y -= dog.speed;
					if (dog.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision) {
							dog.collisionOn = true;
						}
						
						index = i; 
					}
					break;
				case "down":
					dog.solidArea.y += dog.speed;
					if (dog.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision) {
							dog.collisionOn = true;
						}

						index = i; 
					}
					break;
				case "left":
					dog.solidArea.x -= dog.speed;
					if (dog.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision) {
							dog.collisionOn = true;
						}
						index = i; 					
					}
					break;
				case "right":
					dog.solidArea.x += dog.speed;
					if (dog.solidArea.intersects(gp.obj[i].solidArea)) {
						if (gp.obj[i].collision) {
							dog.collisionOn = true;
						}
						index = i; 
					}
					break;
				}
				
				dog.solidArea.x = dog.solidAreaDefaultX;
				dog.solidArea.y = dog.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}			
		}
		return index; 
	}
}
