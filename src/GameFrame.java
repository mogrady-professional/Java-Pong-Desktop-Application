import java.awt.Color;

import javax.swing.JFrame;

/**
 * GameFrame houses panel -> surrounded by JFrame
 * @author Michael
 *
 */
public class GameFrame extends JFrame{

	GamePanel panel;
	/**
	 * Constructor for game panel
	 */
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Java Pong Desktop Application");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
		this.pack(); // Gameframe panel fits around panel-> adjust to accommodate size of frame 
		this.setVisible(true); // Display visible
		this.setLocationRelativeTo(null); // Center JFrame on screen
	}
}