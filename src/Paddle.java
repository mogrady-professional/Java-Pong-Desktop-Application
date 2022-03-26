
import java.awt.*;
import java.awt.event.*;

/**
 * Constructor for Paddle
 * @author Michael
 *
 */
public class Paddle extends Rectangle{
//Instance variables
	int id; // Either 1 or 2 -> 2 players paddle 1 / paddle 2
	int yVelocity; // How fast the paddle moves on button press
	int speed = 10; // Move by 10 pixels at each key press
	
	/**
	 * Define paddle constructor
	 * @param x
	 * @param y
	 * @param PADDLE_WIDTH
	 * @param PADDLE_HEIGHT
	 * @param id
	 */
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
//		super constructor from rectangle class
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}
	
	/**
	 * Key Press Event -> Called from Game Panel -> paddle id case method
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
//				Move up on Y axis on paddle 1
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
//				Move down on Y axis on paddle 1
				setYDirection(speed);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
//				Move up on Y axis on paddle 2
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
//				Move down on Y axis on paddle 2
				setYDirection(speed);
			}
			break;
		}
	}
	
	/**
	 * Key Release Event -> Called from Game Panel
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
//				Stop moving 
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
//				Stop moving 
				setYDirection(0);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
//				Stop moving 
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
//				Stop moving 
				setYDirection(0);
			}
			break;
		}
	}
	
	/**
	 * 
	 * @param yDirection
	 */
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	/**
	 * 
	 */
	public void move() {
		y= y + yVelocity;
	}
	/**
	 * Players -> Color
	 * @param g
	 */
	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
}