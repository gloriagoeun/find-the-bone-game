package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame(); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Find the 5 bones and return home!");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel); 
		
		// window size to be size of the gamePanel
		window.pack();
		
		// keeps at the center 
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setUpGame();
		gamePanel.startGameThread();
	}
}
