package main;

import entities.Bone;
import entities.DownArrow;
import entities.Home;
import entities.UpArrow;

public class EntitySetter {
	GamePanel gp;
	
	public EntitySetter(GamePanel gp) {
		this.gp = gp; 
	}
	
	public void setObject() {
		gp.obj[0] = new Bone(); 
		gp.obj[0].worldX = 39 * gp.tileSize; 
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new Bone();
		gp.obj[1].worldX = 11 * gp.tileSize; 
		gp.obj[1].worldY = 42 * gp.tileSize;
		
		gp.obj[2] = new Bone();
		gp.obj[2].worldX = 18 * gp.tileSize; 
		gp.obj[2].worldY = 18 * gp.tileSize;
		
		gp.obj[3] = new Bone();
		gp.obj[3].worldX = 38 * gp.tileSize; 
		gp.obj[3].worldY = 37 * gp.tileSize;
		
		gp.obj[4] = new Bone();
		gp.obj[4].worldX = 8 * gp.tileSize; 
		gp.obj[4].worldY = 28 * gp.tileSize;
		
		gp.obj[5] = new UpArrow();
		gp.obj[5].worldX = 35 * gp.tileSize; 
		gp.obj[5].worldY = 8 * gp.tileSize;
		
		gp.obj[6] = new UpArrow();
		gp.obj[6].worldX = 21 * gp.tileSize; 
		gp.obj[6].worldY = 32 * gp.tileSize;
		
		gp.obj[7] = new DownArrow();
		gp.obj[7].worldX = 18 * gp.tileSize; 
		gp.obj[7].worldY = 10 * gp.tileSize;
		
		gp.obj[8] = new DownArrow();
		gp.obj[8].worldX = 35 * gp.tileSize; 
		gp.obj[8].worldY = 27 * gp.tileSize;
		
		gp.obj[8] = new Home();
		gp.obj[8].worldX = 9 * gp.tileSize; 
		gp.obj[8].worldY = 7 * gp.tileSize;
		
	}

}
