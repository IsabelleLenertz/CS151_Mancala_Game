import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

/**
 * Class that handles the display and update of the display
 * for the board game.
 * 
 * @author Lucas Galleguillos
 */
public class BoardView extends JLabel implements View {
	private BoardModel model;
	private BoardStrategy strategy;
	private Dimension size;
	private int height;
	private int width;
	
	/**
	 * Single args ctor for the BoardView class. Allows the user to define the size
	 * for the BoardView. The height of the BoardView is determined by the width.
	 * @param width the width for the BoardView to display at
	 */
	public BoardView(int width) {
		this.width = width;
		height = width / 2;
		size = new Dimension(width, height);
		setPreferredSize(size);
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
	 * Called by the model when the view is to be updated.
	 */
	public void isNotified() {
		repaint();
	}
	
	/**
	 * Override the inherited paint component method from JLabel. This allows for
	 * the custom game board to be displayed using the underlying strategy.
	 * 
	 * @param g the default argument associated with this inherited method
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// Start display of the background.
		strategy.drawBoardBackground(0, 0, 400, 400, g2);
		
		Player[] players = model.getPlayers();
		
		Hole[] holes = model.getHoles();
		
		// Start display of board base pieces.
		for (int j = 0; j < holes.length; j = j + 1) {
			switch(j) {
				case 0:
					// Player 1 Mancala
					strategy.drawMancala(players[0].getScore(), width / 10, height / 8, width / 10, (3 * height) / 4 , g2);
					break;
				case 7:
					// Player 2 Mancala
					strategy.drawMancala(players[1].getScore(), (8 * width) / 10, height / 8, width / 10, (3 * height) / 4 , g2);
					break;
				default:
					// The remaining pits. Don't have to specify which pit is whose
					// since all pits are identical in appearance.
					// To do: System for dynamically changing where the pits are drawn.
					strategy.drawPit(holes[j].getStones(), 0, 0, width / 10, 10, g2);
					break;
			}
		}
		
		// Start display of game information overlay.
		for (int i = 0; i < players.length; i = i + 1) {
			switch(i) {
				case 0:
					// Display Player 1
					strategy.displayPlayer("Player 1", (3 * width) / 21, (2 * height) / 8, g2);
					strategy.displayScore(players[0].getScore(), width / 20, 0, g2);
					break;
				case 1:
					// Display Player 2
					strategy.displayPlayer("Player 2", (17 * width) / 20, (2 * height) / 8, g2);
					strategy.displayScore(players[1].getScore(), (15 * width) / 20, 0, g2);
					break;
			}
		}
	}
	
	/**
	 * Setter for this BoardView's underlying BoardModel.
	 * 
	 * @param model the BoardModel associated with this BoardView
	 */
	public void setBoardModel(BoardModel model) {
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
	 * Setter for the BoardView width. BoardView height is tied to the width.
	 * 
	 * @param width the new width for this BoardView
	 */
	public void setWidth(int width) {
		this.width = width;
		height = width / 2;
		size = new Dimension(width, height);
		setPreferredSize(size);
	}
}