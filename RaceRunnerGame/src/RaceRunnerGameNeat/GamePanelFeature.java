package RaceRunnerGameNeat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GamePanelFeature extends JPanel{
	GameTiming gt;
	JLabel labelTimer;
	JButton buttonExit;

	public GamePanelFeature(ArrayList<Player> players, TileMap tileMap, String timer){
		Exit ex = new Exit();
		
		buttonExit = new JButton("Back to Lobby");
		buttonExit.setBounds(210, 75, 75, 50);
		buttonExit.addActionListener(ex);
		
		labelTimer = new JLabel("Time Left: "+timer);
		labelTimer.setForeground(Color.white);
		labelTimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		gt = new GameTiming(labelTimer);
		gt.setTimer(timer);
		gt.runTimer();
		
		setBackground(Color.DARK_GRAY);
		setMinimumSize(new Dimension(400, 470));
		setPreferredSize(new Dimension(400, 470));
		setMaximumSize(new Dimension(400, 470));
		
		add(labelTimer, BorderLayout.LINE_START);
		add(new GamePanel(players, tileMap), BorderLayout.LINE_START);
		add(buttonExit, BorderLayout.LINE_END);
	}
	
	class Exit implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==buttonExit){
				//JOptionPane.showConfirmDialog(null, "Are you sure?");
				System.out.println("Exit");
				}
			}
		}
}
