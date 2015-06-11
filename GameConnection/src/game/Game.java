package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game{
	
	GamePanel panel;
	
	public Game() {
		ArrayList<Player> players = new ArrayList<Player>();
		TileMap tileMap = new TileMap("src/game/testmap.txt", 32);
		Player p1 = new Player(tileMap, Color.MAGENTA, true, "Ghani");
		Player p2 = new Player(tileMap, Color.BLUE, false, "Teun");
		Player p3 = new Player(tileMap, Color.GREEN, false, "Yusuf");
		Player p4 = new Player(tileMap, Color.YELLOW, false, "Yusril");
		players.add(p1);
		players.add(p2);
		//players.add(p3);
		//players.add(p4);
		
		panel = new GamePanel(players, tileMap);
		
		JFrame window = new JFrame("Platformer");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(panel);
		window.pack();
		window.setVisible(true);
	}
	
	public GamePanel getPanel(){
		return panel;
	}
}
