package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game{
	
	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		TileMap tileMap = new TileMap("/Users/ghanimalik/Documents/testmap.txt", 32);
		Player p1 = new Player(tileMap, Color.MAGENTA, false, "Ghani");
		Player p2 = new Player(tileMap, Color.BLUE, false, "Teun");
		Player p3 = new Player(tileMap, Color.GREEN, true, "Yusuf");
		Player p4 = new Player(tileMap, Color.YELLOW, false, "Yusril");
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		
		JFrame window = new JFrame("Platformer");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanel(players, tileMap));
		window.pack();
		window.setVisible(true);
	}
}
