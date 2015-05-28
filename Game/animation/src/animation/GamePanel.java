package animation;

import javax.swing.JPanel;

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
	private Player2 player2;
	
	
	
	public GamePanel(){
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		
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
				System.out.println("5 Minutes");
				String secs = "300";
			    int delay = 1000;
			    int period = 1000;
			    timer = new Timer();
			    interval = Integer.parseInt(secs);
			    System.out.println(secs);
			    timer.schedule(new TimerTask() {

			        public void run() {
			        	tmpInterval = setInterval();
			            System.out.println(tmpInterval);
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
		
		tileMap = new TileMap("/Users/ghanimalik/Documents/testmap.txt", 32);
		player = new Player(tileMap);
		player2 = new Player2(tileMap);
		player.setx(50);
		player2.setx(50);
		player.sety(50);
		player2.sety(50);
	}
	
	/////////////////////////
	
	private void update(){
		
		tileMap.update();
		player.update();
		player2.update();
	}
	
	private void render(){
		
		tileMap.draw(g);
		player.draw(g);
		player2.draw(g);
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
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
