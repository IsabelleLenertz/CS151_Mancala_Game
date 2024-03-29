import java.awt.Graphics2D;
import java.awt.Shape;

/**
 * Interface for the Strategy in the Strategy pattern
 * Used by the BoardView to define the shape and colors of the board
 * @author Isabelle Delmas
 * @date 05-25-2018
 * @revision 05-26-2018			Reason: updated method headers for easier use
 *
 */
public interface BoardStrategy {
	/**
	 * Draw a pit on the board view
	 * @param xPosition x position of the pit (upper left corner)
	 * @param yPosition y position of the pit (upper left corner)
	 * @param height height of the pit
	 * @param width width of the pit
	 * @param g2 graphical context
	 * @return reference to the shape that represents the Pit
	 * @return 
	 */
	public Shape drawPit(int numberOfStones, int xPosition, int yPosition, int width, int heigth, Graphics2D g2);
	
	/**
	 * Draw a mancala on the board view
	 * @param xPosition x position of the mancala (upper left corner)
	 * @param yPosition y position of the mancala (upper left corner)
	 * @param height height of the pit
	 * @param width width of the pit
	 * @param g2 graphical context
	 * @return reference to the shape that represents the Mancala
	 * @return 
	 */
	public Shape drawMancala(int numberOfStones, int xPosition, int yPosition, int width, int heigth, Graphics2D g2);
	
	/**
	 * Display the score of the player on the board view
	 * @param score score to display
	 * @param xPosition x position of the score (upper left corner)
	 * @param yPosition y position of the score (upper left corner)
	 * @param g2 graphical context
	 */
	public void displayScore(int score, int xPosition, int yPosition, Graphics2D g2);
	
	/**
	 * Display the name of the player on the board view
	 * @param playerName name of the player
	 * @param xPosition x position of the player (upper left corner)
	 * @param yPosition y position of the player (upper left corner)
	 * @param g2 graphical context
	 */
	public void displayPlayer(String playerName, int xPosition, int yPosition, Graphics2D g2);
	
	/**
	 * Display the boarder and background of the board on the board view
	 * @param height height of the board view
	 * @param width width of the board view
	 * @param g2 graphical context
	 */
	public void drawBoardBackground(int xPosition, int yPosition, int height, int width, Graphics2D g2);
	
	/**
	 * Display a label on the board view
	 * @param label string to display
	 * @param xPosition x position of the label (upper left corner)
	 * @param yPosition y position of the label (upper left corner)
	 * @param g2 graphical context
	 */
	public void displayPitLabel(String label, int xPosition, int yPosition, Graphics2D g2);
	
}
