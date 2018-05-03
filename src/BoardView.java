import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

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
	private Shape[] shapes;
	
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
		shapes = new Shape[14];
	}
	
	/**
	 * Getter for this BoardView's underlying BoardStrategy. 
	 * 
	 * @return the BoardStrategy contained by this BoardView
	 */
	public BoardStrategy getBoardStrategy() {
		return strategy;
	}
	
	public Shape[] getShapes() {
		return shapes;
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
		
		// Display the background.
		strategy.drawBoardBackground(0, 0, 400, 400, g2);
		
		// Start display of board base pieces.
		Player[] players = model.getPlayers();
		Hole[] holes = model.getHoles();
		int holeWidth = (2 * width) / 25;
		
		// Display the mancalas.
		shapes[13] = strategy.drawMancala(players[0].getScore(), holeWidth, height / 8, holeWidth, (3 * height) / 4 , g2);
		shapes[6] = strategy.drawMancala(players[1].getScore(), (21 * width) / 25, height / 8, holeWidth, (3 * height) / 4 , g2);
		
		// Simultaneous display of top and bottom pit rows.
		for (int i = 0; i < 6; i = i + 1) {
			// Player 1 row
			shapes[i] = strategy.drawPit(holes[i].getStones(), (((3 * i) + 5))*(width / 26), ((25 * height) - (4 * width)) / 25, holeWidth, holeWidth, g2);
			strategy.displayPitLabel("A" + (i + 1), (((3 * i) + 5))*(width / 26), ((25 * height) - (5 * width)) / 25, g2);
			
			// Player 2 row
			shapes[12 - i] = strategy.drawPit(holes[12 - i].getStones(), (((3 * i) + 5))*(width / 26), height / 8, holeWidth, holeWidth, g2);
			strategy.displayPitLabel("B" + (6 - i), (((3 * i) + 5))*(width / 26), (2 * height) / 7, g2);
		}
		
		// Display game information overlay.
		
		// Display Player 1 information.
		strategy.displayPlayer("MANCALA B", holeWidth / 2, height / 5, g2);
		strategy.displayScore(players[0].getScore(), width / 20, height / 25, g2);
		
		// Display Player 2 information.
		strategy.displayPlayer("MANCALA A", width - ( (3 * holeWidth) / 4), height / 5, g2);
		strategy.displayScore(players[1].getScore(), (17 * width) / 21, height / 25, g2);
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