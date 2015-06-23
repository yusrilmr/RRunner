package RaceRunnerGameNeat;

import java.awt.*;

import javax.swing.JOptionPane;

public class Player {
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private int width;
	private int height;
	
	private boolean left;
	private boolean right;
	private boolean jumping;
	private boolean falling;
	
	private double moveSpeed;
	private double maxSpeed;
	private double maxFallingSpeed;
	private double stopSpeed;
	private double jumpStart;
	private double gravity;
	
	private TileMap tileMap;
	private Color color;
	private Boolean camera;
	private String name;
	
	private boolean topLeft;
	private boolean topRight;
	private boolean bottomLeft;
	private boolean bottomRight;
	
	public Player(TileMap tm, Color color, Boolean camera, String name){
		this.name = name;
		this.camera = camera;
		this.color=color;
		tileMap =tm;
		
		width = 20;
		height = 20;
		
		moveSpeed = 0.6;
		maxSpeed = 4.2;
		maxFallingSpeed = 12;
		stopSpeed = 0.30;
		jumpStart = -11.0;
		gravity = 0.64;
	}
	
	public void setx(int i){ x = i; }
	public void sety(int i){ y = i; }
	
	public void setLeft(boolean b){ left = b; }
	public void setRight(boolean b){ right = b; }
	public void setJumping(boolean b){
		if (!falling) {
			jumping = true;
		}
	}
	
	private void calculateCorners(double x, double y){
		int leftTile = tileMap.getColTile((int) (x - width / 2));
		int rightTile = tileMap.getColTile((int) (x + width/ 2) - 1);
		int topTile = tileMap.getRowTile((int) (y - height / 2));
		int bottomTile = tileMap.getRowTile((int) (y + height /2) - 1);
		topLeft = tileMap.getTile(topTile, leftTile) == 0;
		topRight = tileMap.getTile(topTile, rightTile) == 0;
		bottomLeft = tileMap.getTile(bottomTile, leftTile) == 0;
		bottomRight = tileMap.getTile(bottomTile, rightTile) == 0;
	}
	
	//////////////////////////////////////
	
	public void update(){
		
		//determine next position
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0){
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}
		
		if (jumping) {
			dy = jumpStart;
			falling = true;
			jumping = false;
		}
		
		if (falling) {
			dy += gravity;
			if (dy > maxFallingSpeed) {
				dy = maxFallingSpeed;
			}
		}
		else {
			dy = 0;
		}
		
		// check collisions
		
		int currCol = tileMap.getColTile((int) x);
		int currRow = tileMap.getRowTile((int) y);
		
		double tox = x + dx;
		double toy = y + dy;
		
		double tempx = x;
		double tempy = y;
		
		calculateCorners(x, toy);
		if(dy < 0){
			if (topLeft || topRight) {
				dy = 0;
				tempy = currRow * tileMap.getTileSize() + height /2;
			}
			else {
				tempy += dy;
			}
		}
		if (dy > 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				tempy = (currRow + 1) * tileMap.getTileSize() - height /2;
			}
			else {
				tempy += dy;
			}
		}
		
		calculateCorners(tox, y);
		if(dx < 0){
			if (topLeft || bottomLeft) {
				dx = 0;
				tempx = currCol * tileMap.getTileSize() + width /2;
			}
			else {
				tempx += dx;
			}
		}
		if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
				tempx = (currCol + 1) * tileMap.getTileSize() - width /2;
			}
			else {
				tempx += dx;
			}
		}
		
		if (!falling) {
			calculateCorners(x, y + 1);
			if (!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
		
		x = tempx;
		y = tempy;
		
		//move the map
		if(camera){
			tileMap.setx((int) (GamePanel.WIDTH / 2 - x));
			tileMap.sety((int) (GamePanel.HEIGHT / 2 - y));
		}
		
		if (x <= 86 && x >= 42 && y == 438){
			Object[] options = {"OK"};
			int n = JOptionPane.showOptionDialog(null,
                    "Congratulations! " + name,
                    "Congratulations!",
                     JOptionPane.OK_OPTION,
                     JOptionPane.QUESTION_MESSAGE,
                     null,     //do not use a custom Icon
                     options,  //the titles of buttons
                     options[0]); //default button title
			
			if(n == JOptionPane.OK_OPTION){
				System.exit(1);
			}else{
				System.exit(1);
			}
		}
	}
	
	public void draw(Graphics2D g){
		int tx = tileMap.getx();
		int ty = tileMap.gety();
		
		g.setColor(color);
		g.fillRect(
			(int) (tx + x - width / 2),	
			(int) (ty + y - height / 2),
			width,
			height
		);
	}
}
