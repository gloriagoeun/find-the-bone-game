package entities;

import java.io.IOException;

import javax.imageio.ImageIO;

public class DownArrow extends SuperObject{
	public DownArrow() {
		name = "Down Arrow"; 
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/downArrow.png"));
		} catch(IOException e) {
			e.printStackTrace(); 
		}
	}
}
