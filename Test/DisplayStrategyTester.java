import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DisplayStrategyTester {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBackground(new Color(53, 0, 252));
		frame.setSize(900,  900);
		JPanel panel = new StrategyComponentTester();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);


	}

}
