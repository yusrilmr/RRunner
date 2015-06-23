package RaceRunnerGameNeat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.TimerTask;

public class Game{
	public static void main(String[] args) {
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		String timer = "300";
		TileMap tileMap = new TileMap("src/assets/testmap1.txt", 32);
		//TileMap tileMap = new TileMap("D:/testmap1.txt", 32);
		Player p1 = new Player(tileMap, Color.MAGENTA, false, "Ghani");
		Player p2 = new Player(tileMap, Color.BLUE, false, "Teun");
		Player p3 = new Player(tileMap, Color.GREEN, false, "Yusuf");
		Player p4 = new Player(tileMap, Color.YELLOW, true, "Yusril");
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		
		JFrame window = new JFrame("Platformer");
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanelFeature(players, tileMap, timer));
		window.pack();
		window.setVisible(true);
	}
}
