import java.awt.Graphics2D;

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
	 */
	public void drawPit(int numberOfStones, int xPosition, int yPosition, int width, int heigth, Graphics2D g2);
	
	/**
	 * Draw a mancala on the board view
	 * @param xPosition x position of the mancala (upper left corner)
	 * @param yPosition y position of the mancala (upper left corner)
	 * @param height height of the pit
	 * @param width width of the pit
	 * @param g2 graphical context
	 */
	public void drawMancala(int numberOfStones, int xPosition, int yPosition, int width, int heigth, Graphics2D g2);
	
	/**
	 * Display the score of the player on the board view
	 * @param score score to display
	 * @param xPosition x position of the score (upper left corner)
	 * @param yPosition y position of the score (upper left corner)
	 * @param g2 graphical context
	 */
	public void displayScrore(int score, int xPosition, int yPosition, Graphics2D g2);
	
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
	
}
