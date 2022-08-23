package entities;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Home extends SuperObject{
	public Home() {
		name = "Home"; 
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/home.png"));
		} catch(IOException e) {
			e.printStackTrace(); 
		}
	}
}