import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DisplayStrategyTester {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBackground(Color.PINK);
		frame.setSize(900,  900);
		JPanel panel = new StrategyComponentTester();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);


	}

}
