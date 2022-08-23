package entities;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Bone extends SuperObject{
	public Bone() {
		name = "Bone"; 
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/bone.png"));
		} catch(IOException e) {
			e.printStackTrace(); 
		}
	}
}