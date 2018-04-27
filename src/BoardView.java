import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Class that handles the display and update of the display
 * for the board game.
 * 
 * @author Lucas Galleguillos
 */
public class BoardView extends JPanel implements View {
	private BoardModel model;
	private BoardStrategy strategy;
	
	/**
	 * One args ctor for the BoardView class. Assigns a BoardModel instance to this BoardView.
	 * 
	 * @param model the underlying BoardModel
	 */
	public BoardView(BoardModel model) {
		this.model = model;
	}
	
	/**
	 * Setter for this BoardView's underlying BoardStrategy.
	 * 
	 * @param strategy the new BoardStrategy this BoardView will contain
	 */
	public void setBoardStrategy(BoardStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * Getter for this BoardView's underlying BoardStrategy. 
	 * 
	 * @return the BoardStrategy contained by this BoardView
	 */
	public BoardStrategy getBoardStrategy() {
		return strategy;
	}
	
	/**
	 * Override the inherited paint component method from JPanel. This allows for
	 * the custom game board to be displayed using the underlying strategy.
	 * 
	 * @param g the default argument associated with this inherited method
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		strategy.drawBoardBackground(0, 0, 400, 400, g2);
		
		Player[] players = model.getPlayers();
		
		Hole[] holes = model.getHoles();
		
		for (int i = 0; i < holes.length; i = i + 1) {
			if (i == 0) { // Player 1 Mancala
				
			} else if (i == 7) { // Player 2 Mancala
				
			} else { // The remaining pits
				
			}
		}
	}
	
	/**
	 * Called by the model when the view is to be updated.
	 */
	public void isNotified() {
		repaint();
	}
}