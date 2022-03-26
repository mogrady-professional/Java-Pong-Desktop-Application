
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * JPanel Runnable on Thread by using Runnable interface
 * 
 * @author Michael
 *
 */
public class GamePanel extends JPanel implements Runnable {
	/**
	 * One instance of game static final
	 */

	/**
	 * Dimensions ratio of a Ping Pong Table: W: 152.5cm L: 274cm
	 * 
	 */
//	Measurements in pixels
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555)); // Auto adjust to ratio
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;

//	Declare instances
	Thread gameThread; // Thread for runnable interface
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;

	/**
	 * Instantiate objects with Constructor
	 */
	GamePanel() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true); // focus key press to read keys
		this.addKeyListener(new AL()); // Listen for keystrokes -> key adaptor class
		this.setPreferredSize(SCREEN_SIZE); // pass in dimension
//		Threading with runnable interface
		gameThread = new Thread(this);
		gameThread.start(); // start thread
	}

	/**
	 * Generate Ball on Screen
	 */
	public void newBall() {
		random = new Random();
//		Start ball in center of window -> X positioning - Ball Diameter = X axis 
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER);
	}

	/**
	 * Generate Paddles on Screen
	 */
	public void newPaddles() {
//		Define paddle position on the X axis & unique id
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
//		Define paddle position on the X axis & unique id
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	/**
	 * Populate Screen
	 */
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics(); // create graphic
		draw(graphics);
		g.drawImage(image, 0, 0, this); // draw image, coordinates (top left), game panel.
	}

	/**
	 * Draw components -> passing in graphics on JFrame
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		Toolkit.getDefaultToolkit().sync();

	}

	/**
	 * Movement Iteration for paddles and ball
	 */
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}

	/**
	 * Determine collision -> stops panels at window edges -> ball bounce 
	 */
	public void checkCollision() {

		// bounce ball off top & bottom window edges
		if (ball.y <= 0) {
//			Move in opposite direction -> reverse x velocity
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
//			Move in opposite direction -> reverse y velocity
			ball.setYDirection(-ball.yVelocity);
		}
		// bounce ball off paddles ->  inherits intersects rectangle class'
		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; // increase speed for more difficulty
			if (ball.yVelocity > 0)
				ball.yVelocity++; // increase upwards X velocity speed for more difficulty
			else
				ball.yVelocity--; // increase downwards Y velocity speed for more difficulty
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; // increase speed for more difficulty
			if (ball.yVelocity > 0)
				ball.yVelocity++; // increase speed for more difficulty
			else
				ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		// stop paddles at window edges
		if (paddle1.y <= 0)
			paddle1.y = 0;
//		Boundaries for paddle 1
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
//		Boundaries for paddle 2
		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		// give a player 1 point and create new paddles & ball
		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
			System.out.println("Player 2: " + score.player2); // Player 2 Score
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			System.out.println("Player 1: " + score.player1); // Player 1 Score
		}
	}

	/*
	 * 
	 * Run Application
	 */
	public void run() {
		// basic game loop
		long lastTime = System.nanoTime(); //
		double amountOfTicks = 60.0; //
		double ns = 1000000000 / amountOfTicks; // 1bn/amount of ticks
		double delta = 0; //

		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move(); // move components
				checkCollision(); // check for colision
				repaint(); // repaint
				delta--; // subtract time
			}
		}
	}

	/**
	 * Action Listener Class
	 * 
	 * @author Michael
	 *
	 */
	public class AL extends KeyAdapter {
		/**
		 * Key Press method of class
		 */
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}

		/**
		 * Key Release method of class
		 */
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}