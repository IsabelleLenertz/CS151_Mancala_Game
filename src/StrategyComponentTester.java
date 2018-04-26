import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StrategyComponentTester extends JPanel {

	private static final int HEIGHT = 40;
	private static final int INTERVAL = 5;
	private static final int W_INTERVAL = 10;
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		BoardStrategy test = new SquareBoard();

		for (int i = 0; i < 30; i++) {
			test.drawPit(i,  W_INTERVAL, 305 + (i)*(INTERVAL+HEIGHT),  HEIGHT,  HEIGHT,  g2);
			test.drawMancala(i+25,  W_INTERVAL*2 + HEIGHT, 305 + (i)*(INTERVAL+HEIGHT*3), HEIGHT, HEIGHT*3, g2);
			test.displayScrore(i, W_INTERVAL*3 + HEIGHT*2, 305 + i*(INTERVAL+HEIGHT), g2);
			test.displayPlayer("Player " +i, W_INTERVAL*6 + HEIGHT*3, 305 + i*3*(INTERVAL+HEIGHT), g2);
			test.drawBoardBackground(300,  200, g2);
		}
		
	}

}
