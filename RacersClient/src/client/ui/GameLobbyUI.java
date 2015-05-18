package client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameLobbyUI extends JFrame {
	JLabel LabelNamePlayer1;
	JLabel LabelNamePlayer2;
	JLabel LabelNamePlayer3;
	JLabel LabelNamePlayer4;
	JLabel LabelImagePlayer1;
	JLabel LabelImagePlayer2;
	JLabel LabelImagePlayer3;
	JLabel LabelImagePlayer4;
	JLabel LabelRolePlayer1;
	JLabel LabelRolePlayer2;
	JLabel LabelRolePlayer3;
	JLabel LabelRolePlayer4;
	JLabel LabelBackgroundPlayer1;
	JLabel LabelBackgroundPlayer2;
	JLabel LabelBackgroundPlayer3;
	JLabel LabelBackgroundPlayer4;
	JButton ButtonLeave;
	JButton ButtonStart;
	JButton ButtonRefresh;
	
	UIHandler uiHandler;

	

	public GameLobbyUI(UIHandler uiHandler) {
		setTitle("Race Runner - Game Lobby");
		setSize(1000, 500);
		getContentPane().setLayout(null);
		
		this.uiHandler = uiHandler;
		this.addWindowListener(uiHandler);
		
		LabelNamePlayer1 = new JLabel("");
		LabelNamePlayer1.setBounds(190, 100, 150, 200);
		LabelNamePlayer1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		LabelNamePlayer2 = new JLabel("");
		LabelNamePlayer2.setBounds(390, 100, 150, 200);
		LabelNamePlayer2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		LabelNamePlayer3 = new JLabel("");
		LabelNamePlayer3.setBounds(590, 100, 150, 200);
		LabelNamePlayer3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		LabelNamePlayer4 = new JLabel("");
		LabelNamePlayer4.setBounds(790, 100, 150, 200);
		LabelNamePlayer4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		LabelRolePlayer1 = new JLabel("Host");
		LabelRolePlayer1.setBounds(195, 40, 100, 100);
		LabelRolePlayer2 = new JLabel("Guest");
		LabelRolePlayer2.setBounds(390, 40, 100, 100);
		LabelRolePlayer3 = new JLabel("Guest");
		LabelRolePlayer3.setBounds(590, 40, 100, 100);
		LabelRolePlayer4 = new JLabel("Guest");
		LabelRolePlayer4.setBounds(790, 40, 100, 100);
		
		LabelBackgroundPlayer1 = new JLabel();
		LabelBackgroundPlayer1.setBounds(130, 100, 150, 200);
		LabelBackgroundPlayer1.setOpaque(true);
		LabelBackgroundPlayer1.setBackground(Color.white);
		
		LabelBackgroundPlayer2 = new JLabel();
		LabelBackgroundPlayer2.setBounds(330, 100, 150, 200);
		LabelBackgroundPlayer2.setOpaque(true);
		LabelBackgroundPlayer2.setBackground(Color.white);
		
		LabelBackgroundPlayer3 = new JLabel();
		LabelBackgroundPlayer3.setBounds(530, 100, 150, 200);
		LabelBackgroundPlayer3.setOpaque(true);
		LabelBackgroundPlayer3.setBackground(Color.white);
		
		LabelBackgroundPlayer4 = new JLabel();
		LabelBackgroundPlayer4.setBounds(730, 100, 150, 200);
		LabelBackgroundPlayer4.setOpaque(true);
		LabelBackgroundPlayer4.setBackground(Color.white);

		ButtonStart = new JButton("Start");
		ButtonStart.setBounds(5, 420, 100, 35);
		ButtonStart.addActionListener(uiHandler);
		
		ButtonRefresh = new JButton("Refresh");
		ButtonRefresh.setBounds(440, 420, 100, 35);
		ButtonRefresh.addActionListener(uiHandler);
		
		ButtonLeave = new JButton("Leave");
		ButtonLeave.setBounds(880, 420, 100, 35);
		ButtonLeave.addActionListener(uiHandler);
		
		getContentPane().add(ButtonStart);
		getContentPane().add(ButtonLeave);
		getContentPane().add(ButtonRefresh);
		getContentPane().add(LabelNamePlayer1);
		getContentPane().add(LabelNamePlayer2);
		getContentPane().add(LabelNamePlayer3);
		getContentPane().add(LabelNamePlayer4);
		getContentPane().add(LabelRolePlayer1);
		getContentPane().add(LabelRolePlayer2);
		getContentPane().add(LabelRolePlayer3);
		getContentPane().add(LabelRolePlayer4);
		getContentPane().add(LabelBackgroundPlayer1);
		getContentPane().add(LabelBackgroundPlayer2);
		getContentPane().add(LabelBackgroundPlayer3);
		getContentPane().add(LabelBackgroundPlayer4);
		
		
		setLocationRelativeTo(null);
	}
	
	public void setPlayerNames(String p1, String p2, String p3, String p4){
		LabelNamePlayer1.setText(p1);
		LabelNamePlayer2.setText(p2);
		LabelNamePlayer3.setText(p3);
		LabelNamePlayer4.setText(p4);
	}
	
	public void setButton(String s){
		ButtonStart.setText(s);
	}
}
