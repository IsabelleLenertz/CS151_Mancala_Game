import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class for testing BoardView.
 * 
 * @author Lucas Galleguillos
 */
public class BoardViewTester {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		BoardModel bm = new BoardModel();
		RoundBoard rb = new RoundBoard();
		SquareBoard sb = new SquareBoard();
		BoardView bv = new BoardView(400);
		
		bv.setBoardStrategy(rb);
		bv.setBoardModel(bm);
		bm.setBoardView(bv);
		
		bv.repaint();
		
		panel.add(bv);
		
		frame.add(panel);
		
		frame.pack();
		
		frame.setVisible(true);
	}
}
