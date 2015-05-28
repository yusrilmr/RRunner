package game;

import javax.swing.JFrame;

public class Game{
	GamePanel panel;
	
	public Game(){
		JFrame window = new JFrame("Platformer");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new GamePanel();
		window.setContentPane(panel);
		window.pack();
		window.setVisible(true);
	}
	
	public GamePanel getPanel(){
		return panel;
	}
}
