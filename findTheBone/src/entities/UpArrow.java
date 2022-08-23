package entities;

import java.io.IOException;

import javax.imageio.ImageIO;

public class UpArrow extends SuperObject{
	public UpArrow() {
		name = "Up Arrow"; 
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/upArrow.png"));
		} catch(IOException e) {
			e.printStackTrace(); 
		}
	}
}