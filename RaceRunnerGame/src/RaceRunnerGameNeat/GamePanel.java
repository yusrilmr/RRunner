package RaceRunnerGameNeat;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.awt.Event.*;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;

	private Thread thread;
	private boolean running;

	private BufferedImage image;
	private Graphics2D g;

	static int interval;
	static Timer timer;

	int tmpInterval = 0;
	private int FPS = 30;
	private int targetTime = 1000 / FPS;

	private TileMap tileMap;
	private Player player;
	private Player player2;
	private Player player3;
	private Player player4;
	
	private int amountPlayers;
	


	public GamePanel(ArrayList<Player> players, TileMap tileMap){
		super();
		
		//free space on bottom
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.GRAY,25));
		
		
		setFocusable(true);
		requestFocus();
		amountPlayers = players.size();
		this.tileMap = tileMap;
		for(int i = 0; i < players.size(); i++){
			switch(i){
			case 0:
				player = players.get(i);
				break;
			case 1:
				player2 = players.get(i);
				break;
			case 2:
				player3 = players.get(i);
				break;
			case 3:
				player4 = players.get(i);
				break;

			}
		}
	}

	private static final int setInterval() {
		if (interval == 1){
			timer.cancel();
		}
		return --interval;
	}

	public void addNotify(){
		super.addNotify();
		if(thread == null){
			//start class TileMap
			thread = new Thread(this);
			thread.start();
			// Game Duration
			//System.out.println("5 Minutes");
			String secs = "300";
			int delay = 1000;
			int period = 1000;
			timer = new Timer();
			interval = Integer.parseInt(secs);
			//System.out.println(secs);
			timer.schedule(new TimerTask() {

				public void run() {
					tmpInterval = setInterval();
					//System.out.println(tmpInterval);
					if(tmpInterval <= 0){
						//if the countdown reaches 0, then
						thread.stop();
					}
				}
			}, delay, period);

		}
		addKeyListener(this);
	}

	public void run(){
		init();

		long startTime;
		long urdTime;
		long waitTime;

		while(running){
			startTime = System.nanoTime();

			update();
			render();
			draw();

			urdTime = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - urdTime;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void init(){
		running = true;

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		
		if(amountPlayers >= 1){
			player.setx(50);
			player.sety(50);
			if(amountPlayers >= 2){
				player2.setx(50);
				player2.sety(50);
				if(amountPlayers >= 3){
					player3.setx(50);
					player3.sety(50);
					if(amountPlayers == 4){
						player4.setx(50);
						player4.sety(50);
					}
				}
			}
		}
	}

	/////////////////////////

	private void update(){

		tileMap.update();
		if(amountPlayers >= 1){
			player.update();
			if(amountPlayers >= 2){
				player2.update();
				if(amountPlayers >= 3){
					player3.update();
					if(amountPlayers == 4){
						player4.update();
					}
				}
			}
		}
	}

	private void render(){

		tileMap.draw(g);
		if(amountPlayers >= 1){
			player.draw(g);
			if(amountPlayers >= 2){
				player2.draw(g);
				if(amountPlayers >= 3){
					player3.draw(g);
					if(amountPlayers == 4){
						player4.draw(g);
					}
				}
			}
		}
	}

	private void draw(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);

		g2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		int code = key.getKeyCode();

		if (code == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		if (code == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if(code == KeyEvent.VK_UP){
			player.setJumping(true);
		}

		if (code == KeyEvent.VK_A) {
			player2.setLeft(true);
		}
		if (code == KeyEvent.VK_D) {
			player2.setRight(true);
		}
		if(code == KeyEvent.VK_W){
			player2.setJumping(true);
		}
		if (code == KeyEvent.VK_F) {
			player3.setLeft(true);
		}
		if (code == KeyEvent.VK_H) {
			player3.setRight(true);
		}
		if(code == KeyEvent.VK_T){
			player3.setJumping(true);
		}
		if (code == KeyEvent.VK_J) {
			player4.setLeft(true);
		}
		if (code == KeyEvent.VK_L) {
			player4.setRight(true);
		}
		if(code == KeyEvent.VK_I){
			player4.setJumping(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();

		if (code == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if (code == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}

		if (code == KeyEvent.VK_A) {
			player2.setLeft(false);
		}
		if (code == KeyEvent.VK_D) {
			player2.setRight(false);
		}
		if (code == KeyEvent.VK_F) {
			player3.setLeft(false);
		}
		if (code == KeyEvent.VK_H) {
			player3.setRight(false);
		}

		if (code == KeyEvent.VK_J) {
			player4.setLeft(false);
		}
		if (code == KeyEvent.VK_L) {
			player4.setRight(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
