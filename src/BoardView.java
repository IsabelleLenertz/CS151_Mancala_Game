import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Class that handles the display and update of the display
 * for the board game.
 * 
 * @author Lucas Galleguillos
 * updated on 05/05/2018	by Isabelle Delmas		reason : added display of the winner when the game ends
 */
public class BoardView extends JLabel implements View {
	private BoardModel model;
	private BoardStrategy strategy;
	private Dimension size;
	private int height;
	private int width;
	private Shape[] shapes;
	private boolean over;
	
	/**
	 * Single args ctor for the BoardView class. Allows the user to define the size
	 * for the BoardView. The height of the BoardView is determined by the width.
	 * @param width the width for the BoardView to display at
	 */
	public BoardView(int width, int height) {
		this.width = width;
		this.height = height;
		size = new Dimension(width, this.height);
		setPreferredSize(size);
		shapes = new Shape[15];
		over = false;
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
	 * Accessor for the location data of each hole.
	 * 
	 * @return a Shape[] where each Shape represents a Hole
	 */
	public Shape[] getShapes() {
		return shapes;
	}
	
	/**
	 * Called by the model when the view is to be updated.
	 */
	public void isNotified() {
		repaint();
		if (model.gameIsOver()) {
			int score1 = model.getHoles()[6].getStones();
			int score2 = model.getHoles()[13].getStones();
			String winner;
			if (score1 > score2) {
				winner = "Player A";
			} else {
				winner = "Player B";
			}
			
			if(!over)
				JOptionPane.showMessageDialog(this, "the game is over, " + winner + " won.");
			over = true;

		}
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
		strategy.drawBoardBackground(0, 0, height, width, g2);
		
		// Start display of board base pieces.
		Player[] players = model.getPlayers();
		Hole[] holes = model.getHoles();
		int holeWidth = (2 * width) / 25;
		
		// Display the mancalas.
		shapes[6] = strategy.drawMancala(players[1].getScore(), holeWidth, height / 8, holeWidth, (3 * height) / 4 , g2);
		shapes[13] = strategy.drawMancala(players[0].getScore(), (21 * width) / 25, height / 8, holeWidth, (3 * height) / 4 , g2);
		
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
		strategy.displayScore(players[1].getScore(), width / 20, height / 25, g2);
		
		// Display Player 2 information.
		strategy.displayPlayer("MANCALA A", width - ( (3 * holeWidth) / 4), height / 5, g2);
		strategy.displayScore(players[0].getScore(), (17 * width) / 21, height / 25, g2);
		
		// Draw the undo button.
		Shape undoButton = new Rectangle2D.Double(width / 2, 2 * height / 5, (7.0 / 10.0) * (2 * holeWidth), (7.0 / 10.0) * holeWidth);
		g2.setColor(Color.WHITE);
		g2.fill(undoButton);
		g2.setColor(Color.BLACK);
		g2.drawString("UNDO", (11 * width) / 21, 5 * height / 11);
		shapes[14] = undoButton;
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
	 * Setter for the BoardView width.
	 * 
	 * @param width the new width for this BoardView
	 */
	public void setWidth(int width) {
		this.width = width;
		size = new Dimension(this.width, height);
		setPreferredSize(size);
	}
	
	/**
	 * Setter for the BoardView height.
	 * 
	 * @param width the new height for this BoardView
	 */
	public void setHeight(int height) {
		this.height = height;
		size = new Dimension(width, this.height);
		setPreferredSize(size);
	}
}