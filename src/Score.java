
import java.awt.*;

/**
 * Constructor for Score
 * @author Michael
 *
 */
public class Score extends Rectangle{

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1; // Current score for player 1
	int player2; // Current score for player 2
	
	/**
	 * Pass in copy from constructor
	 * @param GAME_WIDTH
	 * @param GAME_HEIGHT
	 */
	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	/**
	 * Draw score on JFrame Screen
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.white); // Score is White
		g.setFont(new Font("Arial",Font.PLAIN,60));
	
//		Draw line down center of screen
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
//		Position for Scoring
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);
	}
}