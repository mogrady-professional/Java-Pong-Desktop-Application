import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Stopwatch implements ActionListener {
//	Create JFrame
	JFrame frame = new JFrame();
	JButton startButton = new JButton("Start");
	JButton resetButton = new JButton("Reset");
	JLabel timeLabel = new JLabel();
	int elapsedTime = 0; // holds amount of m/s after timer start
	int seconds = 0; // hold seconds
	int minutes = 0;
	int hours = 0;
	boolean started = false; // determine if timer has started or not

//	Placeholder 
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);

	
	/**
	 * Timer operates every 1s
	 */
	Timer timer = new Timer(1000, new ActionListener() {
		
		/**
		 * ActionEvent operated by timer if every 1s
		 */
		public void actionPerformed(ActionEvent e) {
			
			elapsedTime=elapsedTime+1000; // increase elapsed time by 1s
			hours = (elapsedTime/3600000); // figure hours that have passed 3,600,000 -> m/s in 1hr
			minutes = (elapsedTime/60000) % 60; // 60,000 m/s in 1m 
			seconds = (elapsedTime/1000) % 60; // 1,000 m/s in 1s -> nothing 60 or above (no left overs with modulus)
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			hours_string = String.format("%02d", hours);
//			Update timeLabel with new Strings
			timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		}
		
	});
	
	
	/**
	 * Stopwatch
	 */
	Stopwatch() {
//		Set text
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		timeLabel.setBounds(100,100,200,100); // X, Y, LENGTH, HEIGHT
		timeLabel.setFont(new Font("Verdana", Font.PLAIN,35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);// horizontal allignment
		
//		Start Button
		startButton.setBounds(100, 200, 100, 50); // X, Y, LENGTH, HEIGHT
		startButton.setFont(new Font("Ink Free", Font.PLAIN,20));
		startButton.setFocusable(false);
		startButton.addActionListener(this); // Action Listener
		
//		Reset Button
		resetButton.setBounds(200, 200, 100, 50); // X, Y, LENGTH, HEIGHT
		resetButton.setFont(new Font("Ink Free", Font.PLAIN,20));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this); // Action Listener
		
//		Frame specifics
		frame.add(startButton); // Add startButton to frame
		frame.add(resetButton); // Add resetButton to frame
		frame.add(timeLabel); // Add timeLabel to frame
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Java Desktop Stopwatch Application");
		frame.setSize(415, 415);
		frame.setLayout(null);
		frame.setVisible(true);

	}

	/**
	 * Call start function on timer
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	if(e.getSource()==startButton) {
			
			if(started==false) {
				started=true;
				startButton.setText("STOP"); // Update text on button
				start(); // Start timer
			}
			else {
				started=false;
				startButton.setText("START"); // Update text on button
				stop(); // Stop timer
			}
			
		}
		if(e.getSource()==resetButton) {
			started=false;
			startButton.setText("START");
			reset();
		}
	}

	/**
	 * Call start timer method
	 */
	void start() {
		timer.start();
	}

	/**
	 * Call stop timer method
	 */
	void stop() {
		timer.stop();
	}

	/**
	 * Reset Values, update strings, change time label
	 */
	void reset() {
		timer.stop();
		elapsedTime=0;
		seconds =0;
		minutes=0;
		hours=0;
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
	}

}
