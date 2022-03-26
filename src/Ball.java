
import java.awt.*;
import java.util.*;

/**
 * Constructor for Ball
 * @author Michael
 *
 */
public class Ball extends Rectangle{

	Random random;
	int xVelocity; // How fast to move on x axis
	int yVelocity; // How fast ball moves on y axis
	int initialSpeed = 2; // starting speed
	
	/**
	 * Positioning of Ball and Velocity
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
//		Random position for ball direction X
		random = new Random();
		int randomXDirection = random.nextInt(2); // 0 -> left 1 -> right 
		if(randomXDirection == 0)
			randomXDirection--; // -1
		setXDirection(randomXDirection*initialSpeed); // random direction
//		Random position for ball direction Y
		int randomYDirection = random.nextInt(2); // 0 -> left 1 -> right 
		if(randomYDirection == 0)
			randomYDirection--; // random direction
		setYDirection(randomYDirection*initialSpeed);
		
	}
	
	/**
	 * Create new ball in random X axis direction position
	 * @param randomXDirection
	 */
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	/**
	 * Create new ball in random Y axis direction position
	 * @param randomYDirection
	 */
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	/**
	 * Velocity for movement iteration
	 */
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	/**
	 * Ball
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}